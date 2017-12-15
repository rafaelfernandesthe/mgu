package br.com.cleartech.pgmc.mgu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.cleartech.pgmc.mgu.utils.MappedViews;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String login() {
		
		return MappedViews.HOME.getPath();
	}

}