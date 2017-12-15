package br.com.cleartech.pgmc.mgu.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class ThymeleafConfig {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public ViewResolver thymeleafViewResolver( ITemplateEngine templateEngine ) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

		viewResolver.setOrder( 1 );
		viewResolver.setTemplateEngine( templateEngine );
		viewResolver.setCharacterEncoding( "UTF-8" );
		// viewResolver.setViewNames( new String[] { "*.html" } );

		return viewResolver;
	}

	@Bean
	public ITemplateEngine templateEngine( ITemplateResolver templateResolver ) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();

		templateEngine.setTemplateResolver( templateResolver );
		templateEngine.setEnableSpringELCompiler( true );
		templateEngine.addDialect( new LayoutDialect() );

		Set<IDialect> additionalList = new HashSet<>();
		additionalList.add( new SpringSecurityDialect() );
		templateEngine.setAdditionalDialects( additionalList );

		return templateEngine;
	}

	@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

		templateResolver.setApplicationContext( this.applicationContext );
		templateResolver.setTemplateMode( TemplateMode.HTML );
		templateResolver.setPrefix( "/pages" );
		templateResolver.setSuffix( ".html" );
		templateResolver.setCharacterEncoding( "UTF-8" );
		templateResolver.setCacheable( false );

		return templateResolver;
	}
}
