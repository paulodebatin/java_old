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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.crud.filter.JWTAuthorizationFilter;


@EnableWebSecurity
@Configuration
@Profile("prod")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Autowired
	private Environment env; 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("Application description: " +  env.getProperty("application-description"));
		
		http.csrf().disable()
			.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests().antMatchers("/pessoas/login*","/swagger-ui*","/swagger-ui/*", "/api-docs*", "/api-docs/swagger-config*").permitAll()
			.anyRequest().authenticated();
	}

}
