package br.com.cleartech.pgmc.mgu.configs;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Order( 1 )
public class WebLoggingFilter implements Filter {

	private String url;
	private String queryString;
	private String user;

	/**
	 * Logging todo o request da aplicação para auditoria
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger( WebLoggingFilter.class );

	@Override
	public void init( final FilterConfig filterConfig ) throws ServletException {
		LOGGER.info( "Iniciando filtro de logging" );
	}

	@Override
	public void doFilter( final ServletRequest request, final ServletResponse response, final FilterChain chain ) throws IOException, ServletException {
		if ( request instanceof HttpServletRequest ) {
			String requestStr = "%s - %s - %s - %s";
			url = ( (HttpServletRequest) request ).getRequestURL().toString();

			if ( url != null && !url.contains( "." ) ) {
				queryString = ( (HttpServletRequest) request ).getQueryString();
				if ( SecurityContextHolder.getContext().getAuthentication() != null ) {
					user = SecurityContextHolder.getContext().getAuthentication().getName();
				}
				LOGGER.info( String.format( requestStr, request.getRemoteHost(), user, url, queryString ) );
			}
		}
		chain.doFilter( request, response );
	}

	@Override
	public void destroy() {
		LOGGER.warn( "Destruindo o filtro de logging" );
	}
}