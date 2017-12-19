package br.com.cleartech.pgmc.mgu.services.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.LogSenhaUsuario;
import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.AssuntoEnum;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;
import br.com.cleartech.pgmc.mgu.enums.ParametrizacaoEnum;
import br.com.cleartech.pgmc.mgu.enums.StatusDynamics;
import br.com.cleartech.pgmc.mgu.exceptions.DynamicsException;
import br.com.cleartech.pgmc.mgu.exceptions.LdapException;
import br.com.cleartech.pgmc.mgu.exceptions.MguException;
import br.com.cleartech.pgmc.mgu.repositories.LogSenhaUsuarioRepository;
import br.com.cleartech.pgmc.mgu.repositories.UsuarioRepository;
import br.com.cleartech.pgmc.mgu.services.DynamicsService;
import br.com.cleartech.pgmc.mgu.services.EmailService;
import br.com.cleartech.pgmc.mgu.services.LdapService;
import br.com.cleartech.pgmc.mgu.services.ParametrizacaoService;
import br.com.cleartech.pgmc.mgu.services.PerfilService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.utils.GeradorSenha;
import br.com.cleartech.pgmc.mgu.wsdl.dynamics.DadosRetorno;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	private final List<String> mensagensValidasDynamics = Arrays.asList( "Usuário criado com Sucesso", "Usuário ja possui cadastro. Foram adicionadas as permissões do PGMC", "Usuário ja possui CPF ou Email Cadastrado" );

	private static final String ERRO_CONEXAO_DYNAMICS = "DYNAMICS ERRO: Problemas no serviço de integração com o Portal Dynanimcs. Favor entrar em contato com a Central de Serviços.";

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private LdapService ldapService;

	@Autowired
	private ParametrizacaoService parametrizacaoService;

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private DynamicsService dynamicsService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private LogSenhaUsuarioRepository logSenhaUsuarioRepository;

	@Override
	public Usuario salvar( Usuario usuario ) {
		return usuarioRepository.save( usuario );
	}

	@Override
	public Usuario findByUsername( String username ) {
		return usuarioRepository.findByUsername( username );
	}

	@Override
	public boolean existsByUsername( String username ) {
		return usuarioRepository.findByUsernameIgnoreCase( username ) != null ? true : false;
	}

	@Override
	public Usuario findUsuarioMasterByUsernameAndIdPrestadora( String username, Long idPrestadora ) {
		return usuarioRepository.findUsuarioMasterByUsernameAndIdPrestadora( username, idPrestadora );
	}

	@Override
	public void salvarUsuarioMaster( Usuario usuario ) throws Exception {
		String senha = GeradorSenha.getRandomPassword( 8 );
		usuario.setDcSenha( GeradorSenha.md5( senha ) );
		usuarioRepository.save( usuario );

		try {
			ldapService.createMasterUser( usuario.getDcUsername(), usuario.getDcSenha() );
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new LdapException( "LDAP ERRO: Não Foi possivel criar o usuario \"" + usuario.getDcUsername() + "\"" );
		}

		try {
			boolean dynamicsAtivo = parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) != null ? Boolean.parseBoolean( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) ) : false;
			if ( dynamicsAtivo ) {
				DadosRetorno retornoDynamics = dynamicsService.criarUsuario( usuario );

				if ( StatusDynamics.SUCESSO.getValue().equals( retornoDynamics.getId().getValue() ) ) {
					if ( mensagensValidasDynamics.contains( retornoDynamics.getMensagem().getValue() ) ) {
						usuario.setFlEnviarDynamics( true );
						usuarioRepository.save( usuario );
					}
				} else {
					ldapService.deleteUser( usuario.getDcUsername(), true );
					throw new DynamicsException( "DYNAMICS ERRO: " + transformarMensagem( retornoDynamics.getMensagem().getValue() ) );
				}
			}
		} catch ( DynamicsException e ) {
			throw new DynamicsException( e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			ldapService.deleteUser( usuario.getDcUsername(), true );
			throw new MguException( ERRO_CONEXAO_DYNAMICS );
		}

		try {
			usuario.setSenhaSemMD5( senha );
			emailService.enviaByUsuarioAndAssunto( usuario, AssuntoEnum.CRIAR_USUARIO );
		} catch ( Exception e ) {
			throw new MessagingException( "MGU ALERTA: Usuário criado com sucesso porém não foi possível enviar a senha para o e-mail \"" + usuario.getDcEmail() + "\", favor entrar em contato com a central de serviços para solicitar a senha de acesso do usuário \"" + usuario.getDcUsername() + "\"\n\n" );
		}

	}

	private boolean existsPerfilDynamics( Usuario usuario ) {
		Perfil perfilDynamics = perfilService.findByDcPerfilAndSistemaDcSistema( "DYNAMICS", "DYNAMICS" );
		for ( Long gpId : usuario.getListaGrupoPerfil() ) {
			for ( GrupoPerfil gp : perfilDynamics.getGrupoPerfis() ) {
				if ( gpId.equals( gp.getId() ) ) {
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
			// usuario novo deve assumir mesmo valor de fl_aprovado do
			// usuario antigo
			usuarioMasterNovo.setFlAprovado( usuarioMasterAntigo.getFlAprovado() );

			usuarioMasterNovo.getGrupoPerfis().addAll( new ArrayList<GrupoPerfil>( usuarioMasterAntigo.getGrupoPerfis() ) );

			// usuarioXGrupoPerfilService.deletarPorUsuario( usuarioMasterAntigo
			bloquear( usuarioMasterAntigo, true );
		}

		usuarioMasterNovo.setFlMaster( true );
		usuarioMasterNovo.setFlBloqueio( BloqueioUsuario.BLOQUEADO_PRIMEIROACESSO );
		salvarUsuarioMaster( usuarioMasterNovo );

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
			emailService.enviaByUsuarioAndAssunto( usuario, AssuntoEnum.BLOQUEAR_USUARIO );
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
			emailService.enviaByUsuarioAndAssunto( usuario, AssuntoEnum.DESBLOQUEAR_USUARIO );
		} catch ( MessagingException e ) {
			throw new MessagingException( "MGU ALERTA: Usuário desbloqueado com sucesso porém não foi possível enviar a senha para o e-mail \"" + usuario.getDcUsername() + "\"" );
		}

	}

}
