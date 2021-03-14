package com.crud;

import java.util.TimeZone;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class CrudApplication {
	
	/**
	 * Exemplo para chamadas dos endpoints: GET http://localhost:3000/pessoas
	 * Exemplo para chamada du UI do OpenAPI/Swagger: http://localhost:3000/swagger-ui.html. Montado conforme documentação: https://www.baeldung.com/spring-rest-openapi-documentation
	 */
	
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		
		SpringApplication.run(CrudApplication.class, args);
	}	
	
	@Bean
	public Filter getCharacterEncodingFilter() {

	    CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();

	    encodingFilter.setEncoding("UTF-8");
	    encodingFilter.setForceEncoding(true);

	    return encodingFilter;

	}

}
