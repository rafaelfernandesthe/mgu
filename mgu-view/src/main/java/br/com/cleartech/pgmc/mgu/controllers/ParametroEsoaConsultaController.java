package br.com.cleartech.pgmc.mgu.controllers;

import java.util.ArrayList;
import java.util.Calendar;
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

import br.com.cleartech.pgmc.mgu.entities.FiltroValor;
import br.com.cleartech.pgmc.mgu.entities.GrupoPrestadora;
import br.com.cleartech.pgmc.mgu.entities.ParametroSistema;
import br.com.cleartech.pgmc.mgu.repositories.GrupoPrestadoraRepository;
import br.com.cleartech.pgmc.mgu.services.ParametroSistemaService;
import br.com.cleartech.pgmc.mgu.services.TempoRespostaService;
import br.com.cleartech.pgmc.mgu.view.utils.MappedViews;
import br.com.cleartech.pgmc.mgu.view.utils.MguUtils;

@Controller
@RequestMapping( "/parametroEsoa" )
public class ParametroEsoaConsultaController {

	@Autowired
	private ParametroSistemaService parametroSistemaService;

	@Autowired
	private GrupoPrestadoraRepository grupoPrestadoraRepository;

	@Autowired
	private TempoRespostaService tempoRespostaService;

	@GetMapping
	public String init( Model model, HttpServletRequest request ) {
		model.addAttribute( "parametroSistema", new ParametroSistema() );
		model.addAttribute( "parametrosJson", new ArrayList<String>() );
		return MappedViews.PARAMETRO_ESOA_CONSULTA.getPath();
	}

	@GetMapping( "/s" )
	public String lista( @ModelAttribute( "parametroSistema" ) ParametroSistema parametroSistema, HttpServletRequest request, ModelMap model ) {
		Calendar inicio = Calendar.getInstance();

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

		logarTempoRespostaPesquisaParametros( parametroSistema.getGrupoPrestadora(), inicio );

		return MappedViews.PARAMETRO_ESOA_CONSULTA.getPath();
	}

	@ModelAttribute( "todosGrupos" )
	public List<GrupoPrestadora> todosGrupos() {
		return grupoPrestadoraRepository.findAllByOrderByNoGrupoPrestadoraAsc();
	}

	private void logarTempoRespostaPesquisaParametros( GrupoPrestadora param, Calendar inicio ) {

		final Integer SISTEMA_MGU = 2;
		final Integer FUNCIONALIDADE_LISTA_PARAMETROS = 5;
		final Integer FILTRO_GRUPO_PRESTADORA = 11;

		List<FiltroValor> filtroValores = new ArrayList<FiltroValor>();

		if ( param != null && param.getId() > 0 ) {
			FiltroValor filtroValor = new FiltroValor();
			filtroValor.setFiltroId( FILTRO_GRUPO_PRESTADORA );
			filtroValor.setValor( String.valueOf( param.getId() ) );
			filtroValores.add( filtroValor );
		}
		Calendar fim = Calendar.getInstance();
		tempoRespostaService.logger( SISTEMA_MGU, FUNCIONALIDADE_LISTA_PARAMETROS, inicio, fim, filtroValores );

	}
}
