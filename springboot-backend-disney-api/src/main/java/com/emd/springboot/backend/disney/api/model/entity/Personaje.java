package com.emd.springboot.backend.disney.api.model.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Table(name = "personajes")
@Entity
public class Personaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Lob
	@JsonIgnore
	private byte[] imagen;
	
	private String nombre;
	
	private Integer edad;
	
	private double peso;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String historia;
	
	@ManyToMany(mappedBy = "personajesAsociados")
	@JsonManagedReference
	private List<PeliculaSerie> peliculasSeries;

}
