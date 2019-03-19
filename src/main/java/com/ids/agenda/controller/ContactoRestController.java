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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ids.agenda.entities.Contacto;
import com.ids.agenda.services.ContactoService;

import io.swagger.annotations.Api;
/**
 * @author lbermejo
 *
 */

@RestController
@CrossOrigin(origins="*",methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@Api(tags = "Agenda:Contactos", consumes = "application/json")
public class ContactoRestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioRestController.class);
	
	@Autowired
	private ContactoService service;	
	
	
	@GetMapping(path="/api/contactos/find/{name}",produces="application/json" )
	public List<Contacto> buscaContactos(
			@PathVariable(name="name") String nombreContacto){
		
		List<Contacto> contactos = service.consultaContactos(nombreContacto);
		LOG.debug("Lista de contactos {}", contactos); 
		return contactos;
	}
	
	@GetMapping(path="/api/contactos",produces="application/json" )
	public List<Contacto> consultaContactos(){
		List<Contacto> contactos = service.consultaContactos();
		LOG.debug("Lista de contactos {}", contactos); 
		return contactos;
	}
	
	@GetMapping("/api/contactos/{contactId}")
	public ResponseEntity<Contacto> consultaContacto(
			@PathVariable(name="contactId") Long contactoId) {
		Contacto contacto = service.consultaContacto(contactoId);
		if(contacto == null) {
			LOG.error("Elemento no encontrado. ");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		LOG.debug("Contacto encontrado: {}", contacto);
		return ResponseEntity.ok(contacto);
	}
	
	@PostMapping("/api/contactos")  //CONFLICT(409, "Conflict"),
	public ResponseEntity<Contacto> guardaContacto(@RequestBody Contacto contacto) { //DTO
		
		Contacto contPrev = service.validaContactoByAlias(contacto);
		if( contPrev == null ) {
			Contacto cont = service.guardaContacto(contacto);
			LOG.error("Elemento guardado ");
			return ResponseEntity.ok(cont);
		}else {
			LOG.error("Elemento guardado previamente. ");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/api/contactos/{contactId}")
	public ResponseEntity<String> borraContacto(@PathVariable(name="contactId") Long contactoId) {
		try{
			service.borraContacto(contactoId);
			LOG.debug("Contacto borrado : {}", contactoId);
			return ResponseEntity.ok("Elemento elimnado");
		}catch(Exception e) {
			LOG.error("Error al borrar el elemento : {}", e);
			return new ResponseEntity<String>("Error elimando el elemento", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/api/contactos/{contactId}")
	public ResponseEntity<Contacto> actualizaContacto(@RequestBody Contacto contacto,  //DTO 
			@PathVariable(name="contactId")Long contactoId) {
		Contacto contPrev = service.consultaContacto(contacto.getId());
		if( contPrev == null ) {
			Contacto cont = service.guardaContacto(contacto);
			LOG.debug("Contacto actualizado");
			return ResponseEntity.ok(cont);
		}else {
			LOG.error("Elemento no guardado previamente. ");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * Valida conctacto por alias
	 * Contacto validaContactoByEmail(Contacto contacto);
	 * 
	 * @param contacto
	 * @return
	 */
	@PostMapping("/api/contactos/valida")
	public Contacto validaContactoByAlias(@RequestBody Contacto contacto) { //DTO
		return service.validaContactoByAlias(contacto);
	}
	
	
}
