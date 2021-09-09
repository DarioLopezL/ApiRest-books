package com.dari.books.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.dari.books.model.Libro;


public interface ILibroDao extends CrudRepository<Libro, Long>{

}
