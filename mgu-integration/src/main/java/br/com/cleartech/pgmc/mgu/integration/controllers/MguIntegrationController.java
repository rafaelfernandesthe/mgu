package br.com.cleartech.pgmc.mgu.integration.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.entities.Sistema;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;
import br.com.cleartech.pgmc.mgu.enums.CodigoMensagem;
import br.com.cleartech.pgmc.mgu.enums.TipoOperadora;
import br.com.cleartech.pgmc.mgu.exceptions.DynamicsException;
import br.com.cleartech.pgmc.mgu.exceptions.LdapException;
import br.com.cleartech.pgmc.mgu.exceptions.MguException;
import br.com.cleartech.pgmc.mgu.integration.services.LoginControllerService;
import br.com.cleartech.pgmc.mgu.integration.utils.ResponseUtils;
import br.com.cleartech.pgmc.mgu.integration.ws.requests.LogoutRequest;
import br.com.cleartech.pgmc.mgu.integration.ws.requests.PerfilOperadoraRequest;
import br.com.cleartech.pgmc.mgu.integration.ws.requests.PerfilRequest;
import br.com.cleartech.pgmc.mgu.integration.ws.requests.TipoOperadoraBean;
import br.com.cleartech.pgmc.mgu.integration.ws.requests.UsuarioMasterRequest;
import br.com.cleartech.pgmc.mgu.integration.ws.responses.MguResponse;
import br.com.cleartech.pgmc.mgu.services.AcessoSimultaneoService;
import br.com.cleartech.pgmc.mgu.services.LdapService;
import br.com.cleartech.pgmc.mgu.services.PerfilService;
import br.com.cleartech.pgmc.mgu.services.PrestadoraService;
import br.com.cleartech.pgmc.mgu.services.SistemaService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.utils.XmlUtils;

@RestController
@RequestMapping( "/mgu" )
public class MguIntegrationController {

	private static final Logger logger = LoggerFactory.getLogger( MguIntegrationController.class );

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private LoginControllerService loginControllerService;

	@Autowired
	private LdapService ldapService;

	@Autowired
	private PrestadoraService prestadoraService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AcessoSimultaneoService acessoSimultaneoService;

	@Autowired
	private SistemaService sistemaService;

	@PostMapping( "/sistema" )
	public MguResponse getPerfis( @RequestBody Sistema sistema ) throws Exception {
		logger.info( sistema.toString() );
		CodigoMensagem codigo = null;
		MguResponse mgu = new MguResponse();
		if ( sistema == null || sistema.getDcSistema() == null || sistema.getDcSistema().isEmpty() ) {
			codigo = CodigoMensagem.RETORNO_19;
		} else {
			List<Perfil> listPerfil = perfilService.findBySistema( sistema );
			if ( listPerfil == null || listPerfil.isEmpty() ) {
				codigo = CodigoMensagem.RETORNO_21;
			} else {
				mgu.setPerfis( new ArrayList<Perfil>() );
				for ( Perfil ite : listPerfil ) {
					Perfil i = new Perfil();
					i.setId( ite.getId() );
					i.setDcPerfil( ite.getDcPerfil() );
					mgu.addPerfil( i );
				}
				codigo = CodigoMensagem.RETORNO_20;
			}
		}
		mgu.setRetorno( codigo.getCodigo() );
		mgu.setDescricao( codigo.getDescricao() );
		return mgu;
	}

	@PostMapping( "/integracao" )
	public Object getLogin( @RequestBody Usuario usuarioRequest ) throws Exception {
		logger.info( usuarioRequest.toString() );
		return loginControllerService.processarLoginIntegracao( usuarioRequest );

	}

	@PostMapping( "/integracaoperfil" )
	public Object getLoginPerfisAgrupado( @RequestBody Usuario usuarioRequest ) throws Exception {
		logger.info( usuarioRequest.toString() );
		return loginControllerService.processarLoginIntegracaoPerfil( usuarioRequest );

	}

