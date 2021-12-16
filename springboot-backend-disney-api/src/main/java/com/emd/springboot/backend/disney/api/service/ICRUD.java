package com.emd.springboot.backend.disney.api.service;

import java.util.List;
import java.util.Optional;

public interface ICRUD<T> {
	
	List<T> listar();
	T registrar(T objeto);
	T editar(T objeto);
	Optional<T> obtenerPorId(Integer id);
	boolean eliminar(Integer id);

}
