package com.emd.springboot.backend.disney.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emd.springboot.backend.disney.api.model.entity.Personaje;

public interface IPersonajeRepository extends JpaRepository<Personaje, Integer> {

}
