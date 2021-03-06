package com.edusoft.mentorweb.financeiro.controller.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.edusoft.mentorweb.financeiro.dto.PessoaDTO;

import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "pessoas", description = "API para manipulação de dados de pessoa")
public interface PessoaControllerOpenApi {
	
	@Operation(summary = "Retornar todas as pessoas")
	@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
	public List<PessoaDTO> listar();
	
	@Operation(summary = "Alterar/Atualizar uma pessoa")
	@ApiResponse(responseCode = "200", description = "Pessoa alterada com sucesso")
	@ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
	public ResponseEntity<PessoaDTO> atualizar(
			@ApiParam(value = "ID de uma pessoa", example = "1", required = true)  Long pessoaId, 
			@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados", required = true) PessoaDTO pessoaDTO);
	
	

}
