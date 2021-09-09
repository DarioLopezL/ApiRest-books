package com.dari.books.service;

import java.util.*;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dari.books.model.Categoria;
import com.dari.books.model.dao.IcategoriaDao;
import com.dari.books.response.CategoriaResponseRest;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	private static final Logger Log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

	@Autowired
	private IcategoriaDao categoriaDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
		Log.info("inicio metodo buscarCategorias()");
		CategoriaResponseRest response = new CategoriaResponseRest();

		try {
			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
			response.getCategoriaResponse().setCategoria(categoria);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta no ok", "-1", "Error al consultar categorias ");
			Log.error("error al consultar categorias : ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {

		Log.info("Inicio metodo buscarPorId");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();

		try {
			Optional<Categoria> categoria = categoriaDao.findById(id);
			if (categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
			} else {
				Log.error("Error en consultar categoria ");
				response.setMetadata("respuesta no ok", "-1", "Categoria no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			Log.error("Error en consultar categoria ");
			response.setMetadata("respuesta no ok", "-1", "Error al consultar categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200

	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
		Log.info("Inicio metodo crear categoria ");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();

		try {
			Categoria categoriaGuardada = categoriaDao.save(categoria);
			if(categoriaGuardada != null) {
				list.add(categoriaGuardada);
				response.getCategoriaResponse().setCategoria(list);
			} else {
				Log.error("Error en grabar categoria ");
				response.setMetadata("respuesta no ok", "-1", "Categoria no guardada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
				
			}
		}catch(Exception e){
			Log.error("Error en Crear categoria ");
			response.setMetadata("respuesta no ok", "-1", "Error al crear categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta ok", "00", "Respuesta creada exitosamente");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
		Log.info("Inicio metodo actualizar ");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();	
		
		try {
			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
			if (categoriaBuscada.isPresent()) {
				categoriaBuscada.get().setNombre(categoria.getNombre());
				categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
				
				Categoria categoriaActualizar = categoriaDao.save(categoriaBuscada.get()); //actualizado
				if(categoriaActualizar != null) {
					response.setMetadata("Respuesta ok", "00", "Respuesta actualizada");
					list.add(categoriaActualizar);
					response.getCategoriaResponse().setCategoria(list);
			
			      } else {
				Log.error("Error en actualizar categoria ");
				response.setMetadata("respuesta no ok", "-1", "Categoria no actualizada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
				
			} else {
				Log.error("Error en actualizar categoria ");
				response.setMetadata("respuesta no ok", "-1", "Categoria no actualizada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
				
			}
				
		}catch(Exception e){
			Log.error("Error en actualizar categoria ",e.getMessage());
			e.getStackTrace();
			response.setMetadata("respuesta no ok", "-1", "Categoria no actualizada");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
		Log.info("Inicio metodo eliminar categoria  ");
		CategoriaResponseRest response = new CategoriaResponseRest();
		try {  
			categoriaDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Categoria eliminada");
			
		}catch(Exception e){
			Log.error("Error en eliminar categoria ", e.getMessage());
			e.getStackTrace();
			response.setMetadata("respuesta no ok", "-1", "Categoria no eliminada");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200
	}
	

}
