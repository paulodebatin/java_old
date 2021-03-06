package com.edusoft.mentorweb.financeiro.dto;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {
	@NotBlank
	@Size(max = 60)
	@Schema(description = "Nome da pessoa.",example = "João da silva", required = true)
	private String nome;
	
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private Boolean receberNoticias;
	private Boolean fumante;
	private OffsetDateTime dataNascimento;
}
