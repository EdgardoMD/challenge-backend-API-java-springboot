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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emd.springboot.backend.disney.api.model.entity.Personaje;
import com.emd.springboot.backend.disney.api.service.IPersonajeService;

@RestController
@RequestMapping("/characters")
public class PersonajeController {
	
	@Autowired
	private IPersonajeService service;
	
	@GetMapping
	public ResponseEntity<?> listarPersonajes(){
		return ResponseEntity.ok(service.listarPersonajesShort());
	}
	
	@PostMapping
	public ResponseEntity<?> registrarPersonaje(@RequestBody Personaje personaje){
		Personaje nuevoPersonaje = service.registrar(personaje);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPersonaje);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPersonajePorId(@PathVariable Integer id){
		Optional<Personaje> personajeOp = service.obtenerPorId(id);
		if(!personajeOp.isPresent()) {
			return ResponseEntity.badRequest().body("No existe personaje con ese ID");
		}
		return ResponseEntity.ok(personajeOp);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editarPersonajePorId(@RequestBody Personaje personaje, @PathVariable Integer id){
		Optional<Personaje> personajeOp = service.obtenerPorId(id);
		if(personajeOp.isEmpty()) {
			return ResponseEntity.badRequest().body("No existe personaje con ese ID");
		}
		Personaje personajeEdit = personajeOp.get();
		personajeEdit.setNombre(personaje.getNombre());
		personajeEdit.setEdad(personaje.getEdad());
		personajeEdit.setPeso(personaje.getPeso());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.editar(personajeEdit));	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPersonaje(@PathVariable Integer id){
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/name")
	public ResponseEntity<?> buscarPersonajePorNombre(@RequestParam("name") String name){
		return ResponseEntity.ok(service.buscarPersonajePorNombre(name));
	}
	
	@GetMapping("/age")
	public ResponseEntity<?> buscarPersonajePorEdad(@RequestParam("age") int age){
		return ResponseEntity.ok(service.buscarPersonajesPorEdad(age));
	}

}
