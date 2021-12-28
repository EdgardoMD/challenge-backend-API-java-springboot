package com.emd.springboot.backend.disney.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emd.springboot.backend.disney.api.model.entity.PeliculaSerie;

public interface IPeliculaSerieRepository extends JpaRepository<PeliculaSerie, Integer> {
	
	@Query("from PeliculaSerie ps where ps.titulo = :titulo")
	PeliculaSerie obtenerPeliculaSeriePorTitulo(@Param("titulo") String titulo);
	
    @Query(value = "select fk_peliculas_series from peliculas_series_generos where fk_generos =:generoId", nativeQuery = true)
    List<Integer> obtenerPeliculasSeriesPorIdGenero(@Param("generoId") Integer generoId);

}
