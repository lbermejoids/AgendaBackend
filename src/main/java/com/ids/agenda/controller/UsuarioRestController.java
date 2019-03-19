/**
 * 
 */
package com.ids.agenda.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@CrossOrigin(origins="*")
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
	public ResponseEntity<User> consultaUsuario(@PathVariable(name="userId") Long usuarioId) {
		User usuario = service.consultaUsuario(usuarioId);
		
		if(usuario == null) {
			LOG.error("Usuario no encontrado. ");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		LOG.debug("Contacto encontrado: {}", usuario);
		return ResponseEntity.ok(usuario);
		
	}
	
	@PostMapping("/api/usuarios") //CONFLICT(409, "Conflict"),
	public  ResponseEntity<User> guardaUsuario(@RequestBody User usuario) { //DTO
		User userPrev = service.consultaUsuario(usuario.getId());
		if (userPrev == null ) {
			User u = service.guardaUsuario(usuario);
			LOG.debug("Usuario guardado");
			return ResponseEntity.ok(u);
		}else {
			LOG.error("Elemento guardado previamente. ");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/api/usuarios/{userId}")
	public ResponseEntity<String> borraUsuario(@PathVariable(name="userId") Long usuarioId) {
		try {
			service.borraUsuario(usuarioId);
			LOG.debug("Usuario borrado : {}",usuarioId);
			return ResponseEntity.ok("Elemento elimnado");
		}catch(Exception e){
			LOG.error("Error al borrar el elemento : {}", e);
			return new ResponseEntity<String>("Error elimando el elemento", HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PutMapping("/api/usuarios/{userId}")
	public ResponseEntity<User> actualizaUsuario(@RequestBody User usuario, //DTO
			@PathVariable(name="userId")Long usuarioId) {
		User userPrev = service.consultaUsuario(usuario.getId());
		if (userPrev == null ) {
			User u = service.guardaUsuario(usuario);
			LOG.debug("Usuario actualizado");
			return ResponseEntity.ok(u);
		}else {
			LOG.error("Elemento no guardado previamente. ");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/api/usuarios/login")
	public ResponseEntity<User> loginUsuarioByUsername(@RequestBody User usuario) { //DTO
		User user = service.validaUsuarioByUsername(usuario);
		if( user == null ) {
			LOG.error("Login incorrecto. ");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		LOG.debug("login usuario by Username: {}", user );
		return ResponseEntity.ok(user);
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
