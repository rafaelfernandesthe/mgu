package br.com.cleartech.pgmc.mgu.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class MguLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger( MguLogoutSuccessHandler.class );

	@Override
	public void onLogoutSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException, ServletException {

		String refererUrl = request.getHeader( "Referer" );
		LOGGER.info( "Logout from: " + refererUrl );

		super.onLogoutSuccess( request, response, authentication );
	}
}