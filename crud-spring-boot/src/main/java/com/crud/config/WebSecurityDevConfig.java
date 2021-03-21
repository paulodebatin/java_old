package com.crud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import com.crud.filter.actuator.ContentTraceFilter;
import com.crud.filter.actuator.PrincipalTraceFilter;

@EnableWebSecurity
@Configuration
@Profile("dev")
@ConditionalOnProperty(prefix = "management.trace.http", name = "enabled", matchIfMissing = true)
public class WebSecurityDevConfig extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(WebSecurityDevConfig.class);

    private HttpTraceFilter httpTraceFilter;
    private ContentTraceFilter contentTraceFilter;
    private PrincipalTraceFilter principalTraceFilter;

    public WebSecurityDevConfig(HttpTraceFilter httpTraceFilter, ContentTraceFilter contentTraceFilter, PrincipalTraceFilter principalTraceFilter) {
        this.httpTraceFilter = httpTraceFilter;
        this.contentTraceFilter = contentTraceFilter;
        this.principalTraceFilter = principalTraceFilter;
    }

    @Autowired
    private Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Application description: " + env.getProperty("application-description"));

        http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();

        http.addFilterBefore(contentTraceFilter, SecurityContextPersistenceFilter.class)
            .addFilterAfter(httpTraceFilter, SecurityContextPersistenceFilter.class)
            .addFilterAfter(principalTraceFilter, FilterSecurityInterceptor.class);
        
        

    }

}
