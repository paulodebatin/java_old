package com.edusoft.mentorweb.model.event;

import com.edusoft.mentorweb.model.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PessoaComCodigoGeradoEvent {
	private Pessoa pessoa;
}
