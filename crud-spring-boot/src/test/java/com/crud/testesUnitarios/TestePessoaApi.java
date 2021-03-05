package com.crud.testesUnitarios;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import com.crud.vendas.domain.model.Pessoa;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class TestePessoaApi {
	
    
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    
    
    private String tokenAutenticacao;
    
    private final static String URL_BASE = "http://localhost:%d";
    private final static String PATH_LOGIN = "/pessoas/login";
    private final static String PATH_BUSCAR_PESSOA_POR_ID = "/pessoas/%d";
    private final static String PATH_BUSCAR_PESSOA_TODAS = "/pessoas";
    private final static String AUTHORIZATION = "Authorization";
    private final static String BEARER = "Bearer ";
	
	@BeforeEach
	void setup() {
		autenticar();
	}


	private void autenticar() {
		if  (tokenAutenticacao != null) {
			return;
		}
		
		URI uri = UriComponentsBuilder.fromHttpUrl(String.format(URL_BASE, port)).path(PATH_LOGIN)
                .queryParam("username", "1234")
                .queryParam("password", "1234")
                .build().toUri();
	
		ResponseEntity<String> responseLogar = this.restTemplate.getForEntity(uri, String.class);
		
		Assertions.assertNotNull(responseLogar.getBody());
		tokenAutenticacao = responseLogar.getBody();
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	@Order(1)
	void testeBuscarTodasPessoas() throws RestClientException, MalformedURLException {
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION, BEARER + tokenAutenticacao);
		
		String url = String.format(URL_BASE, port) + PATH_BUSCAR_PESSOA_TODAS;
		
		ResponseEntity<List> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                List.class
        );
		Assertions.assertTrue(response.getBody().size() > 0);
	}
	
	
	
	@Test
	@Order(2)
	void testeBuscarPessoaPorId() throws RestClientException, MalformedURLException {
		Long pessoaId = 12l;
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION, BEARER + tokenAutenticacao);
		
		String url = String.format(URL_BASE, port) + String.format(PATH_BUSCAR_PESSOA_POR_ID, pessoaId);
		
		ResponseEntity<Pessoa> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                Pessoa.class
        );
		Assertions.assertEquals(pessoaId, response.getBody().getId());
	}



}
