package com.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication {
	
	/**
	 * Exemplo para chamadas dos endpoints: GET http://localhost:3000/pessoas
	 * Exemplo para chamada du UI do OpenAPI/Swagger: http://localhost:3000/swagger-ui.html. Montado conforme documentação: https://www.baeldung.com/spring-rest-openapi-documentation
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}	

}
