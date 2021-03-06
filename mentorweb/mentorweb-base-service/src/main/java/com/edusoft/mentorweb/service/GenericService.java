package com.edusoft.mentorweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericService<T>{
	
	@Autowired
    private JpaRepository<T, Long> repository;


	public List<T> findAll() {
		return repository.findAll();
	}

	public T save(T entity) {
		return repository.save(entity);
	}

	public Optional<T> findById(long id) {
		return repository.findById(id);
	}

	public void delete(T entity) {
		repository.delete(entity);
	}

	public void deleteById(long id) {
		repository.deleteById(id);
	}

	public long count() {
		return repository.count();
	}
	
	public boolean existsById(long id) {
		return repository.existsById(id);
	}

}
