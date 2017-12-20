package br.com.cleartech.pgmc.mgu.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger( WebSecurityConfig.class );

	String ldapUrl = "ldap://10.100.103.150:389";
	String ldapRoot = "dc=pgmchom";
	String ldapPort = "389";
	String ldapManagerDn = "cn=admin,dc=pgmchom";
	String ldapManagerPassword = "1pgmchom55";
	String ldapProfile = "cleartech";

	@Bean
	public BaseLdapPathContextSource ldapContextSource() {
		LOGGER.info( "LDAP: {}", ldapUrl );
		LdapContextSource bean = new LdapContextSource();
		bean.setUrl( ldapUrl );
		bean.setBase( ldapRoot );
		bean.setUserDn( ldapManagerDn );
		bean.setPassword( ldapManagerPassword );
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