package br.com.cleartech.pgmc.mgu.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
            	.failureUrl( "/login-error" ).permitAll()
                .and()
            .logout()
            	.logoutSuccessUrl( "/login" ).permitAll()
            ;
    	//@formatter:on
	}

	@Autowired
	public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {

		// auth.inMemoryAuthentication()
		// .withUser("user1").password("pass1").roles("USER");

		//@formatter:off
		auth.ldapAuthentication()
			.userDnPatterns( "cn={0},ou=usuarios,ou="+ldapProfile )
			.groupSearchBase("ou=grupos,ou="+ldapProfile )
		.contextSource( ldapContextSource() )
    	.passwordCompare()
    		.passwordEncoder( new  Md5PasswordEncoder() )
    		.passwordAttribute( "userPassword" )
    	;
    	//@formatter:on
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate( ldapContextSource() );
	}

}