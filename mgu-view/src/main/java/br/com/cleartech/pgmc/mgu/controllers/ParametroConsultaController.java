package br.com.cleartech.pgmc.mgu.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cleartech.pgmc.mgu.configs.MguAuthenticationProvider.MguUserDetails;
import br.com.cleartech.pgmc.mgu.entities.GrupoPrestadora;
import br.com.cleartech.pgmc.mgu.entities.ParametroSistema;
import br.com.cleartech.pgmc.mgu.repositories.GrupoPrestadoraRepository;
import br.com.cleartech.pgmc.mgu.services.ParametroSistemaService;
import br.com.cleartech.pgmc.mgu.view.utils.MappedViews;
import br.com.cleartech.pgmc.mgu.view.utils.MguUtils;

@Controller
@RequestMapping( "/parametro" )
public class ParametroConsultaController {

	@Autowired
	private ParametroSistemaService parametroSistemaService;

	@Autowired
	private GrupoPrestadoraRepository grupoPrestadoraRepository;

	@GetMapping
	public String init( Model model, HttpServletRequest request ) {

		ParametroSistema parametroSistema = parametroSistemaService.findByGrupoPrestadoraId( MguUtils.getUsuarioLogado().getIdGrupoPrestadora() );
		if ( parametroSistema == null ) {
			model.addAttribute( "parametroSistema", new ParametroSistema() );
		} else {
			model.addAttribute( "parametroSistema", parametroSistema );
		}

		MguUserDetails usuario = MguUtils.getUsuarioLogado();
		Map<Long, String> listPrestadora = new LinkedHashMap<Long, String>();
		listPrestadora.put( usuario.getIdGrupoPrestadora(), usuario.getGrupoPrestadora() );
		model.addAttribute( "listaGrupoPrestadora", listPrestadora );

		return MappedViews.PARAMETRO_CONSULTA.getPath();
	}

	@RequestMapping( value = "/salvar", method = POST )
	public String salvar( HttpServletRequest request, ModelMap model, @ModelAttribute( "parametroSistema" ) ParametroSistema parametroSistema, BindingResult result ) {

		if ( !validaParametroSistema( parametroSistema, result ) ) {
			MguUserDetails usuario = MguUtils.getUsuarioLogado();
			Map<Long, String> listPrestadora = new LinkedHashMap<Long, String>();
			listPrestadora.put( usuario.getIdGrupoPrestadora(), usuario.getGrupoPrestadora() );
			model.addAttribute( "listaGrupoPrestadora", listPrestadora );
			return MappedViews.PARAMETRO_CONSULTA.getPath();
		}

		GrupoPrestadora grupoPrestadora = grupoPrestadoraRepository.findOne( parametroSistema.getGrupoPrestadora().getId() );
		parametroSistema.setGrupoPrestadora( grupoPrestadora );
		parametroSistemaService.salvar( parametroSistema );

		return "redirect:/parametro" + String.format( MappedViews.SUCESSO_PARAMETRO_NOVO.getPath(), "Parâmetro salvo com sucesso!" );
	}

	private boolean validaParametroSistema( ParametroSistema parametroSistema, BindingResult result ) {
		boolean valido = true;

		if ( parametroSistema.getFlPrazoExpirarSenha() != null && parametroSistema.getFlPrazoExpirarSenha() == 0 && parametroSistema.getPrazoExpirarSenha() == null ) {
			ObjectError error = new ObjectError( "prazoExpirarSenha", "Prazo para expirar Senha é obrigatório." );
			result.addError( error );
			valido = false;
		}

		if ( parametroSistema.getFlBloquearInatividade() != null && parametroSistema.getFlBloquearInatividade() == 0 && parametroSistema.getBloquearInatividade() == null ) {
			ObjectError error = new ObjectError( "bloquearInatividade", "Bloquear usuário por inatividade é obrigatório." );
			result.addError( error );
			valido = false;
		}

		if ( parametroSistema.getFlQtdErrarSenha() != null && parametroSistema.getFlQtdErrarSenha() == 0 && parametroSistema.getQtdErrarSenha() == null ) {
			ObjectError error = new ObjectError( "qtdErrarSenha", "Quantidade de vezes que o usuário pode errar a senha é obrigatório." );
			result.addError( error );
			valido = false;
		}

		if ( parametroSistema.getGrupoPrestadora() == null || parametroSistema.getGrupoPrestadora().getId() == null ) {
			ObjectError error = new ObjectError( "prestadora.id", "Grupo Prestadora é obrigatório." );
			result.addError( error );
			valido = false;
		}

		return valido;
	}
}
