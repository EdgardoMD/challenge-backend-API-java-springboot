package com.emd.springboot.backend.disney.api.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.emd.springboot.backend.disney.api.dto.PersonajeDTO;
import com.emd.springboot.backend.disney.api.model.entity.Personaje;

public interface IPersonajeService extends ICRUD<Personaje>{
	
	Personaje buscarPersonajePorNombre(@Param("nombre") String nombre);
	List<Personaje> buscarPersonajesPorEdad(@Param("edad") int edad);
	
	List<PersonajeDTO> listarPersonajesShort();

}
