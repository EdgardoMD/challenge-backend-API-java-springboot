package com.emd.springboot.backend.disney.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PeliculaSerieDTO {
	
	private byte[] imagen;
	private String titulo;
	private Date fecchaCreacion;

}
