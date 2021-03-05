package com.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.vendas.domain.model.Pessoa;
import com.crud.vendas.domain.repository.PessoaRepository;

@Service
public class CadastroPessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa salvar(Pessoa pessoa) {
		/*Pessoa pessoaExistente = pessoaRepository.findByEmail(pessoa.getEmail());
		
		if (pessoaExistente != null && !pessoaExistente.equals(pessoa)) {
			throw new NegocioException("Já existe um pessoa cadastrado com este e-mail.");
		}
		*/
		return pessoaRepository.save(pessoa);
	}
	
	public void excluir(Long pessoaId) {
		pessoaRepository.deleteById(pessoaId);
	}
	
}
