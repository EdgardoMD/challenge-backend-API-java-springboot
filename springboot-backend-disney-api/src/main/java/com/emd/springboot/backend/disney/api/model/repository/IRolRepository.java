package com.emd.springboot.backend.disney.api.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emd.springboot.backend.disney.api.model.entity.NombreRol;
import com.emd.springboot.backend.disney.api.model.entity.Rol;

public interface IRolRepository extends JpaRepository<Rol, Integer> {
	
	Optional<Rol> findByNombre(NombreRol nombre);

}
