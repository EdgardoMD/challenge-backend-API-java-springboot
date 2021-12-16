package com.emd.springboot.backend.disney.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}
