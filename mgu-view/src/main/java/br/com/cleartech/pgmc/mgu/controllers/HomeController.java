package br.com.cleartech.pgmc.mgu.controllers;

import br.com.cleartech.pgmc.mgu.utils.MappedViews;

public class HomeController {

	public String login() {

		return MappedViews.HOME.getPath();
	}

}