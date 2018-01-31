package br.com.cleartech.pgmc.mgu.config;

import java.util.Locale;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import br.com.cleartech.pgmc.mgu.view.converters.GrupoPrestadoraConverter2Controller;
import br.com.cleartech.pgmc.mgu.view.converters.GrupoPrestadoraConverter2Page;
import br.com.cleartech.pgmc.mgu.view.converters.NivelEscalonamentoConverter2Controller;
import br.com.cleartech.pgmc.mgu.view.converters.NivelEscalonamentoConverter2Page;
import br.com.cleartech.pgmc.mgu.view.converters.UsuarioConverter2Controller;
import br.com.cleartech.pgmc.mgu.view.converters.UsuarioConverter2Page;

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

	@Override
	public void addFormatters( FormatterRegistry registry ) {
		super.addFormatters( registry );
		registry.addConverter( new NivelEscalonamentoConverter2Page() );
		registry.addConverter( nivelEscalonamentoConverter2Controller() );
		registry.addConverter( new UsuarioConverter2Page() );
		registry.addConverter( usuarioConverter2Controller() );
		registry.addConverter( new GrupoPrestadoraConverter2Page() );
		registry.addConverter( grupoPrestadoraConverter2Controller() );
		// registry.addConverterFactory( autocompleteConverterFactory() );
	}

	@Bean
	public NivelEscalonamentoConverter2Controller nivelEscalonamentoConverter2Controller() {
		return new NivelEscalonamentoConverter2Controller();
	}

	@Bean
	public UsuarioConverter2Controller usuarioConverter2Controller() {
		return new UsuarioConverter2Controller();
	}

	@Bean
	public GrupoPrestadoraConverter2Controller grupoPrestadoraConverter2Controller() {
		return new GrupoPrestadoraConverter2Controller();
	}

	// @Bean
	// public AutocompleteConverterFactory autocompleteConverterFactory() {
	// return new AutocompleteConverterFactory();
	// }

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver( new Locale( "pt", "BR" ) );
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();

		bundle.setBasename( "classpath:Mensagens" );
		bundle.setDefaultEncoding( "UTF-8" );
		bundle.setCacheSeconds( 1 );

		return bundle;
	}

	@Bean
	public RestTemplateBuilder restTemplate() {
		return new RestTemplateBuilder();
	}

}