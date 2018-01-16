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

		String usernameMail = parametrizacaoService.findByDcParametro( ParametrizacaoEnum.MAIL_USERNAME.getDcParametro() );
		String passwordMail = parametrizacaoService.findByDcParametro( ParametrizacaoEnum.MAIL_PASSWORD.getDcParametro() );
		boolean auth = false;
		if ( usernameMail != null && passwordMail != null ) {
			auth = true;
			mailSender.setUsername( usernameMail );
			mailSender.setPassword( passwordMail );
		}

		Properties props = mailSender.getJavaMailProperties();
		props.put( "mail.smtp.auth", String.valueOf( auth ) );
		//props.put( "mail.transport.protocol", "smtp" );
		//props.put( "mail.smtp.starttls.enable", "true" );
		// props.put( "mail.debug", "true" );

		return mailSender;
	}
}
