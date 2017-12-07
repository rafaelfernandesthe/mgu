package br.com.cleartech.pgmc.mgu.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

	@GetMapping( "/login" )
	public ModelAndView login() {
		ModelAndView model = new ModelAndView( "/login" );

		return model;
	}

	@GetMapping( "/login-error" )
	public ModelAndView erroLogin() {
		ModelAndView model = new ModelAndView( "/login" );
		model.addObject( "error", true );
		return model;
	}

}