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

	List<Contacto> consultaContactos();
	Contacto consultaContacto(Long contactoId);
	void guardaContacto(Contacto contacto);
	void borraContacto(Long contactoId);
	void actualizaContacto(Contacto contacto);
	
	Contacto validaContactoByEmail(Contacto contacto);
	Contacto validaContactoByAlias(Contacto contacto);
}
