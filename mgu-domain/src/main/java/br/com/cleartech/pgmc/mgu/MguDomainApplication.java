package br.com.cleartech.pgmc.mgu;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan( "br.com.cleartech.pgmc" )
@EnableJpaRepositories( "br.com.cleartech.pgmc" )
public class MguDomainApplication {

	@Bean
	@Autowired
	public DataSource dataSource() throws NamingException {
		JndiTemplate jndiTemplate = new JndiTemplate();
		return (DataSource) jndiTemplate.lookup( "java:/MguDS-CLEARTECH" );
	}

	@Bean
	@Autowired
	public EntityManagerFactory entityManagerFactory( DataSource dataSource ) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl( true );

		Properties jpaProperties = new Properties();
		jpaProperties.setProperty( "org.hibernate.envers.revision_field_name", "CD_REVISAO" );
		jpaProperties.setProperty( "org.hibernate.envers.revision_type_field_name", "CD_TIPO_REVISAO" );
		jpaProperties.setProperty( "hibernate.dialect", "org.hibernate.dialect.OracleDialect" );
		jpaProperties.setProperty( "hibernate.show_sql", "false" );
		jpaProperties.setProperty( "hibernate.format_sql", "true" );
		jpaProperties.setProperty( "hibernate.hbm2ddl.auto", "none" );

		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter( vendorAdapter );
		localContainerEntityManagerFactoryBean.setPackagesToScan( "br.com.cleartech.pgmc" );
		localContainerEntityManagerFactoryBean.setDataSource( dataSource );
		localContainerEntityManagerFactoryBean.setJpaProperties( jpaProperties );
		localContainerEntityManagerFactoryBean.afterPropertiesSet();
		localContainerEntityManagerFactoryBean.setPersistenceUnitName( "pgmcDS" );

		return localContainerEntityManagerFactoryBean.getObject();
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager( EntityManagerFactory entityManagerFactory ) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory( entityManagerFactory );
		return jpaTransactionManager;
	}
}
