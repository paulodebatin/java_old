package com.crud.testesUnitarios;

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
import org.springframework.transaction.annotation.Transactional;

import com.crud.auth.domain.model.Usuario;
import com.crud.auth.domain.repository.UsuarioRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class TesteUsuarioRepository {
	
	Logger logger = LoggerFactory.getLogger(TesteUsuarioRepository.class);
	
	private static final Long usuarioId = 1l;
	

	@Autowired
	UsuarioRepository repository;
	
	@Test
	@Order(1)
	@Transactional("authTransactionManager")
	void testFindById() {
		logger.info("testFindById");
        Optional<Usuario> returnedUsuario = repository.findById(usuarioId);
        Assertions.assertEquals(usuarioId, returnedUsuario.get().getId());
    }
}
