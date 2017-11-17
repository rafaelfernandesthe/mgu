package br.com.cleartech.pgmc.mgu.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cleartech.pgmc.mgu.config.EntityBaseController;
import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.services.GrupoPerfilService;
import br.com.cleartech.pgmc.mgu.vo.GrupoPerfilConsultaVO;

@Controller
@RequestMapping( "/grupoPerfilConsulta" )
public class GrupoPerfilConsultaController extends EntityBaseController {

	@Autowired
	private GrupoPerfilService grupoPerfilService;

	@Override
	public void setThisModel() {
		this.thisModel = "/grupoPerfil/grupoPerfilConsulta.html";
	}

	@GetMapping
	public ModelAndView init( GrupoPerfil grupoPerfil ) {
		ModelAndView model = getThisModel();
		model.addObject( "grupoPerfisJSON", new ArrayList<>() );
		return model;
	}

	@GetMapping( "/s" )
	public ModelAndView lista( GrupoPerfil grupoPerfil, ModelAndView model ) {
		grupoPerfil.setPrestadora( new Prestadora( 1630l ) );
		List<GrupoPerfil> lista = grupoPerfilService.findByPrestadoraAndNome( grupoPerfil );

		model.addObject( "grupoPerfisJSON", new GrupoPerfilConsultaVO().getVOToJson( lista ) );
		return model;
	}

}