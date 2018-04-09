package br.com.cleartech.pgmc.mgu.services.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleartech.pgmc.mgu.entities.Delegado;
import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.LogSenhaUsuario;
import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.AssuntoEnum;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;
import br.com.cleartech.pgmc.mgu.enums.ParametrizacaoEnum;
import br.com.cleartech.pgmc.mgu.enums.StatusDynamics;
import br.com.cleartech.pgmc.mgu.exceptions.DynamicsException;
import br.com.cleartech.pgmc.mgu.exceptions.LdapException;
import br.com.cleartech.pgmc.mgu.exceptions.MguException;
import br.com.cleartech.pgmc.mgu.repositories.LogSenhaUsuarioRepository;
import br.com.cleartech.pgmc.mgu.repositories.PrestadoraRepository;
import br.com.cleartech.pgmc.mgu.repositories.UsuarioRepository;
import br.com.cleartech.pgmc.mgu.services.DelegadoService;
import br.com.cleartech.pgmc.mgu.services.DynamicsService;
import br.com.cleartech.pgmc.mgu.services.EmailService;
import br.com.cleartech.pgmc.mgu.services.GrupoPerfilService;
import br.com.cleartech.pgmc.mgu.services.LdapService;
import br.com.cleartech.pgmc.mgu.services.ParametrizacaoService;
import br.com.cleartech.pgmc.mgu.services.PerfilService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.utils.GeradorSenha;
import br.com.cleartech.pgmc.mgu.wsdl.dynamics.DadosRetorno;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger logger = LoggerFactory.getLogger( UsuarioService.class );

	private final List<String> mensagensValidasDynamics = Arrays.asList( "Usuário criado com Sucesso", "Usuário ja possui cadastro. Foram adicionadas as permissões do PGMC", "Usuário ja possui CPF ou Email Cadastrado" );

	private static final String ERRO_CONEXAO_DYNAMICS = "DYNAMICS ERRO: Problemas no serviço de integração com o Portal Dynanimcs. Favor entrar em contato com a Central de Serviços através do e-mail: suporte.pgmc@cleartech.com.br.";

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private LdapService ldapService;

	@Autowired
	private ParametrizacaoService parametrizacaoService;

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private GrupoPerfilService grupoPerfilService;

	@Autowired
	private DynamicsService dynamicsService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private LogSenhaUsuarioRepository logSenhaUsuarioRepository;

	@Autowired
	private DelegadoService delegadoService;

	@Autowired
	private PrestadoraRepository prestadoraRepository;

	@Override
	public Usuario salvar( Usuario usuario ) {
		return usuarioRepository.save( usuario );
	}

	@Override
	public Usuario salvar( Usuario usuario, boolean master, Prestadora prestadoraLogada ) throws Exception {
		if ( !master ) {
			usuario.setFlEnviarDynamics( false );
		}
		String senha = GeradorSenha.getRandomPassword( 8 );
		usuario.setDcSenha( GeradorSenha.md5( senha ) );

		try {
			ldapService.createUser( usuario.getDcUsername(), usuario.getDcSenha(), master );
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new LdapException( "LDAP ERRO: Não Foi possivel criar o usuario \"" + usuario.getDcUsername() + "\"" );
		}

		try {
			boolean dynamicsAtivo = parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) != null ? Boolean.parseBoolean( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) ) : false;
			if ( dynamicsAtivo ) {
				DadosRetorno retornoDynamics = dynamicsService.criarUsuario( usuario, prestadoraLogada );

				if ( StatusDynamics.SUCESSO.getValue().equals( retornoDynamics.getId().getValue() ) ) {
					if ( mensagensValidasDynamics.contains( retornoDynamics.getMensagem().getValue() ) ) {
						if ( existsPerfilDynamics( usuario ) ) {
							usuario.setFlEnviarDynamics( true );
						}
					}
				} else {
					ldapService.deleteUser( usuario.getDcUsername(), master );
					throw new DynamicsException( "DYNAMICS ERRO: " + transformarMensagem( retornoDynamics.getMensagem().getValue() ) );
				}
			}
		} catch ( DynamicsException e ) {
			throw new DynamicsException( e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			ldapService.deleteUser( usuario.getDcUsername(), master );
			throw new MguException( ERRO_CONEXAO_DYNAMICS );
		}

		usuarioRepository.save( usuario );

		try {
			usuario.setSenhaSemMD5( senha );
			emailService.enviaByUsuarioAndAssunto( usuario, AssuntoEnum.CRIAR_USUARIO, null );
		} catch ( Exception e ) {
			throw new MessagingException( "MGU ALERTA: Usuário criado com sucesso porém não foi possível enviar a senha para o e-mail \"" + usuario.getDcEmail() + "\", favor entrar em contato com a Central de Serviços através do e-mail: suporte.pgmc@cleartech.com.br para solicitar a senha de acesso do usuário \"" + usuario.getDcUsername() + "\"" );
		}

		return usuario;
	}

	@Override
	public Usuario salvarEditar( Usuario usuarioAtualizado, Usuario usuarioDB, Prestadora prestadoraLogada ) throws Exception {
		usuarioAtualizado.setUltimoAcesso( usuarioDB.getUltimoAcesso() == null ? new Date() : usuarioDB.getUltimoAcesso() );
		usuarioAtualizado.setUltimaTrocaSenha( usuarioDB.getUltimaTrocaSenha() == null ? new Date() : usuarioDB.getUltimaTrocaSenha() );

		Boolean paraBloqueio = null;
		if ( BloqueioUsuario.BLOQUEADO_ADM.equals( usuarioDB.getFlBloqueio() ) && BloqueioUsuario.BLOQUEADO_NAO.equals( usuarioAtualizado.getFlBloqueio() ) ) {
			paraBloqueio = false;
		} else if ( BloqueioUsuario.BLOQUEADO_NAO.equals( usuarioDB.getFlBloqueio() ) && BloqueioUsuario.BLOQUEADO_ADM.equals( usuarioAtualizado.getFlBloqueio() ) ) {
			paraBloqueio = true;
		}

		if ( usuarioDB.getFlMaster() ) {
			boolean mudouDelegado = false;
			if ( ( usuarioDB.getDelegado() == null && usuarioAtualizado.getDelegado() != null ) || ( usuarioDB.getDelegado() != null && !usuarioDB.getDelegado().equals( usuarioAtualizado.getDelegado() ) ) ) {
				mudouDelegado = true;
			}
			if ( mudouDelegado && usuarioDB.getDelegado() != null ) {
				if ( !usuarioDB.getDelegado().getFlMaster() ) {
					ldapService.alterarMasterParaUsuario( usuarioDB.getDelegado().getDcUsername() );
				}
				try {
					emailService.enviaByUsuarioAndAssunto( usuarioAtualizado, AssuntoEnum.REMOVER_DELEGADO, usuarioDB.getDelegado() );
				} catch ( Exception e ) {
					// exceção de email sendo tratada no fim do metodo
				}
				delegadoService.removerDelegadoDeUsuarioMaster( usuarioDB );
			}
			if ( mudouDelegado && usuarioAtualizado.getDelegado() != null ) {
				Delegado delegado = new Delegado();
				delegado.setUsuarioMaster( usuarioDB );
				delegado.setUsuarioComum( usuarioAtualizado.getDelegado() );
				delegado.setPrestadora( usuarioAtualizado.getPrestadoras().get( 0 ) );
				delegadoService.salvar( delegado );
				try {
					emailService.enviaByUsuarioAndAssunto( usuarioAtualizado, AssuntoEnum.ADICIONAR_DELEGADO, usuarioAtualizado.getDelegado() );
				} catch ( Exception e ) {
					// exceção de email sendo tratada no fim do metodo
				}
				ldapService.alterarUsuarioParaMaster( usuarioAtualizado.getDelegado().getDcUsername() );
			}
		}

		usuarioAtualizado.setFlEnviarDynamics( false );
		if ( existsPerfilDynamics( usuarioAtualizado ) ) {
			usuarioAtualizado.setFlEnviarDynamics( true );
		}

		atualizaDados( usuarioAtualizado, usuarioDB, prestadoraRepository.buscaPrestadorasDoGrupo( prestadoraLogada.getGrupoPrestadora().getId() ), grupoPerfilService.findByPrestadora( prestadoraLogada.getId() ) );
		usuarioRepository.save( usuarioDB );

		try {
			boolean dynamicsAtivo = parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) != null ? Boolean.parseBoolean( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) ) : false;
			logger.info( "dynamics ativo? " + dynamicsAtivo );
			if ( dynamicsAtivo ) {
				dynamicsService.alterar( usuarioDB, false, prestadoraLogada );
			}
		} catch ( DynamicsException e ) {
			throw new DynamicsException( e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new MguException( ERRO_CONEXAO_DYNAMICS );
		}

		try {
			if ( paraBloqueio != null ) {
				if ( paraBloqueio ) {
					emailService.enviaByUsuarioAndAssunto( usuarioDB, AssuntoEnum.BLOQUEAR_USUARIO, null );
				} else if ( !paraBloqueio ) {
					emailService.enviaByUsuarioAndAssunto( usuarioDB, AssuntoEnum.DESBLOQUEAR_USUARIO, null );
				}
			}
		} catch ( Exception e ) {
			throw new MessagingException( "MGU ALERTA: Usuário atualizado com sucesso porém não foi possível enviar o e-mail de bloqueio/desbloqueio para \"" + usuarioAtualizado.getDcEmail() + "\", Favor entrar em contato com a Central de Serviços através do e-mail: suporte.pgmc@cleartech.com.br." );
		}

		return usuarioAtualizado;
	}

	private void atualizaDados( Usuario usuarioAtualizado, Usuario usuarioDB, List<Prestadora> prestadorasGrupo, List<GrupoPerfil> gruposPerfisDaPrestadora ) {
		usuarioDB.setNmUsuario( usuarioAtualizado.getNmUsuario() );
		usuarioDB.setDcEmail( usuarioAtualizado.getDcEmail() );
		usuarioDB.setDcCargo( usuarioAtualizado.getDcCargo() );
		usuarioDB.setDcTelefone( usuarioAtualizado.getDcTelefone() );
		usuarioDB.setDcTelefoneFixo( usuarioAtualizado.getDcTelefoneFixo() );
		usuarioDB.setNivelEscalonamento( usuarioAtualizado.getNivelEscalonamento() );
		
		usuarioDB.setGrupoPerfis( grupoPerfilService.findByUsuario( usuarioDB.getId(), null ) );
		usuarioDB.getGrupoPerfis().removeAll( gruposPerfisDaPrestadora );
		usuarioDB.getGrupoPerfis().addAll( usuarioAtualizado.getGrupoPerfis() );

		usuarioDB.setFlBloqueio( usuarioAtualizado.getFlBloqueio() );
		usuarioDB.setFlEnvioEmail( usuarioAtualizado.isFlEnvioEmail() );
		usuarioDB.setFlEnviarDynamics( usuarioAtualizado.getFlEnviarDynamics() );
		usuarioDB.setDelegado( usuarioAtualizado.getDelegado() );

		if ( !usuarioDB.getFlMaster() ) {
			usuarioDB.setPrestadoras( prestadorasGrupo );
		}
	}

	@Override
	public Usuario findByUsername( String username ) {
		return usuarioRepository.findByUsername( username );
	}

	@Override
	public boolean existsByUsernameIgnoreCase( String username ) {
		return usuarioRepository.findByUsernameIgnoreCase( username ) != null ? true : false;
	}

	@Override
	public Usuario findUsuarioMasterByUsernameAndIdPrestadora( String username, Long idPrestadora ) {
		return usuarioRepository.findUsuarioMasterByUsernameAndIdPrestadora( username, idPrestadora );
	}

	private boolean existsPerfilDynamics( Usuario usuario ) {
		Perfil perfilDynamics = perfilService.findByDcPerfilAndSistemaDcSistema( "DYNAMICS", "DYNAMICS" );
		for ( GrupoPerfil gpUsuario : usuario.getGrupoPerfis() ) {
			for ( GrupoPerfil gpDynamics : perfilDynamics.getGrupoPerfis() ) {
				if ( gpUsuario.getId().equals( gpDynamics.getId() ) ) {
					return true;
				}
			}
		}
		return false;
	}

	// Metodo feito para evitar modificação no ws do dynamics
	private String transformarMensagem( String mensagem ) {
		if ( mensagem.equals( "O usuário informado ja existe na base do Dynamics CRM." ) ) {
			mensagem = "O usuário de acesso informado já existe na base do Dynamics e não pode ser utilizado. Favor informar outro username.";
		}

		return mensagem;
	}

	@Override
	public void substituirUsuarioMaster( Usuario usuarioMasterNovo, String usernameAnterior ) throws Exception {
		// Atribuindo o perfil do usuário antigo para o novo e excluindo o
		// perfil do antigo.
		Usuario usuarioMasterAntigo = findUsuarioMasterByUsernameAndIdPrestadora( usernameAnterior, usuarioMasterNovo.getPrestadoras().get( 0 ).getId() );
		if ( usuarioMasterAntigo != null ) {
			// usuario novo deve assumir mesmo valor de fl_aprovado do usuario
			// antigo
			usuarioMasterNovo.setFlAprovado( new Boolean( usuarioMasterAntigo.getFlAprovado() ) );
			usuarioMasterNovo.getGrupoPerfis().addAll( new ArrayList<GrupoPerfil>( usuarioMasterAntigo.getGrupoPerfis() ) );

			usuarioMasterAntigo.setGrupoPerfis( null );
			bloquear( usuarioMasterAntigo, true );

		}

		usuarioMasterNovo.setFlMaster( true );
		usuarioMasterNovo.setFlBloqueio( BloqueioUsuario.BLOQUEADO_PRIMEIROACESSO );
		salvar( usuarioMasterNovo, true, usuarioMasterNovo.getPrestadoras().get( 0 ) );

	}

	@Override
	public void bloquear( Usuario usuario, boolean removerMaster ) throws Exception {
		usuario.setFlEnviarDynamics( false );
		usuario.setFlBloqueio( BloqueioUsuario.BLOQUEADO_ADM );
		usuarioRepository.save( usuario );

		try {
			boolean dynamicsAtivo = parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) != null ? Boolean.parseBoolean( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) ) : false;
			if ( dynamicsAtivo ) {
				dynamicsService.desativarUsuario( usuario.getDcUsername(), usuario.getDcEmail() );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new MguException( ERRO_CONEXAO_DYNAMICS );
		}

		if ( removerMaster ) {
			usuario.setFlMaster( false );
			usuarioRepository.save( usuario );
		}

		try {
			emailService.enviaByUsuarioAndAssunto( usuario, AssuntoEnum.BLOQUEAR_USUARIO, null );
		} catch ( Exception e ) {
			// TODO: Verificar se deve retornar
			new MessagingException( "MGU ALERTA: Não foi possivel enviar email de bloqueio para o usuario \"" + usuario.getDcUsername() + "\"" ).printStackTrace();
		}

	}

	@Override
	public void alteraBloqueioUsuario( Usuario usuario, BloqueioUsuario bloqueio, String usuarioAlterando ) throws Exception {
		usuario.setFlBloqueio( bloqueio );
		usuarioRepository.save( usuario );

		LogSenhaUsuario log = new LogSenhaUsuario();
		log.setDataAlteracao( new Timestamp( new Date().getTime() ) );
		log.setUsuario( usuario );
		log.setIpAlteracao( usuario.getDcIpOrigem() );
		log.setNomeUsuarioAlteracao( usuarioAlterando );
		logSenhaUsuarioRepository.save( log );

		try {
			emailService.enviaByUsuarioAndAssunto( usuario, AssuntoEnum.DESBLOQUEAR_USUARIO, null );
		} catch ( Exception e ) {
			throw new MessagingException( "MGU ALERTA: Usuário desbloqueado com sucesso porém não foi possível enviar a senha para o e-mail \"" + usuario.getDcUsername() + "\"" );
		}

	}

	@Override
	public List<Usuario> find( Usuario usuario, Long prestadoraId ) {
		return usuarioRepository.find( usuario, prestadoraId );
	}

	@Override
	public Usuario find( Long idUsuario ) {
		return usuarioRepository.findOne( idUsuario );
	}

	@Override
	public List<Usuario> findUsuarioDelegadoDisponivel( Long idUsuario, Long idPrestadora ) {
		return usuarioRepository.findUsuarioDelegadoDisponivel( idUsuario, idPrestadora );
	}

	@Override
	public void excluir( Long idUsuario ) throws Exception {
		Usuario usuario = usuarioRepository.findOne( idUsuario );

		if ( usuarioRepository.existsDelegadoComUsuario( idUsuario ) ) {
			throw new MguException( "MGU ERRO: Usuário não pode ser excluído porque é um delegado!" );
		}

		usuarioRepository.delete( idUsuario );

		try {
			ldapService.deleteUser( usuario.getDcUsername(), usuario.getFlMaster() );
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new LdapException( "LDAP ERRO: Usuário não existe no LDAP!" );
		}

		try {
			boolean dynamicsAtivo = parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) != null ? Boolean.parseBoolean( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) ) : false;
			if ( dynamicsAtivo ) {
				dynamicsService.desativarUsuario( usuario.getDcUsername(), usuario.getDcEmail() );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new MguException( ERRO_CONEXAO_DYNAMICS );
		}

		try {
			emailService.enviaByUsuarioAndAssunto( usuario, AssuntoEnum.REMOVER_USUARIO, null );
		} catch ( MessagingException e ) {
			e.printStackTrace();
			throw new MessagingException( "MGU ALERTA: Usuário excluído com sucesso porém não foi possível enviar o e-mail para \"" + usuario.getDcUsername() + "\"" );
		}

	}

	@Override
	public void resetar( Long idUsuario, String usuarioLogado, boolean realizadoPeloMaster ) throws Exception {
		Usuario usuario = usuarioRepository.findOne( idUsuario );
		usuario.setQtTentativaAcesso( 0l );

		String novaSenha = GeradorSenha.getRandomPassword( 8 );
		usuario.setDcSenha( novaSenha );
		ldapService.alterarSenha( usuario.getDcUsername(), GeradorSenha.md5( novaSenha ) );

		if ( realizadoPeloMaster ) {
			if ( !usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_NAO ) ) {
				alteraBloqueioUsuario( usuario, BloqueioUsuario.BLOQUEADO_NAO, usuarioLogado );
			}
		} else {
			alteraBloqueioUsuario( usuario, BloqueioUsuario.BLOQUEADO_EXPIRADA, usuarioLogado );
		}

		emailService.enviaByUsuarioAndAssunto( usuario, AssuntoEnum.REINICIAR_SENHA, null );
	}

	@Override
	public void alterarSenha( Long idUsuario, String usuarioLogado, String novaSenha ) throws Exception {
		Usuario usuario = usuarioRepository.findOne( idUsuario );
		usuario.setFlPrimeiroAcessoSNOA( false );
		usuario.setUltimaTrocaSenha( new Date() );
		usuario.setUltimoAcesso( new Date() );
		usuario.setQtTentativaAcesso( 0l );

		novaSenha = GeradorSenha.md5( novaSenha );
		usuario.setDcSenha( novaSenha );
		ldapService.alterarSenha( usuario.getDcUsername(), novaSenha );

		if ( !usuario.getFlBloqueio().equals( BloqueioUsuario.BLOQUEADO_NAO ) ) {
			alteraBloqueioUsuario( usuario, BloqueioUsuario.BLOQUEADO_NAO, usuarioLogado );
		}

		emailService.enviaByUsuarioAndAssunto( usuario, AssuntoEnum.ALTERAR_SENHA, null );
	}

	@Override
	public void desbloquear( Usuario usuario, Prestadora prestadoraLogada ) throws Exception {
		usuario.setFlEnviarDynamics( true );
		usuario.setFlBloqueio( BloqueioUsuario.BLOQUEADO_NAO );
		usuarioRepository.save( usuario );

		try {
			boolean dynamicsAtivo = parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) != null ? Boolean.parseBoolean( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) ) : false;
			if ( dynamicsAtivo ) {
				dynamicsService.criarUsuario( usuario, prestadoraLogada );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new MguException( ERRO_CONEXAO_DYNAMICS );
		}

		try {
			emailService.enviaByUsuarioAndAssunto( usuario, AssuntoEnum.DESBLOQUEAR_USUARIO, null );
		} catch ( Exception e ) {
			// TODO: Verificar se deve retornar
			new MessagingException( "MGU ALERTA: Não foi possivel enviar email de desbloqueio para o usuario \"" + usuario.getDcUsername() + "\"" ).printStackTrace();
		}
	}

	@Override
	public Usuario findLite( long idUsuario ) {
		return usuarioRepository.findLite( idUsuario );
	}

}
