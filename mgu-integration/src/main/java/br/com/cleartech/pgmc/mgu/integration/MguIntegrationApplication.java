package br.com.cleartech.pgmc.mgu.integration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.cleartech.pgmc.mgu.integration.configs.WsLoggingFilter;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan( "br.com.cleartech.pgmc.mgu" )
public class MguIntegrationApplication implements WebApplicationInitializer {

	@Override
	public void onStartup( ServletContext servletContext ) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register( MguIntegrationApplication.class );
		ctx.setServletContext( servletContext );
		Dynamic dynamic = servletContext.addServlet( "dispatcher", new DispatcherServlet( ctx ) );
		dynamic.addMapping( "/*" );
		dynamic.setLoadOnStartup( 1 );

		FilterRegistration.Dynamic loggingFilter = servletContext.addFilter( "wsLoggingFilter", new WsLoggingFilter() );
		loggingFilter.setAsyncSupported( true );
		loggingFilter.addMappingForServletNames( dispatcherTypes( true ), false, "dispatcher" );
	}

	private EnumSet<DispatcherType> dispatcherTypes( boolean asyncSupported ) {
		return ( asyncSupported ? EnumSet.of( DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC ) : EnumSet.of( DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE ) );
	}
}