package com.emd.springboot.backend.disney.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emd.springboot.backend.disney.api.model.entity.Genero;

public interface IGeneroRepository extends JpaRepository<Genero, Integer> {

}
