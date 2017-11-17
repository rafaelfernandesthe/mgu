package br.com.cleartech.pgmc.mgu.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cleartech.pgmc.mgu.config.EntityBaseController;
import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.services.GrupoPerfilService;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.utils.MguUtils;
import br.com.cleartech.pgmc.mgu.utils.ValueObject;

@Controller
@RequestMapping( "/usuarioCadastro" )
public class UsuarioCadastroController extends EntityBaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger( UsuarioCadastroController.class );

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private GrupoPerfilService grupoPerfilService;

	private List<ValueObject> grupoPerfisSelecionados;

	@InitBinder
	public void initBinder( WebDataBinder webDataBinder ) {}

	private Usuario usuario;

	@Override
	public void setThisModel() {
		thisModel = "/usuario/usuarioCadastro";
	}

	@GetMapping
	public String init( Usuario usuario, Model model ) {
		// List<GrupoPerfil> listaGrupoPerfilTotal = getGrupoPerfilList();
		// model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getVO2JSON(
		// listaGrupoPerfilTotal, "id", "noGrupoPerfil" ) );
		// model.addAttribute( "grupoPerfisTargetJSON", MguUtils.getVO2JSON( new
		// ArrayList<>(), "id", "noGrupoPerfil" ) );

		List<ValueObject> listaGrupoPerfilTotal = new ArrayList<>();
		listaGrupoPerfilTotal.add( new ValueObject( "1", "primeiro" ) );
		listaGrupoPerfilTotal.add( new ValueObject( "2", "segundo" ) );
		model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getJson( listaGrupoPerfilTotal ) );
		model.addAttribute( "grupoPerfisTargetJSON", MguUtils.getVO2JSON( new ArrayList<>(), "id", "noGrupoPerfil" ) );
		model.addAttribute( "grupoPerfisSelecionados", new ArrayList<>() );

		grupoPerfisSelecionados = new ArrayList<>();
		return thisModel;
	}

	@PostMapping( "/salvar" )
	public String salvar( Usuario usuario, BindingResult bindingResult, Model model ) {

		LOGGER.info(model.asMap()+"");
		LOGGER.info( usuario.getNmUsuario() );
		LOGGER.info( usuario.getDcUsername() );
		LOGGER.info( usuario.getGrupoPerfis() + "" );
		LOGGER.info( getGrupoPerfisSelecionados() + "" );
		for ( GrupoPerfil gp : usuario.getGrupoPerfis() )
			LOGGER.info( gp.getNoGrupoPerfil() );

		List<ValueObject> listaGrupoPerfilTotal = new ArrayList<>();
		listaGrupoPerfilTotal.add( new ValueObject( "1", "primeiro" ) );
		listaGrupoPerfilTotal.add( new ValueObject( "2", "segundo" ) );
		model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getJson( listaGrupoPerfilTotal ) );
		model.addAttribute( "grupoPerfisTargetJSON", MguUtils.getVO2JSON( new ArrayList<>(), "id", "noGrupoPerfil" ) );

		// List<GrupoPerfil> listaGrupoPerfilTotal = getGrupoPerfilList();
		// if ( bindingResult.hasErrors() ) {
		// listaGrupoPerfilTotal.removeAll( usuario.getGrupoPerfis() );
		// model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getVO2JSON(
		// listaGrupoPerfilTotal, "id", "noGrupoPerfil" ) );
		// model.addAttribute( "grupoPerfisTargetJSON", MguUtils.getVO2JSON(
		// usuario.getGrupoPerfis(), "id", "noGrupoPerfil" ) );
		//
		// bindingResult.addError( new ObjectError( "errox", "Errox!!" ) );
		// } else {
		// // usuarioService.salvar( usuario );
		// model.addAttribute( "grupoPerfisSourceJSON", MguUtils.getVO2JSON(
		// listaGrupoPerfilTotal, "id", "noGrupoPerfil" ) );
		// model.addAttribute( "grupoPerfisTargetJSON", MguUtils.getVO2JSON( new
		// ArrayList<>(), "id", "noGrupoPerfil" ) );
		// model.addAttribute( "msgSucesso", "Sucesso!" );
		// }

		return thisModel;
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

	public List<ValueObject> getGrupoPerfisSelecionados() {
		return grupoPerfisSelecionados;
	}

	public void setGrupoPerfisSelecionados( List<ValueObject> grupoPerfisSelecionados ) {
		this.grupoPerfisSelecionados = grupoPerfisSelecionados;
	}

}