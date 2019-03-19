/**
 * 
 */
package com.ids.agenda.services;

import java.util.List;

import com.ids.agenda.entities.User;

/**
 * @author lbermejo
 *
 */
public interface UsuarioService {

	List<User> consultaUsuarios();
	User consultaUsuario(Long usuarioId);
	User guardaUsuario(User usuario);
	void borraUsuario(Long usuarioId);
	User actualizaUsuario(User usuario);
	
	User loginUsuario(User usuario);
	User validaUsuarioByEmail(User usuario);
	User validaUsuarioByUsername(User usuario);
	User validaUsuarioByUsernameAndEmail(User usuario);
	
}
