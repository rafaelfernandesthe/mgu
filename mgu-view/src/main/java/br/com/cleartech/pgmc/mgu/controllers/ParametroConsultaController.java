package br.com.cleartech.pgmc.mgu.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cleartech.pgmc.mgu.entities.ParametroSistema;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.utils.MappedViews;

@Controller
@RequestMapping( "/parametro" )
public class ParametroConsultaController {

	
	@GetMapping
	public String init(Usuario usuario, ParametroSistema parametroSistema) {

		return MappedViews.PARAMETRO_CONSULTA.getPath();
	}
}
