package com.crud.auth.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.auth.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
