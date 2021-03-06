package com.edusoft.mentorweb.financeiro.testes;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.edusoft.mentorweb.service.financeiro.PessoaService;

@SpringBootTest
class TestePessoaService {
	
	Logger logger = LoggerFactory.getLogger(TestePessoaService.class);
	
	@Autowired
	PessoaService pessoaService;
	
	@Test
	void testListAll() {
		pessoaService.findAll();
		pessoaService.fazOutraCoisa();
    }
}
