package br.com.cleartech.pgmc.mgu.integration.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
import br.com.cleartech.pgmc.mgu.exceptions.DynamicsException;
import br.com.cleartech.pgmc.mgu.exceptions.LdapException;
import br.com.cleartech.pgmc.mgu.exceptions.MguException;
import br.com.cleartech.pgmc.mgu.integration.services.LoginControllerService;
import br.com.cleartech.pgmc.mgu.integration.utils.ResponseUtils;
import br.com.cleartech.pgmc.mgu.integration.ws.requests.LogoutRequest;
import br.com.cleartech.pgmc.mgu.integration.ws.requests.PerfilOperadoraBean;
import br.com.cleartech.pgmc.mgu.integration.ws.requests.PerfilRequest;
import br.com.cleartech.pgmc.mgu.integration.ws.requests.UsuarioMasterRequest;
import br.com.cleartech.pgmc.mgu.integration.ws.responses.MguResponse;
import br.com.cleartech.pgmc.mgu.services.LdapService;
import br.com.cleartech.pgmc.mgu.services.PerfilService;
import br.com.cleartech.pgmc.mgu.services.PrestadoraService;
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

	@GetMapping( "/status" )
	public Object status() {
		return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_26 );
	}

	@PostMapping( "/sistema" )
	public MguResponse getPerfis( @RequestBody Sistema sistema ) throws Exception {
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

		return loginControllerService.processarLoginIntegracao( usuarioRequest );

	}

	@PostMapping( "/integracaoperfil" )
	public Object getLoginPerfisAgrupado( @RequestBody Usuario usuarioRequest ) throws Exception {

		return loginControllerService.processarLoginIntegracaoPerfil( usuarioRequest );

	}

	@PostMapping( "/criarusuariomaster" )
	public Object criarUsuarioMaster( @RequestBody UsuarioMasterRequest usuarioMaster ) {
		Object mguResponse = null;
		Usuario usuario = null;
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

				Boolean valor = usuarioService.existsByUsername( usuario.getDcUsername() );
				if ( valor ) {
					return ResponseUtils.mguResponse( CodigoMensagem.RETORNO_5 );
				}
				usuarioService.salvarUsuarioMaster( usuario );
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_2 );

			} catch ( LdapException e ) {
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_33, e.getMessage() );
			} catch ( DynamicsException e ) {
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_34, e.getMessage() );
			} catch ( MguException e ) {
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_6, e.getMessage() );
			} catch ( Exception e ) {
				mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_6 );
			}
		} else {
			mguResponse = ResponseUtils.mguResponse( CodigoMensagem.RETORNO_5 );
		}
		return mguResponse;
	}

	@PostMapping( "/trocarusuariomaster" )
	public String trocarUsuarioMaster( @RequestBody UsuarioMasterRequest usuarioMaster ) {

		return null;
	}

	@PostMapping( "/aprovarusuario" )
	public String aprovarUsuario( @RequestBody UsuarioMasterRequest usuarioMaster ) throws Exception {
		return null;
	}

	@PostMapping( "/logout" )
	public String logout( @RequestBody LogoutRequest logoutBean ) throws Exception {
		return null;

	}

	@PostMapping( "/consultaUsuario" )
	public String consultaUsuario( @RequestBody UsuarioMasterRequest usuarioMaster ) throws Exception {
		return null;

	}

	@PostMapping( "/criarperfil" )
	public String criarPerfil( @RequestBody PerfilRequest perfil ) throws Exception {
		return null;

	}

	/**
	 * Cria um perfil com os tipos de operadoras definidos
	 * 
	 * @param perfilOperadora
	 * @return XML
	 * @throws Exception
	 */
	@PostMapping( "/criarperfiloperadora" )
	public String criarPerfilOperadora( @RequestBody PerfilOperadoraBean perfilOperadora ) throws Exception {
		return null;

	}

	@PostMapping( "/deletarperfil" )
	public String deletarPerfil( @RequestBody PerfilRequest perfil ) throws Exception {
		return null;

	}

	private static String retornaMensagem( CodigoMensagem codigo, String complemento ) {
		StringBuilder ret = new StringBuilder();
		ret.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
		ret.append( "<mgu><retorno>" );
		ret.append( codigo.getCodigo() );
		ret.append( "</retorno><descricao>" );
		// ret.append( XmlUtils.cdataWrapper( codigo.getDescricao() + "\n" +
		// complemento ) );
		ret.append( "</descricao></mgu>" );
		return ret.toString();
	}

	private static String retornaMensagemIdPerfil( CodigoMensagem codigo, Long idPerfil ) {
		StringBuilder ret = new StringBuilder();
		ret.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
		ret.append( "<mgu><retorno>" );
		ret.append( codigo.getCodigo() );
		ret.append( "</retorno><dados>" );
		// ret.append( XmlUtils.cdataWrapper( codigo.getDescricao() ) );
		ret.append( "</dados><idPerfil>" );
		ret.append( idPerfil );
		ret.append( "</idPerfil></mgu>" );
		return ret.toString();
	}

}
