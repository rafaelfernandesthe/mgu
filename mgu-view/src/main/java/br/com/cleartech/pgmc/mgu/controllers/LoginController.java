package br.com.cleartech.pgmc.mgu.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.cleartech.pgmc.mgu.utils.MappedViews;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger( LoginController.class );

	@GetMapping( "/login" )
	public String login( Model model, HttpServletRequest request ) {
		Object loginError = request.getSession().getAttribute( WebAttributes.AUTHENTICATION_EXCEPTION );
		if ( loginError != null ) {
			AuthenticationException loginException = (AuthenticationException) loginError;
			logger.info( loginException.getMessage() );
			model.addAttribute( "loginError", loginException.getMessage() );
			request.getSession().removeAttribute( WebAttributes.AUTHENTICATION_EXCEPTION );
		}

		return MappedViews.LOGIN.getPath();
	}

}