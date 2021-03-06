package com.edusoft.mentorweb.service.financeiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edusoft.mentorweb.model.Pessoa;
import com.edusoft.mentorweb.repository.PessoaRepository;
import com.edusoft.mentorweb.service.GenericService;

@Service
public class PessoaService extends GenericService<Pessoa>{
	
	@Autowired
	private PessoaRepository repository;
	
	public void fazOutraCoisa() {
		repository.fazOutraCoisa();
	}

	

}
