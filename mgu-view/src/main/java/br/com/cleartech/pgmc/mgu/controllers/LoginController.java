package br.com.cleartech.pgmc.mgu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.cleartech.pgmc.mgu.utils.MappedViews;

@Controller
public class LoginController {

	@GetMapping( "/login" )
	public String login() {
		return MappedViews.LOGIN.getPath();
	}

	@GetMapping( "/login-error" )
	public String erroLogin( Model model ) {
		model.addAttribute( "error", true );
		return MappedViews.LOGIN.getPath();

	}
	
}