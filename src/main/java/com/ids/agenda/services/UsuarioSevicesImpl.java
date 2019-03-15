/**
 * 
 */
package com.ids.agenda.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ids.agenda.entities.User;
import com.ids.agenda.repositories.UsuarioRepository;

/**
 * @author lbermejo
 *
 */
@Service
public class UsuarioSevicesImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;
	
	public User loginUsuario(User usuario) {
		Optional<User> usuarioDB = repository.findByUsuarioIn(usuario.getUsuario());
		
		if( usuarioDB.isPresent() && 
				usuarioDB.get().getPassword().equals(usuario.getPassword()) ) {
			return usuarioDB.get();
		}
		return null;
	}

	public User validaUsuarioByEmail(User usuario) {
		Optional<User> usuarioDB = repository.findByEmailIn(usuario.getEmail());
		return usuarioDB.isPresent() ? usuarioDB.get() : null;	}
	
	public User validaUsuarioByUsername(User usuario) {
		Optional<User> usuarioDB = repository.findByUsuarioIn(usuario.getUsuario());
		return usuarioDB.isPresent() ? usuarioDB.get() : null;	}
	
	public User validaUsuarioByUsernameAndEmail(User usuario) {
		Optional<User> usuarioDB = repository.findByUsuarioAndEmailIn(usuario.getUsuario(), usuario.getEmail());
		return usuarioDB.isPresent() ? usuarioDB.get() : null;
	}
	
	@Override
	public User consultaUsuario(Long usuarioId) {
		Optional<User> usuario = repository.findById(usuarioId);
		return usuario.isPresent() ? usuario.get() : null; 
	}
	
	@Override
	public List<User> consultaUsuarios() {
		return repository.findAll();
	}

	@Override
	public void guardaUsuario(User usuario) {
		repository.saveAndFlush(usuario);
		
	}

	@Override
	public void borraUsuario(Long usuarioId) {
		repository.deleteById(usuarioId);
	}

	@Override
	public void actualizaUsuario(User usuario) {
		repository.saveAndFlush(usuario);
	}
}



