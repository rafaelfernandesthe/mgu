package br.com.cleartech.pgmc.mgu.integration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
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

	}
}