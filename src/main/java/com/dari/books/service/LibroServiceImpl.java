package com.dari.books.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.dari.books.model.Libro;
import com.dari.books.model.dao.ILibroDao;

import com.dari.books.response.LibroResponseRest;

@Service
public class LibroServiceImpl implements ILibroService {

	private static final Logger Log = LoggerFactory.getLogger(LibroServiceImpl.class);

	@Autowired
	private ILibroDao libroDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibroResponseRest> buscarLibros() {
		Log.info("inicio metodo buscarLibros()");
		LibroResponseRest response = new LibroResponseRest();

		try {
			List<Libro> libro = (List<Libro>) libroDao.findAll();
			response.getLibroResponse().setLibro(libro);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta no ok", "-1", "Error al consultar Libros... ");
			Log.error("error al consultar Libros.. : ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // devuelve 200

	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibroResponseRest> buscarPorId(Long id) {

		Log.info("Inicio metodo buscarPorId Libros...");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();

		try {
			Optional<Libro> libro = libroDao.findById(id);
			if (libro.isPresent()) {
				list.add(libro.get());
				response.getLibroResponse().setLibro(list);
			} else {
				Log.error("Error en consultar Libros... ");
				response.setMetadata("respuesta no ok", "-1", "Libro no encontrada :( ");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			Log.error("Error en consultar Libro... ");
			response.setMetadata("respuesta no ok", "-1", "Error al consultar Libro..");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // devuelve 200

	}

	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> crear(Libro libro) {

		Log.info("Inicio metodo crear Libro... ");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();

		try {
			Libro libroGuardada = libroDao.save(libro);
			if (libroGuardada != null) {
				list.add(libroGuardada);
				response.getLibroResponse().setLibro(list);
			} else {
				Log.error("Error en grabar Libro ");
				response.setMetadata("respuesta no ok", "-1", "Libro no guardada");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {
			Log.error("Error en Crear Libro ");
			response.setMetadata("respuesta no ok", "-1", "Error al crear Libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta ok", "00", "Respuesta creada exitosamente");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // devuelve 200

	}

	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> actualizar(Libro libro, Long id) {
		Log.info("Inicio metodo actualizar Libro.. ");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();

		try {
			Optional<Libro> libroBuscada = libroDao.findById(id);
			if (libroBuscada.isPresent()) {
				libroBuscada.get().setNombre(libro.getNombre());
				libroBuscada.get().setDescripcion(libro.getDescripcion());

				Libro libroActualizar = libroDao.save(libroBuscada.get()); // actualizado
				if (libroActualizar != null) {
					response.setMetadata("Respuesta ok", "00", "Respuesta actualizada");
					list.add(libroActualizar);
					response.getLibroResponse().setLibro(list);

				} else {
					Log.error("Error en actualizar Libro ");
					response.setMetadata("respuesta no ok", "-1", "Libro no actualizada");
					return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
				}

			} else {
				Log.error("Error en actualizar Libro ");
				response.setMetadata("respuesta no ok", "-1", "Libro no actualizada");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);

			}

		} catch (Exception e) {
			Log.error("Error en actualizar categoria ", e.getMessage());
			e.getStackTrace();
			response.setMetadata("respuesta no ok", "-1", "Libro no actualizada");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> eliminar(Long id) {
		Log.info("Inicio metodo eliminar Libro...  ");
		LibroResponseRest response = new LibroResponseRest();
		try {
			libroDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Libro eliminada");

		} catch (Exception e) {
			Log.error("Error en eliminar Libro.. ", e.getMessage());
			e.getStackTrace();
			response.setMetadata("respuesta no ok", "-1", "Libro no eliminada");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

}
