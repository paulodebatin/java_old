package com.crud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
@Profile("dev")
public class WebSecurityDevConfig extends WebSecurityConfigurerAdapter {
	
	Logger logger = LoggerFactory.getLogger(WebSecurityDevConfig.class);
	
	@Autowired
	private Environment env; 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("Application description: " +  env.getProperty("application-description"));
		
		http.csrf().disable()
			.authorizeRequests().antMatchers("/**").permitAll();
	}

}
