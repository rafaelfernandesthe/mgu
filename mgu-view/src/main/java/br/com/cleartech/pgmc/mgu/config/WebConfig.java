package br.com.cleartech.pgmc.mgu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers( ResourceHandlerRegistry registry ) {
		registry.addResourceHandler( "/resources/**" ).addResourceLocations( "/resources/" );
	}

	@Override
	public void addViewControllers( ViewControllerRegistry registry ) {
		registry.addViewController( "/" ).setViewName( "/home" );
		registry.addViewController( "/home" ).setViewName( "/home" );

		super.addViewControllers( registry );
	}
}