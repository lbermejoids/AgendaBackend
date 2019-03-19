/**
 * 
 */
package com.ids.agenda.services;

import java.util.List;

import com.ids.agenda.entities.Contacto;

/**
 * @author lbermejo
 *
 */
public interface ContactoService {

	List<Contacto> consultaContactos(String nombreContacto);
	List<Contacto> consultaContactos();
	Contacto consultaContacto(Long contactoId);
	Contacto guardaContacto(Contacto contacto);
	void borraContacto(Long contactoId);
	Contacto actualizaContacto(Contacto contacto);
	
	Contacto validaContactoByEmail(Contacto contacto);
	Contacto validaContactoByAlias(Contacto contacto);
}
