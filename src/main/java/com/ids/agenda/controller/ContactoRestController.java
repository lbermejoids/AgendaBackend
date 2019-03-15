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

import com.ids.agenda.entities.Contacto;
import com.ids.agenda.services.ContactoService;

import io.swagger.annotations.Api;
/**
 * @author lbermejo
 *
 */

@RestController
@CrossOrigin
@Api(tags = "Agenda:Contactos", consumes = "application/json")
public class ContactoRestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioRestController.class);
	
	@Autowired
	private ContactoService service;	
	
	//@GetMapping("/api/contactos")
	@GetMapping(path="/api/contactos",produces="application/json" )
	public List<Contacto> consultaContacto(){
		List<Contacto> contactos = service.consultaContactos();
		LOG.debug("Lista de contactos {}", contactos); 
		return contactos;
	}
	
	@GetMapping("/api/contactos/{contactId}")
	public Contacto consultaContacto(@PathVariable(name="contactId") Long contactoId) {
		Contacto contacto = service.consultaContacto(contactoId);
		LOG.debug("Contacto encontrado: {}", contacto);
		return contacto;
	}
	
	@PostMapping("/api/contactos")
	public void guardaContacto(@RequestBody Contacto contacto) { //DTO
		service.guardaContacto(contacto);
		LOG.debug("Contacto guardado");
	}
	
	@DeleteMapping("/api/contactos/{contactId}")
	public void borraContacto(@PathVariable(name="contactId") Long contactoId) {
		service.borraContacto(contactoId);
		LOG.debug("Contacto borrado : {}", contactoId);
	}
	
	@PutMapping("/api/contactos/{contactId}")
	public void actualizaContacto(@RequestBody Contacto contacto,  //DTO 
			@PathVariable(name="contactId")Long contactoId) {
		
		service.guardaContacto(contacto);
		LOG.debug("Contacto actualizado");
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
