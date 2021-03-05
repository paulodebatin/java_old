package com.crud.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "authEntityManagerFactory", 
		basePackages = {"com.crud.auth.domain.repository" },
		transactionManagerRef = "authTransactionManager")
public class DbAuthConfig {

	@Bean(name = "authDataSource")
	@ConfigurationProperties(prefix = "auth.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "authEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean authEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("authDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.crud.auth.domain.model").persistenceUnit("auth").build();
	}

	@Bean(name = "authTransactionManager")
	public PlatformTransactionManager authTransactionManager(
			@Qualifier("authEntityManagerFactory") EntityManagerFactory authEntityManagerFactory) {
		return new JpaTransactionManager(authEntityManagerFactory);
	}

}
