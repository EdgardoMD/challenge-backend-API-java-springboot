package com.emd.springboot.backend.disney.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emd.springboot.backend.disney.api.dto.PeliculaSerieDTO;
import com.emd.springboot.backend.disney.api.model.entity.PeliculaSerie;
import com.emd.springboot.backend.disney.api.model.repository.IPeliculaSerieRepository;

@Service
public class PeliculaSerieServiceImpl implements IPeliculaSerieService{
	
	@Autowired
	private IPeliculaSerieRepository repository;

	@Override
	public List<PeliculaSerie> listar() {	
		return repository.findAll();
	}

	@Override
	public PeliculaSerie registrar(PeliculaSerie peliculaSerie) {
		return repository.save(peliculaSerie);
	}

	@Override
	public PeliculaSerie editar(PeliculaSerie peliculaSerie) {
		return repository.save(peliculaSerie);
	}

	@Override
	public Optional<PeliculaSerie> obtenerPorId(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean eliminar(Integer id) {
		repository.deleteById(id);
		return true;
	}

	@Override
	public List<PeliculaSerieDTO> listarPeliculasSeriesDto() {
		List<PeliculaSerieDTO> listaDto = new ArrayList<>();
		repository.findAll()
		.forEach(p -> {
			PeliculaSerieDTO peliculaSerieDto = new PeliculaSerieDTO();
			peliculaSerieDto.setImagen(p.getImagen());
			peliculaSerieDto.setTitulo(p.getTitulo());
			peliculaSerieDto.setFecchaCreacion(p.getFechaCreacion());
			listaDto.add(peliculaSerieDto);
			
		});
		return listaDto;
	}

}
