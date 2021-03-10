package com.edusoft.mentorweb.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.edusoft.mentorweb.model.event.PessoaComCodigoGeradoEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@DynamicUpdate
@DynamicInsert
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Pessoa extends AbstractAggregateRoot<Pessoa>{

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Size(max = 255)
	private String endereco;
	
	@Size(max = 20)
	private String numero;
	
	@Size(max = 255)
	private String bairro;
	
	@Size(max = 255)
	private String cidade;

	@Size(max = 2)
	private String uf;
	
	@Size(max = 8)
	private String cep;

	@Column(name = "receber_noticias")
	private Boolean receberNoticias;
	
	private Boolean fumante;
	
	@Column(name = "data_nascimento")
	private OffsetDateTime dataNascimento;
	
	@PostUpdate
	private void gerarCodigo() {
		System.out.println("Código gerado no @PostUpdate da model: " + UUID.randomUUID().toString());
		registerEvent(new PessoaComCodigoGeradoEvent(this));
	}

}
