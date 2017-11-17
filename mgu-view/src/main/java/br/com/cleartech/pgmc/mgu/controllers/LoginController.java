package br.com.cleartech.pgmc.mgu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@GetMapping( "/login" )
	public ModelAndView login() {
		ModelAndView model = new ModelAndView( "/login.html" );

		return model;
	}

	@GetMapping( "/login-error" )
	public ModelAndView erroLogin() {
		ModelAndView model = new ModelAndView( "/login.html" );
		model.addObject( "error", true );
		return model;
	}

}