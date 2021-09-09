package com.dari.books.service;

import java.util.List;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dari.books.model.Usuario;
import com.dari.books.model.dao.IUsuarioDao;


@Service
public class UsuarioService implements UserDetailsService {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		
		Usuario usuario = this.usuarioDao.findBynombreUsuario(username);
		
		if(usuario==null) {
			log.error("error, el usuario no existe");
			throw new UsernameNotFoundException("error, el usuario no existe");
			
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
		.stream()
		.map(role -> new SimpleGrantedAuthority(role.getNombre()))
		.peek(Authority -> log.info("Role: "+Authority.getAuthority()))
		.collect(Collectors.toList());
	
	
	return new User(usuario.getNombreUsuario(), usuario.getPassword(), usuario.getHabilitado(),true,true,true, authorities);
	
	}

	
		
		
	

	
}
