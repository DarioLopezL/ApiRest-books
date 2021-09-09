package com.dari.books.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.dari.books.model.Categoria;
import com.dari.books.model.dao.IcategoriaDao;
import com.dari.books.response.CategoriaResponseRest;

public class CategoriaServiceImplTest {

	@InjectMocks
	CategoriaServiceImpl service;
	
	@Mock
	IcategoriaDao categoriaDao;
	
	List<Categoria> list = new ArrayList<Categoria>();
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.cargarCategoria();
		
	}

	@Test
	public void buscarCategoriasTest() {
		when(categoriaDao.findAll()).thenReturn(list);
	
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
		assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size());
		
		verify(categoriaDao, times(1)).findAll();
		
		
	}

public void cargarCategoria() {
	
	Categoria catUno = new Categoria(Long.valueOf(1), "Abarrotes", "Distintos Abarrotes");
	Categoria catDos = new Categoria(Long.valueOf(1), "Lacteos", "variedad de lacteos");
	Categoria catTres = new Categoria(Long.valueOf(1), "Bebidas", "bebidas sin azucar");
	Categoria catCuatro = new Categoria(Long.valueOf(1), "Carnes blancas", "distintas carnes");

	list.add(catUno);
	list.add(catDos);
	list.add(catTres);
	list.add(catCuatro);
}







}
