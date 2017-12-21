package br.com.cleartech.pgmc.mgu.configs;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.com.cleartech.pgmc.mgu.services.ParametrizacaoService;

@Configuration
public class MailConfig {

	@Autowired
	private ParametrizacaoService parametrizacaoService;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	
		mailSender.setHost( "smtp.cleartech.com.br" );
		mailSender.setPort( 25 );
		mailSender.setUsername( "svc_no_replys" );
		mailSender.setPassword( "CTECH@noreply" );
//		
//		mailSender.setHost( "smtp.gmail.com" );
//		mailSender.setPort( 587 );
//		mailSender.setUsername( "pedrotomecm@gmail.com" );
//		mailSender.setPassword( "ptcm1230" );

		Properties props = mailSender.getJavaMailProperties();
		props.put( "mail.transport.protocol", "smtp" );
		props.put( "mail.smtp.auth", "true" );
		props.put( "mail.smtp.starttls.enable", "true" );
		// props.put( "mail.debug", "true" );

		return mailSender;
	}
}
