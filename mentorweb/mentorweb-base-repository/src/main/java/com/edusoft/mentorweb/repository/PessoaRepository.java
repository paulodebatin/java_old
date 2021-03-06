package com.edusoft.mentorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edusoft.mentorweb.model.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryCustom{
	
}