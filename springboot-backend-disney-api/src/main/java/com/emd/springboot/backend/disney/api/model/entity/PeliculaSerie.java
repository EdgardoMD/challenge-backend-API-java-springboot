package com.emd.springboot.backend.disney.api.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Table(name = "peliculas_series")
@Entity
public class PeliculaSerie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Lob
	//@Basic(fetch = FetchType.LAZY)
	@JsonIgnore
	private byte[] imagen;
	
	@NotBlank
	@Size(min = 2)
	private String titulo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	private int calificacion;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "peliculas_series_personajes",
			joinColumns = @JoinColumn(name = "FK_PELICULAS_SERIES", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "FK_PERSONAJES", nullable = false))
	@JsonBackReference
	private List<Personaje> personajesAsociados;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "peliculas_series_generos",
			joinColumns = @JoinColumn(name = "FK_PELICULAS_SERIES", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "FK_GENEROS", nullable = false))
	@JsonBackReference
	private List<Genero> generos;
	
	public PeliculaSerie() {
		this.personajesAsociados = new ArrayList<>();
		this.generos = new ArrayList<>();
	}
	
	public Integer getImagenHashCode() {
		return (this.imagen != null) ? this.imagen.hashCode(): null;
	}

}
