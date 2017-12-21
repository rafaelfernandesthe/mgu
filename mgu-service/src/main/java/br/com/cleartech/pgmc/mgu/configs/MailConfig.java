package br.com.cleartech.pgmc.mgu.configs;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.com.cleartech.pgmc.mgu.enums.ParametrizacaoEnum;
import br.com.cleartech.pgmc.mgu.services.ParametrizacaoService;

@Configuration
public class MailConfig {

	@Autowired
	private ParametrizacaoService parametrizacaoService;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	
		mailSender.setHost( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.MAIL_HOST.getDcParametro() ) );
		mailSender.setPort( Integer.valueOf( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.MAIL_PORT.getDcParametro() ) ) );
		mailSender.setUsername( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.MAIL_USERNAME.getDcParametro() ) );
		mailSender.setPassword( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.MAIL_PASSWORD.getDcParametro() ) );

		Properties props = mailSender.getJavaMailProperties();
		props.put( "mail.transport.protocol", "smtp" );
		props.put( "mail.smtp.auth", "true" );
		props.put( "mail.smtp.starttls.enable", "true" );
		// props.put( "mail.debug", "true" );

		return mailSender;
	}
}
