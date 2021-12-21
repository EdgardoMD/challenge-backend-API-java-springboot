package com.emd.springboot.backend.disney.api.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emd.springboot.backend.disney.api.model.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	boolean existsByNombreUsuario(String nombreUsuario);
	boolean existsByEmail(String email);

}
