package br.com.cleartech.pgmc.mgu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.cleartech.pgmc.mgu.formatters.NivelEscalonamentoConverter2Controller;
import br.com.cleartech.pgmc.mgu.formatters.NivelEscalonamentoConverter2Page;

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
//		registry.addViewController( "/home" ).setViewName( "/home" );
		super.addViewControllers( registry );
	}

	@Override
	public void addFormatters( FormatterRegistry registry ) {
		super.addFormatters( registry );
		registry.addConverter( new NivelEscalonamentoConverter2Page() );
		registry.addConverter( nivelEscalonamentoConverter2Controller() );
		// registry.addConverterFactory( autocompleteConverterFactory() );
	}

	@Bean
	public NivelEscalonamentoConverter2Controller nivelEscalonamentoConverter2Controller() {
		return new NivelEscalonamentoConverter2Controller();
	}

	// @Bean
	// public AutocompleteConverterFactory autocompleteConverterFactory() {
	// return new AutocompleteConverterFactory();
	// }
}