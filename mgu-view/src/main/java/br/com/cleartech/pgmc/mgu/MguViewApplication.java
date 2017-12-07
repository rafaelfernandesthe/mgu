package br.com.cleartech.pgmc.mgu;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class MguViewApplication implements WebApplicationInitializer {

	@Override
	public void onStartup( ServletContext servletContext ) throws ServletException {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.scan( MguViewApplication.class.getPackage().getName() );

		servletContext.addListener( new ContextLoaderListener( applicationContext ) );
		servletContext.addListener( new RequestContextListener() );

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet( "dispatcher", dispatcherServlet( applicationContext ) );
		dispatcher.setAsyncSupported( true );
		dispatcher.setLoadOnStartup( 0 );
		dispatcher.addMapping( "/*" );

		FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter( "characterEncodingFilter", characterEncodingFilter() );
		characterEncodingFilter.setAsyncSupported( true );
		characterEncodingFilter.addMappingForServletNames( dispatcherTypes( true ), false, "dispatcher" );

	}

	private DispatcherServlet dispatcherServlet( WebApplicationContext applicationContext ) {
		return new DispatcherServlet( applicationContext );
	}

	private CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();

		characterEncodingFilter.setEncoding( "UTF-8" );
		characterEncodingFilter.setForceEncoding( true );

		return characterEncodingFilter;
	}

	private EnumSet<DispatcherType> dispatcherTypes( boolean asyncSupported ) {
		return ( asyncSupported ? EnumSet.of( DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC ) : EnumSet.of( DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE ) );
	}
}