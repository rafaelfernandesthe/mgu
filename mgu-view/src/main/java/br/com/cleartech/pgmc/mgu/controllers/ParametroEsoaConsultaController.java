package br.com.cleartech.pgmc.mgu.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cleartech.pgmc.mgu.entities.GrupoPrestadora;
import br.com.cleartech.pgmc.mgu.entities.ParametroSistema;
import br.com.cleartech.pgmc.mgu.repositories.GrupoPrestadoraRepository;
import br.com.cleartech.pgmc.mgu.services.ParametroSistemaService;
import br.com.cleartech.pgmc.mgu.view.utils.MappedViews;
import br.com.cleartech.pgmc.mgu.view.utils.MguUtils;

@Controller
@RequestMapping( "/parametroEsoa" )
public class ParametroEsoaConsultaController {

	@Autowired
	private ParametroSistemaService parametroSistemaService;

	@Autowired
	private GrupoPrestadoraRepository grupoPrestadoraRepository;

	@GetMapping
	public String init( Model model, HttpServletRequest request ) {
		model.addAttribute( "parametroSistema", new ParametroSistema() );
		model.addAttribute( "parametrosJson", new ArrayList<String>() );
		return MappedViews.PARAMETRO_ESOA_CONSULTA.getPath();
	}

	@GetMapping( "/s" )
	public String lista( @ModelAttribute( "parametroSistema" ) ParametroSistema parametroSistema, HttpServletRequest request, ModelMap model ) {

		List<ParametroSistema> resultList = new ArrayList<ParametroSistema>();
		if ( parametroSistema.getGrupoPrestadora() != null ) {
			ParametroSistema result = parametroSistemaService.findByGrupoPrestadoraId( parametroSistema.getGrupoPrestadora().getId() );
			if ( result != null ) {
				parametroSistema.getGrupoPrestadora().setPrestadoras( null );
				result.setGrupoPrestadora( parametroSistema.getGrupoPrestadora() );
				resultList.add( result );
			}
		} else {
			resultList = parametroSistemaService.findAll();
			for ( ParametroSistema p : resultList ) {
				Hibernate.initialize( p.getGrupoPrestadora() );
				p.getGrupoPrestadora().setPrestadoras( null );
			}
		}

		model.addAttribute( "parametrosJson", MguUtils.getJSON( resultList ) );
		return MappedViews.PARAMETRO_ESOA_CONSULTA.getPath();
	}

	@ModelAttribute( "todosGrupos" )
	public List<GrupoPrestadora> todosGrupos() {
		return grupoPrestadoraRepository.findAllByOrderByNoGrupoPrestadoraAsc();
	}

}
