package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.AssuntoEnum;
import br.com.cleartech.pgmc.mgu.enums.ParametrizacaoEnum;
import br.com.cleartech.pgmc.mgu.enums.StatusDynamics;
import br.com.cleartech.pgmc.mgu.exceptions.DynamicsException;
import br.com.cleartech.pgmc.mgu.exceptions.LdapException;
import br.com.cleartech.pgmc.mgu.exceptions.MguException;
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

	@Override
	public Usuario salvar( Usuario usuario ) {
		return usuarioRepository.save( usuario );
	}

	@Override
	public Usuario findByUsername( String username ) {
		return usuarioRepository.findByDcUsername( username );
	}

	@Override
	public boolean existsByUsername( String username ) {
		return usuarioRepository.findByDcUsername( username ) != null ? true : false;
	}

	@Override
	public Usuario salvarUsuarioMaster( Usuario usuario ) throws Exception {
		String senha = GeradorSenha.getRandomPassword( 8 );
		usuario.setDcSenha( GeradorSenha.md5( senha ) );
		usuarioRepository.save( usuario );

		try {
			ldapService.createMasterUser( usuario.getDcUsername(), usuario.getDcSenha() );
		} catch ( Exception e ) {
			// usuarioRepository.deletarParaRollback( usuario );
			throw new LdapException( "LDAP ERRO: Não Foi possivel criar o usuario \"" + usuario.getDcUsername() + "\"" );
		}

		try {
			boolean dynamicsAtivo = parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) != null ? Boolean.parseBoolean( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.DYNAMICS_ACTIVE.getDcParametro() ) ) : false;
			if ( dynamicsAtivo ) {
				DadosRetorno retornoDynamics = dynamicsService.criarUsuario( usuario );
				System.out.println( retornoDynamics.toString() );

				if ( StatusDynamics.SUCESSO.getValue().equals( retornoDynamics.getId().getValue() ) ) {
					if ( mensagensValidasDynamics.contains( retornoDynamics.getMensagem().getValue() ) ) {
						if ( existsPerfilDynamics( usuario ) ) {
							usuario.setFlEnviarDynamics( true );
							usuarioRepository.save( usuario );
						}
					}
				} else {
					ldapService.deleteUser( usuario.getDcUsername(), true );
					// usuarioRepository.deletarParaRollback( usuario );
					throw new DynamicsException( "DYNAMICS ERRO: " + transformarMensagem( retornoDynamics.getMensagem().getValue() ) );
				}
			}
		} catch ( DynamicsException e ) {
			throw new DynamicsException( e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			ldapService.deleteUser( usuario.getDcUsername(), true );
			// usuarioRepository.deletarParaRollback( usuario );
			throw new MguException( ERRO_CONEXAO_DYNAMICS );
		}

		try {
			usuario.setSenhaSemMD5( senha );
			emailService.enviaByUsuarioAndAssunto( usuario, AssuntoEnum.CRIAR_USUARIO );
		} catch ( Exception e ) {
			e.printStackTrace();
			new MguException( "MGU ALERTA: Usuário criado com sucesso, porem não foi possivel enviar a senha para o e-mail \"" + usuario.getDcEmail() + "\" , favor entrar em contato com a central de serviços para solicitar a senha de acesso do usuário \"" + usuario.getDcUsername() + "\"\n\n" ).printStackTrace();
		}

		return null;
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

}
