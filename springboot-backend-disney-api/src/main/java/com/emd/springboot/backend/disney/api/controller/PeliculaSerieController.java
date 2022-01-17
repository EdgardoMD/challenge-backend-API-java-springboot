package com.emd.springboot.backend.disney.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emd.springboot.backend.disney.api.model.entity.PeliculaSerie;
import com.emd.springboot.backend.disney.api.service.IPeliculaSerieService;

@RestController
@CrossOrigin({"http://localhost:4200"})
@RequestMapping("/movies")
public class PeliculaSerieController {
	
	@Autowired
	private IPeliculaSerieService service;
	
	@GetMapping
	public ResponseEntity<?> listarPeliculasSeries(){
		//return ResponseEntity.ok(service.listarPeliculasSeriesDto());
		return ResponseEntity.ok(service.listar());
	}
	
	@PostMapping
	public ResponseEntity<?> registrarPeliculaSerieConImagen(PeliculaSerie peliculaSerie,
			@RequestParam MultipartFile archivo) throws IOException{
		if(!archivo.isEmpty()) {
			peliculaSerie.setImagen(archivo.getBytes());
		}
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
	public ResponseEntity<?> editarPeliculaSerieConImagen(PeliculaSerie peliculaSerie, 
			@PathVariable Integer id, @RequestParam MultipartFile archivo) throws IOException{
		Optional<PeliculaSerie> peliculaSerieOp = service.obtenerPorId(id);
		if(!peliculaSerieOp.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		PeliculaSerie peliculaSerieEdit = peliculaSerieOp.get();
		peliculaSerieEdit.setTitulo(peliculaSerie.getTitulo());
		peliculaSerieEdit.setCalificacion(peliculaSerie.getCalificacion());
		peliculaSerieEdit.setFechaCreacion(peliculaSerie.getFechaCreacion());
		
		if(!archivo.isEmpty()) {
			peliculaSerieEdit.setImagen(archivo.getBytes());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.editar(peliculaSerieEdit));	
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPeliculaSerie(@PathVariable Integer id){
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/name")
	public ResponseEntity<?> buscarPeliculaSeriePorNombre(@RequestParam("name") String titulo){
		return ResponseEntity.ok(service.obtenerPeliculaSeriePorTitulo(titulo));
	}
	
	@GetMapping("/gender")
	public ResponseEntity<?> buscarPeliculasSeriesPorIdGenero(@RequestParam("gender") Integer generoId){
		
		List<Integer> listadoIds = service.obtenerPeliculasSeriesPorIdGenero(generoId);
		List<PeliculaSerie> listadoPeliculasPorGenero = new ArrayList<>();
		
		for(int i=0; i<listadoIds.size(); i++) {
			PeliculaSerie ps = service.obtenerPorId(listadoIds.get(i)).get();
			listadoPeliculasPorGenero.add(ps);		
		}			
		
		return ResponseEntity.ok(listadoPeliculasPorGenero);
	}
	
	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verImagen(@PathVariable Integer id) {
		Optional<PeliculaSerie> optional = service.obtenerPorId(id);
		if(optional.isEmpty() || optional.get().getImagen() == null) {
			return ResponseEntity.notFound().build();
		}
		Resource img = new ByteArrayResource(optional.get().getImagen());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);
		
	}
	
	

}
