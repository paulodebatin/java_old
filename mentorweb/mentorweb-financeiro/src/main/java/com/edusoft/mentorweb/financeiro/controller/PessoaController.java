package com.edusoft.mentorweb.financeiro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edusoft.mentorweb.financeiro.controller.openapi.PessoaControllerOpenApi;
import com.edusoft.mentorweb.financeiro.dto.PessoaDTO;
import com.edusoft.mentorweb.model.Pessoa;
import com.edusoft.mentorweb.service.financeiro.PessoaService;

import io.swagger.v3.oas.annotations.Parameter;

@CrossOrigin //(origins = "http://localhost:8089")
@RestController
@RequestMapping("/v1/pessoas")

public class PessoaController implements PessoaControllerOpenApi{

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ModelMapper modelMapper;
		

	@GetMapping
	@Override
	public List<PessoaDTO> listar() {
		
		// Exemplo para demonstrar: server.compression.enabled=true
		List<PessoaDTO> pessoas = new ArrayList<PessoaDTO>();
 		for (int i = 0; i < 12000; i++) {
			PessoaDTO pessoa = new PessoaDTO();
			pessoa.setNome("Nome " + i);
			pessoa.setEndereco("Endereco " + i);
			pessoa.setBairro("Bairro " + i);
			pessoa.setCidade("Cidade " + i);
			pessoa.setUf("stado " + i);
			pessoa.setCep("cep" + i);
			pessoas.add(pessoa);
		}
		
 		return pessoas;
		//return pessoaService.findAll().stream().map(this::converteToDTO).collect(Collectors.toList());
	}
	

	@PutMapping("/{pessoaId}")
	@Override
	public ResponseEntity<PessoaDTO> atualizar(@Valid @Parameter(description = "id da pessoa a ser atualizada")  @PathVariable Long pessoaId,
			@RequestBody PessoaDTO pessoaDTO) {
		
		Optional<Pessoa> pessoaOptional = pessoaService.findById(pessoaId);
		if  (!pessoaOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		
		Pessoa pessoa = pessoaOptional.get();
		modelMapper.map(pessoaDTO, pessoa);
		
		pessoa = pessoaService.save(pessoa);
		
		modelMapper.map(pessoa,pessoaDTO);
		
		return ResponseEntity.ok(pessoaDTO);
	}

//
//	private PessoaDTO converteToDTO(Pessoa pessoa) {
//		// Exemplo de conversoes: https://www.youtube.com/watch?v=HU7bfKG8nV4
//		return modelMapper.map(pessoa, PessoaDTO.class);
//	}
	
	
		
}
