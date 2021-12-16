package com.emd.springboot.backend.disney.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emd.springboot.backend.disney.api.dto.PersonajeDTO;
import com.emd.springboot.backend.disney.api.model.entity.Personaje;
import com.emd.springboot.backend.disney.api.model.repository.IPersonajeRepository;

@Service
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

	@Override
	public Personaje buscarPersonajePorNombre(String nombre) {
		return repository.buscarPersonajePorNombre(nombre);
	}

	@Override
	public List<Personaje> buscarPersonajesPorEdad(int edad) {
		return repository.findByEdad(edad);
	}

	@Override
	public List<PersonajeDTO> listarPersonajesShort() {
		List<PersonajeDTO> personajeDTOList = new ArrayList<>();
        repository.findAll()
                .forEach(p -> {
                    PersonajeDTO personajeDTO = new PersonajeDTO();
                    personajeDTO.setImagen(p.getImagen());
                    personajeDTO.setNombre(p.getNombre());
                    personajeDTOList.add(personajeDTO);
                });
        return personajeDTOList;
	}

	

}
