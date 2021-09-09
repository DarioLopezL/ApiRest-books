package com.dari.books.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dari.books.model.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	public Usuario findBynombreUsuario(String nombreUsuario);
	
	@Query("select u from Usuario u where u.nombreUsuario=?1")
	public Usuario findByIdnombreUsuariov2(String nombreUsuario);
	
}
