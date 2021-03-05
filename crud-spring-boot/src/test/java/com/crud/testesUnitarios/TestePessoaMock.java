package com.crud.testesUnitarios;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.crud.api.controller.PessoaController;
import com.crud.vendas.domain.model.Pessoa;
import com.crud.vendas.domain.repository.PessoaRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class TestePessoaMock {
	
	private static final Long pessoaId = 1l;
	private static final String USERNAME = "user1";
	private static final String PASSWORD = "1234";
	
	PessoaRepository repositoryMock;
	PessoaController pessoaControllerMock;
	
	@BeforeEach
	void setup() {
		if  (repositoryMock == null) {
			 repositoryMock = criaMockPessoaRespository(pessoaId);
		}	
		if  (pessoaControllerMock == null) {
			pessoaControllerMock = criaMockPessoaController();
		}	
		
	}

	@Test
	@Order(1)
	void testChamadaDiretoControlerMock() {
		PessoaController pessoaController = new PessoaController();
		assertNotNull(pessoaController.logar(USERNAME, PASSWORD));
	}
	
	@Test
	@Order(2)
	void testFindByIdMock() {
        Optional<Pessoa> returnedPessoa = repositoryMock.findById(pessoaId);
        Assertions.assertEquals(pessoaId, returnedPessoa.get().getId());
    }
	
	@Test
	@Order(3)
	void testFindAllMock() {
        List<Pessoa> lPessoa = repositoryMock.findAll();
        Assertions.assertTrue(lPessoa.size() == 1);
    }
	

	private PessoaRepository criaMockPessoaRespository(Long pessoaId) {
		PessoaRepository repositoryMock = Mockito.mock(PessoaRepository.class);
		
		Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        doReturn(Optional.of(pessoa)).when(repositoryMock).findById(pessoaId);
        
        List<Pessoa> lPessoa = new ArrayList<Pessoa>();
        lPessoa.add(pessoa);
        doReturn(lPessoa).when(repositoryMock).findAll();
        
        return repositoryMock;
	}

	private PessoaController criaMockPessoaController() {
		PessoaController controllerMock = Mockito.mock(PessoaController.class);
		String hash = UUID.randomUUID().toString();
		doReturn(hash).when(controllerMock).logar(USERNAME,PASSWORD);
		return controllerMock;
	}
}
