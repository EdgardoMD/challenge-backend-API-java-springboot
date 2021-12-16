package com.emd.springboot.backend.disney.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.emd.springboot.backend.disney.api.model.entity.Personaje;
import com.emd.springboot.backend.disney.api.model.repository.IPersonajeRepository;

public class PersonajeServiceImpl implements IPersonajeService{
	
	@Autowired
	private IPersonajeRepository repository;

	@Override
	public List<Personaje> listar() {
		
		return repository.findAll();
	}

	@Override
	public Personaje registrar(Personaje personaje) {
		
		return repository.save(personaje);
	}

	@Override
	public Personaje editar(Personaje personaje) {
		
		return repository.save(personaje);
	}

	@Override
	public Optional<Personaje> obtenerPorId(Integer id) {
		
		return repository.findById(id);
	}

	@Override
	public boolean eliminar(Integer id) {
		repository.deleteById(id);
		return true;
	}

}
