package br.com.cleartech.pgmc.mgu.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import br.com.cleartech.pgmc.mgu.enums.ParametrizacaoEnum;
import br.com.cleartech.pgmc.mgu.services.ParametrizacaoService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger( WebSecurityConfig.class );
	
	@Autowired
	private ParametrizacaoService parametrizacaoService;

	@Bean
	public BaseLdapPathContextSource ldapContextSource() {
		LdapContextSource bean = new LdapContextSource();
		String host = parametrizacaoService.findByDcParametro( ParametrizacaoEnum.LDAP_HOST.getDcParametro() );
		String port = parametrizacaoService.findByDcParametro( ParametrizacaoEnum.LDAP_PORT.getDcParametro() );
		String ldapUrl = String.format( "ldap://%s:%s", host, port );
		LOGGER.info( "LDAP: {}", ldapUrl );
		bean.setUrl( ldapUrl );
		bean.setBase( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.LDAP_ROOT.getDcParametro() ) );
		bean.setUserDn( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.LDAP_MANAGER_LOGIN.getDcParametro() ) );
		bean.setPassword( parametrizacaoService.findByDcParametro( ParametrizacaoEnum.LDAP_MANAGER_PASSWORD.getDcParametro() ) );
		return bean;
	}

	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		//@formatter:off
        http
            .authorizeRequests()
                .antMatchers("/resources/js/*","/resources/img/*","/resources/img/login/*",
                			"/resources/css/*").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
	            .loginPage("/login").permitAll()
            	.defaultSuccessUrl( "/home" )
            	.failureHandler( new SimpleUrlAuthenticationFailureHandler("/login") )
                .and()
            .logout()
            	.logoutSuccessUrl( "/login" ).permitAll()
            	.logoutSuccessHandler( new MguLogoutSuccessHandler() )
            ;
    	//@formatter:on
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate( ldapContextSource() );
	}

	// @Bean
	// public TransactionAwareContextSourceProxy
	// transactionAwareContextSourceProxy() {
	// return new TransactionAwareContextSourceProxy( ldapContextSource() );
	// }

	// @Bean
	// public ContextSourceTransactionManager transactionManager() {
	// ContextSourceTransactionManager transactionManager = new
	// ContextSourceTransactionManager();
	// transactionManager.setRenamingStrategy( new
	// DefaultTempEntryRenamingStrategy() );
	// transactionManager.setContextSource( ldapContextSource() );
	// return transactionManager;
	// }

	// @Bean
	// public TransactionProxyFactoryBean dataAcessObject() {
	// TransactionProxyFactoryBean transactionFactory = new
	// TransactionProxyFactoryBean();
	// transactionFactory.setTarget( ldapService() );
	// transactionFactory.setTransactionManager( transactionManager() );
	// // Properties prop = new Properties();
	// // prop.put( "*", "PROPAGATION_REQUIRED" );
	// //
	// // transactionFactory.setTransactionAttributes( prop );
	// transactionFactory.setTransactionAttributeSource( new
	// AnnotationTransactionAttributeSource() );
	// return transactionFactory;
	// }
	//
	// @Bean
	// public LdapService ldapService() {
	// return new LdapServiceImpl();
	// }

}