	@PostMapping( "/criarusuariomaster" )
	public Object criarUsuarioMaster( @RequestBody UsuarioMasterRequest usuarioMaster ) {
		logger.info( usuarioMaster.toString() );
		Object mguResponse;
		Usuario usuario;
		Boolean existeUsuario;
		if ( XmlUtils.contemCaracterEspecial( usuarioMaster.getUserName() ) ) {
			return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_27 );
		}
		try {
			existeUsuario = ldapService.existeUsuario( usuarioMaster.getUserName() );
		} catch ( Exception e ) {
			return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_33 );
		}
		if ( !existeUsuario ) {
			try {
				usuario = usuarioMaster.masterToUsuario();
				Prestadora prestadora = prestadoraService.findById( usuarioMaster.getIdPrestadora() );

				if ( prestadora == null ) {
					return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_6, "Prestadora não informada." );
				}

				usuario.setFlMaster( true );
				usuario.setFlBloqueio( BloqueioUsuario.BLOQUEADO_PRIMEIROACESSO );

				usuario.getPrestadoras().add( prestadora );

				Boolean valor = usuarioService.existsByUsernameIgnoreCase( usuario.getDcUsername() );
				if ( valor ) {
					return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_5 );
				}
				usuarioService.salvar( usuario, true );
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_2 );

			} catch ( LdapException e ) {
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_33, e.getMessage() );
			} catch ( DynamicsException e ) {
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_34, e.getMessage() );
			} catch ( MguException e ) {
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_6, e.getMessage() );
			} catch ( MessagingException e ) {
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_35, e.getMessage() );
			} catch ( Exception e ) {
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_6 );
			}
		} else {
			mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_5 );
		}
		return mguResponse;
	}

	@PostMapping( "/trocarusuariomaster" )
	public Object trocarUsuarioMaster( @RequestBody UsuarioMasterRequest usuarioMasterNovoRequest ) {
		logger.info( usuarioMasterNovoRequest.toString() );
		Object mguResponse;
		try {

			if ( XmlUtils.contemCaracterEspecial( usuarioMasterNovoRequest.getUserName() ) ) {
				return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_27 );
			}

			Prestadora prestadora = prestadoraService.findById( usuarioMasterNovoRequest.getIdPrestadora() );
			if ( prestadora == null ) {
				return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_6 );
			}

			try {

				if ( ldapService.existeUsuario( usuarioMasterNovoRequest.getUserName() ) || usuarioService.existsByUsernameIgnoreCase( usuarioMasterNovoRequest.getUserName() ) ) {
					return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_5 );
				}
			} catch ( LdapException e ) {
				return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_6 );
			}

			Usuario usuarioMasterNovo = usuarioMasterNovoRequest.masterToUsuario();
			usuarioMasterNovo.getPrestadoras().add( prestadora );
			usuarioMasterNovo.setFlMaster( true );
			usuarioMasterNovo.setFlBloqueio( BloqueioUsuario.BLOQUEADO_PRIMEIROACESSO );
			usuarioMasterNovo.setFlAprovado( true );

			usuarioService.substituirUsuarioMaster( usuarioMasterNovo, usuarioMasterNovoRequest.getUserNameAnterior() );

			mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_2 );

		} catch ( LdapException e ) {
			mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_33, e.getMessage() );
		} catch ( DynamicsException e ) {
			mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_34, e.getMessage() );
		} catch ( MguException e ) {
			mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_6, e.getMessage() );
		} catch ( MessagingException e ) {
			mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_2, e.getMessage() );
		} catch ( Exception e ) {
			mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_6 );
		}
		return mguResponse;
	}

	@PostMapping( "/aprovarusuario" )
	public Object aprovarUsuario( @RequestBody UsuarioMasterRequest usuarioMasterRequest ) throws Exception {
		logger.info( usuarioMasterRequest.toString() );
		Object mguResponse = null;
		try {
			Usuario usuario = usuarioService.findByUsername( usuarioMasterRequest.getUserName() );
			if ( usuario != null ) {
				usuario.setFlAprovado( true );
				usuarioService.alteraBloqueioUsuario( usuario, BloqueioUsuario.BLOQUEADO_NAO, "rest" );
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_3 );
			} else {
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_7 );
			}
		} catch ( MessagingException e ) {
			mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_3, e.getMessage() );
		}
		return mguResponse;
	}

	@PostMapping( "/logout" )
	public Object logout( @RequestBody LogoutRequest logoutRequest ) throws Exception {
		logger.info( logoutRequest.toString() );
		acessoSimultaneoService.deletarByUsername( logoutRequest.getUsuario() );

		MguResponse mguResponse = new MguResponse();
		mguResponse.setRetorno( 0 );
		mguResponse.setDescricao( "Usuário " + logoutRequest.getUsuario() + " efetuou o logout com sucesso!" );

		return mguResponse;
	}

	@PostMapping( "/consultaUsuario" )
	public Object consultaUsuario( @RequestBody UsuarioMasterRequest usuarioMasterRequest ) throws Exception {
		logger.info( usuarioMasterRequest.toString() );
		boolean usuarioExiste = usuarioService.existsByUsernameIgnoreCase( usuarioMasterRequest.getUserName() );
		MguResponse mguResponse = new MguResponse();
		if ( usuarioExiste ) {
			mguResponse.setRetorno( 0 );
			mguResponse.setDescricao( usuarioMasterRequest.getUserName() + " existe no banco de dados!" );
		} else {
			mguResponse.setRetorno( 1 );
			mguResponse.setDescricao( usuarioMasterRequest.getUserName() + " não existe no banco de dados!" );
		}
		return mguResponse;

	}

	@PostMapping( "/criarperfil" )
	public Object criarPerfil( @RequestBody PerfilRequest perfilRequest ) throws Exception {
		logger.info( perfilRequest.toString() );
		Sistema sistema = sistemaService.findByDcSistemaIgnoreCase( perfilRequest.getSistema().getNome() );
		if ( sistema != null ) {
			perfilService.criarPerfil( perfilRequest.getNome(), sistema );
			return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_10 );
		} else {
			return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_19 );
		}
	}

	@PostMapping( "/criarperfiloperadora" )
	public Object criarPerfilOperadora( @RequestBody PerfilOperadoraRequest perfilOperadoraRequest ) throws Exception {
		logger.info( perfilOperadoraRequest.toString() );
		Sistema sistema = sistemaService.findByDcSistemaIgnoreCase( perfilOperadoraRequest.getSistema().getNome() );
		if ( sistema != null ) {
			List<TipoOperadora> tipoOperadoras = new ArrayList<TipoOperadora>();
			for ( TipoOperadoraBean tipo : perfilOperadoraRequest.getTipoOperadoras() ) {
				tipoOperadoras.add( TipoOperadora.valueOf( tipo.getNome() ) );
			}
			Perfil perfil = perfilService.criarPerfiOperadora( perfilOperadoraRequest.getNome(), sistema, tipoOperadoras );
			return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_10, perfil.getId() );
		} else {
			return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_19 );
		}

	}

	@PostMapping( "/deletarperfil" )
	public Object deletarPerfil( @RequestBody PerfilRequest perfilRequest ) throws Exception {
		logger.info( "/deletarperfil -> deletarperfil(): " + perfilRequest.toString() );
		boolean deletado = perfilService.deletarPerfil( perfilRequest.getNome(), perfilRequest.getSistema().getNome() );
		if ( deletado ) {
			return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_11 );
		} else {
			return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_13 );
		}

	}

}
