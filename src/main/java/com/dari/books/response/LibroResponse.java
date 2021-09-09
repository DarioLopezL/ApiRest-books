package com.dari.books.response;

import java.util.List;

import com.dari.books.model.Libro;


public class LibroResponse {
	private List<Libro> libro;

	public List<Libro> getLibro() {
		return this.libro;
	}

	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}

}
