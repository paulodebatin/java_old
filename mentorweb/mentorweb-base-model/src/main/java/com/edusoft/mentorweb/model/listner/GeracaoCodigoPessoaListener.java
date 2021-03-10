package com.edusoft.mentorweb.model.listner;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.edusoft.mentorweb.model.Pessoa;
import com.edusoft.mentorweb.model.event.PessoaComCodigoGeradoEvent;

@Component
public class GeracaoCodigoPessoaListener {
	
	@TransactionalEventListener
	public void aoGerarCodigoPessoa(PessoaComCodigoGeradoEvent event) {
		Pessoa pessoa = event.getPessoa();
		System.out.println("GeracaoCodigoPessoaListener: Listener disparado pelo registro de código de pessoa. Nome=" + pessoa.getNome());
	}

}
