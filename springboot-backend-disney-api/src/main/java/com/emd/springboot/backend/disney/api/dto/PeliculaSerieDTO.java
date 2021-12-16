package com.emd.springboot.backend.disney.api.dto;

import lombok.Data;

@Data
public class PeliculaSerieDTO {
	
	private byte[] imagen;
	private String titulo;
	private String fechaCreacion;

}
