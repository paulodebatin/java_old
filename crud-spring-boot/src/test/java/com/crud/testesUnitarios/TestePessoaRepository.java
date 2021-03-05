package com.crud.testesUnitarios;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crud.vendas.domain.model.Pessoa;
import com.crud.vendas.domain.repository.PessoaRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class TestePessoaRepository {
	
	Logger logger = LoggerFactory.getLogger(TestePessoaRepository.class);
	
	private static final Long pessoaId = 12l;
	

	@Autowired
	PessoaRepository repository;
	
	@Test
	@Order(1)
	void testFindById() {
		logger.info("testFindById");
        Optional<Pessoa> returnedPessoa = repository.findById(pessoaId);
        Assertions.assertEquals(pessoaId, returnedPessoa.get().getId());
    }
	
	@Test
	@Order(2)
	void testFindAll() {
		logger.info("testFindAll");
        List<Pessoa> lPessoa = repository.findAll();
        Assertions.assertTrue(lPessoa.size() > 0);
    }
	
	@Test
	@Order(3)
	void testGravacaoPessoa() {
		Optional<Pessoa> returnedPessoa = null;
		for (int i = 0; i < 100; i++) {
			logger.info("testGravacaoPessoa - Buscando pessoa");
	        returnedPessoa = repository.findById(pessoaId);
			
		}
        if  (returnedPessoa.isPresent()) {
        	Pessoa pessoa = returnedPessoa.get();
        	pessoa.setNome("Teste3");
        	logger.info("testGravacaoPessoa - gravando pessoa");
        	repository.save(pessoa);
        }
        
        
    }
	

}
