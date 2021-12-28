package com.emd.springboot.backend.disney.api.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Table(name = "generos")
@Entity
public class Genero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] imagen;
	
	@ManyToMany(mappedBy = "generos")
	@JsonManagedReference
	private List<PeliculaSerie> peliculasSeries;
	
	public Genero() {
		this.peliculasSeries = new ArrayList<>();
	}

}
