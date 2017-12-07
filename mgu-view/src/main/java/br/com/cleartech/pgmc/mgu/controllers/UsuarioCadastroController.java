package br.com.cleartech.pgmc.mgu.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.services.GrupoPerfilService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.utils.MappedViews;
import br.com.cleartech.pgmc.mgu.utils.MguUtils;

@Controller
@RequestMapping( "/usuarioCadastro" )
public class UsuarioCadastroController {

	private static final Logger LOGGER = LoggerFactory.getLogger( UsuarioCadastroController.class );

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private GrupoPerfilService grupoPerfilService;

	private Usuario usuario;

	@GetMapping
	public String init( Usuario usuario, Model model ) {
		List<GrupoPerfil> listaGrupoPerfilTotal = getGrupoPerfilList();
		model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getVO2JSON( listaGrupoPerfilTotal, "id", "noGrupoPerfil" ) );
		model.addAttribute( "grupoPerfisTargetJSON", MguUtils.getJSON( new ArrayList<GrupoPerfil>() ) );

		return MappedViews.USUARIO_CADASTRO.getPath();
	}

	@PostMapping( "/salvar" )
	public String salvar( Usuario usuario, BindingResult bindingResult, Model model ) {

		LOGGER.info( model.asMap() + "" );
		LOGGER.info( usuario.getNmUsuario() );
		LOGGER.info( usuario.getDcUsername() );
		LOGGER.info( usuario.getGrupoPerfisIdList().toString() );

		if ( !bindingResult.hasErrors() ) {
			bindingResult.addError( new ObjectError( "errox", "Errox!!" ) );
			bindingResult.addError( new ObjectError( "erroy", "Erroy!!" ) );
			bindingResult.addError( new ObjectError( "erroz", ", erro tal !" ) );
		}

		// prepare
		List<GrupoPerfil> groupSelecteds = MguUtils.idListToGrupoPerfilList( usuario.getGrupoPerfisIdList() );
		usuario.setGrupoPerfis( groupSelecteds );

		if ( !bindingResult.hasErrors() ) {

			// usuarioService.salvar( usuario );
			return "redirect:/grupoPerfilConsulta" + MappedViews.SUCESS_PARAMETER.getPath();
		} else {
			List<GrupoPerfil> listaGrupoPerfilTotal = getGrupoPerfilList();
			listaGrupoPerfilTotal.removeAll( groupSelecteds );
			model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getVO2JSON( listaGrupoPerfilTotal, "id", "noGrupoPerfil" ) );

			groupSelecteds = grupoPerfilService.loadAllById( groupSelecteds );
			model.addAttribute( "grupoPerfisTargetJSON", MguUtils.getVO2JSON( groupSelecteds, "id", "noGrupoPerfil" ) );

			return MappedViews.USUARIO_CADASTRO.getPath();
		}
	}

	private List<GrupoPerfil> getGrupoPerfilList() {
		return grupoPerfilService.findByPrestadora( 1630l );
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario( Usuario usuario ) {
		this.usuario = usuario;
	}

}