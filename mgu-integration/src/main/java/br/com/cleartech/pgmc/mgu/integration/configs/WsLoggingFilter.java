package br.com.cleartech.pgmc.mgu.integration.configs;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order( 1 )
public class WsLoggingFilter implements Filter {

	/**
	 * Logging todo o request no WS para auditoria
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger( WsLoggingFilter.class );

	@Override
	public void init( final FilterConfig filterConfig ) throws ServletException {
		LOGGER.info( "Iniciando filtro de logging" );
	}

	@Override
	public void doFilter( final ServletRequest request, final ServletResponse response, final FilterChain chain ) throws IOException, ServletException {
		if ( request instanceof HttpServletRequest ) {
			LOGGER.info( String.format( "%s - %s", request.getRemoteHost(), ( (HttpServletRequest) request ).getRequestURL().toString() ) );
		}

		chain.doFilter( request, response );
	}

	@Override
	public void destroy() {
		LOGGER.warn( "Destruindo o filtro de logging" );
	}

}
