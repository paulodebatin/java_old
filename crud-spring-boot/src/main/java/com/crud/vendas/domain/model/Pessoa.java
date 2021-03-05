package com.crud.vendas.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@DynamicUpdate
@DynamicInsert
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	@Schema(description = "Nome da pessoa.",example = "João da silva", required = true)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Boolean getReceberNoticias() {
		return receberNoticias;
	}

	public void setReceberNoticias(Boolean receberNoticias) {
		this.receberNoticias = receberNoticias;
	}

	public Boolean getFumante() {
		return fumante;
	}

	public void setFumante(Boolean fumante) {
		this.fumante = fumante;
	}

	public OffsetDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(OffsetDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
