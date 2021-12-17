package com.emd.springboot.backend.disney.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emd.springboot.backend.disney.api.model.entity.PeliculaSerie;
import com.emd.springboot.backend.disney.api.service.IPeliculaSerieService;

@RestController
@RequestMapping("/movies")
public class PeliculaSerieController {
	
	@Autowired
	private IPeliculaSerieService service;
	
	@GetMapping
	public ResponseEntity<?> listarPeliculasSeries(){
		return ResponseEntity.ok(service.listarPeliculasSeriesDto());
	}
	
	@PostMapping
	public ResponseEntity<?> registrarPeliculaSerie(@RequestBody PeliculaSerie peliculaSerie){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(peliculaSerie));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPeliculaSeriePorId(@PathVariable Integer id){
		Optional<PeliculaSerie> peliculaSerieOp = service.obtenerPorId(id);
		if(peliculaSerieOp.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(peliculaSerieOp);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editarPeliculaSerie(@RequestBody PeliculaSerie peliculaSerie, @PathVariable Integer id){
		Optional<PeliculaSerie> peliculaSerieOp = service.obtenerPorId(id);
		if(!peliculaSerieOp.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		PeliculaSerie peliculaSerieEdit = peliculaSerieOp.get();
		peliculaSerieEdit.setImagen(peliculaSerie.getImagen());
		peliculaSerieEdit.setTitulo(peliculaSerie.getTitulo());
		peliculaSerieEdit.setCalificacion(peliculaSerie.getCalificacion());
		peliculaSerieEdit.setFechaCreacion(peliculaSerie.getFechaCreacion());
		return ResponseEntity.status(HttpStatus.CREATED).body(peliculaSerieEdit);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPeliculaSerie(@PathVariable Integer id){
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
