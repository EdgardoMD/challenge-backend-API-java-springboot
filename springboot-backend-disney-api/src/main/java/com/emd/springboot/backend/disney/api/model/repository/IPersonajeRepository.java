package com.emd.springboot.backend.disney.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emd.springboot.backend.disney.api.model.entity.Personaje;

public interface IPersonajeRepository extends JpaRepository<Personaje, Integer> {
	
	@Query("from Personaje p where p.nombre = :nombre")
	Personaje buscarPersonajePorNombre(@Param("nombre") String nombre);
	
	//@Query("from Personaje p where p.edad = :edad")
    List<Personaje> findByEdad(@Param("edad") int edad);
}
