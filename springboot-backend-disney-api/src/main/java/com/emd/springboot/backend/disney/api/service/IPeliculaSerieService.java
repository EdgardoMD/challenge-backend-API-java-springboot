package com.emd.springboot.backend.disney.api.service;

import java.util.List;

import com.emd.springboot.backend.disney.api.dto.PeliculaSerieDTO;
import com.emd.springboot.backend.disney.api.model.entity.PeliculaSerie;

public interface IPeliculaSerieService extends ICRUD<PeliculaSerie> {
	
	List<PeliculaSerieDTO> listarPeliculasSeriesDto();

}
