package br.com.cleartech.pgmc.mgu.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.services.GrupoPerfilService;
import br.com.cleartech.pgmc.mgu.view.dtos.GrupoPerfilConsultaDTO;
import br.com.cleartech.pgmc.mgu.view.utils.MappedViews;

@Controller
@RequestMapping( "/grupoPerfilConsulta" )
public class GrupoPerfilConsultaController {

	@Autowired
	private GrupoPerfilService grupoPerfilService;

	@GetMapping
	public String init( Model model ) {
		model.addAttribute( "grupoPerfisJSON", new ArrayList<>() );
		model.addAttribute( "grupoPerfil", new GrupoPerfil() );
		return MappedViews.GRUPO_PERFIL_CONSULTA.getPath();
	}

	@GetMapping( "/s" )
	public String lista( GrupoPerfil grupoPerfil, Model model ) {
		grupoPerfil.setPrestadora( new Prestadora( 1630l ) );
		List<GrupoPerfil> lista = grupoPerfilService.findByPrestadoraAndNome( grupoPerfil );

		model.addAttribute( "grupoPerfis",lista );
		model.addAttribute( "grupoPerfisJSON", new GrupoPerfilConsultaDTO().getVOToJson( lista ) );
		return MappedViews.GRUPO_PERFIL_CONSULTA.getPath();
	}

}