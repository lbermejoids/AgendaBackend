/**
 * 
 */
package com.ids.agenda.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ids.agenda.entities.User;
import com.ids.agenda.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lbermejo
 *
 */

@RestController
@CrossOrigin
@Api(tags = "Agenda:Usuarios", consumes = "application/json")
public class UsuarioRestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioRestController.class);
	
	@Autowired
	private UsuarioService service;	
	
	@GetMapping("/api/usuarios")
	public List<User> consultaUsuario(){
		List<User> usuarios = service.consultaUsuarios();
		LOG.debug("Lista de usuarios {}", usuarios); 
		return usuarios;
	}
	
	@GetMapping("/api/usuarios/{userId}")
	public User consultaUsuario(@PathVariable(name="userId") Long usuarioId) {
		User usuario = service.consultaUsuario(usuarioId);
		LOG.debug("Usuario encontrado: {}", usuario);
		return usuario;
	}
	
	@PostMapping("/api/usuarios")
	public void guardaUsuario(@RequestBody User usuario) { //DTO
		service.guardaUsuario(usuario);
		LOG.debug("Usuario guardado");
	}
	
	@DeleteMapping("/api/usuarios/{userId}")
	public void borraUsuario(@PathVariable(name="userId") Long usuarioId) {
		service.borraUsuario(usuarioId);
		LOG.debug("Usuario borrado : {}",usuarioId);
		
	}
	
	@PutMapping("/api/usuarios/{userId}")
	public void actualizaUsuario(@RequestBody User usuario, //DTO
			@PathVariable(name="userId")Long usuarioId) {
		
		service.guardaUsuario(usuario);
		LOG.debug("Usuario actualizado");
	}
	
	@PostMapping("/api/usuarios/login")
	public User loginUsuarioByUsername(@RequestBody User usuario) { //DTO
		User user = service.validaUsuarioByUsername(usuario);
		LOG.debug("login usuario by Username: {}", user );
		
		return user;
	}
	
	@PostMapping("/api/usuarios/email")
	@ApiOperation(httpMethod = "POST", value = "Valida el email del usuario.")
	public User validaUsuarioByEmail(@RequestBody User usuario) { //DTO
		User user = service.validaUsuarioByEmail(usuario);
		LOG.debug("login usuario by mail: {}", user );
		
		return user;
	}
	
	/**
	 * 
	 * @param usuario
	 * @return
	 */
	@PostMapping("/api/usuarios/validad")
	@ApiOperation(httpMethod = "POST", value = "Valida el nombre de usuario junto el email.")
	public User loginUsuarioByUsernameAndMail(@RequestBody User usuario) { //DTO
		User user = service.validaUsuarioByUsernameAndEmail(usuario);
		LOG.debug("login usuario by UsernameAndMail : {}", user );
		
		return user;
	}
	
}
