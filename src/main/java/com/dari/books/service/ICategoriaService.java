package com.dari.books.service;

import org.springframework.http.ResponseEntity;

import com.dari.books.model.Categoria;
import com.dari.books.response.CategoriaResponseRest;

public interface ICategoriaService {
	
	public ResponseEntity<CategoriaResponseRest>  buscarCategorias();

	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);

	public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria);
	
	public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id);
	
	public ResponseEntity<CategoriaResponseRest> eliminar(Long id); 

}
