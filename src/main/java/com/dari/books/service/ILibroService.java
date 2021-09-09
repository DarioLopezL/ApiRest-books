package com.dari.books.service;

import org.springframework.http.ResponseEntity;

import com.dari.books.model.Libro;

import com.dari.books.response.LibroResponseRest;

public interface ILibroService {
	public ResponseEntity<LibroResponseRest> buscarLibros();

	public ResponseEntity<LibroResponseRest> buscarPorId(Long id);

	public ResponseEntity<LibroResponseRest> crear(Libro libro);

	public ResponseEntity<LibroResponseRest> actualizar(Libro libro, Long id);

	public ResponseEntity<LibroResponseRest> eliminar(Long id);

	

}
