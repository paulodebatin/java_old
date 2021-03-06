package com.edusoft.mentorweb.financeiro.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.edusoft")
@EntityScan("com.edusoft")
@EnableJpaRepositories(basePackages = {"com.edusoft"})
@EnableTransactionManagement
public class ServiceAutoConfiguration {

}
