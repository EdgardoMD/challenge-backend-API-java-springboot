package com.emd.springboot.backend.disney.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emd.springboot.backend.disney.api.model.entity.PeliculaSerie;

public interface IPeliculaSerieRepository extends JpaRepository<PeliculaSerie, Integer> {
	
	@Query("from PeliculaSerie ps where ps.titulo = :titulo")
	PeliculaSerie obtenerPeliculaSeriePorTitulo(@Param("titulo") String titulo);
	


}
