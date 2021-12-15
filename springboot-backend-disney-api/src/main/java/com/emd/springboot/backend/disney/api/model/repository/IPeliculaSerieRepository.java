package com.emd.springboot.backend.disney.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emd.springboot.backend.disney.api.model.entity.PeliculaSerie;

public interface IPeliculaSerieRepository extends JpaRepository<PeliculaSerie, Integer> {

}
