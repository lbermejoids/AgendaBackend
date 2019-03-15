/**
 * 
 */
package com.ids.agenda.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ids.agenda.entities.Contacto;
import com.ids.agenda.repositories.ContactoRepository;

/**
 * @author lbermejo
 *
 */
@Service
public class ContactoSevicesImpl implements ContactoService{

	@Autowired
	private ContactoRepository repository;

	
	public Contacto validaContactoByEmail(Contacto contacto) {
		Optional<Contacto> contactoDB = repository.findByEmailIn(contacto.getEmail());
		return contactoDB.isPresent() ? contactoDB.get() : null;	
	}
	
	public Contacto validaContactoByAlias(Contacto contacto) {
		Optional<Contacto> contactoDB = repository.findByAliasIn(contacto.getAlias());
		return contactoDB.isPresent() ? contactoDB.get() : null;	
	}
	
	@Override
	public Contacto consultaContacto(Long contactoId) {
		Optional<Contacto> contacto = repository.findById(contactoId);
		return contacto.isPresent() ? contacto.get() : null;
	}

	@Override
	public List<Contacto> consultaContactos() {
		return repository.findAll();
	}

	@Override
	public void guardaContacto(Contacto contacto) {
		repository.saveAndFlush(contacto);
	}

	@Override
	public void borraContacto(Long contactoId) {
		repository.deleteById(contactoId);
	}

	@Override
	public void actualizaContacto(Contacto contacto) {
		repository.saveAndFlush(contacto);
	}
	
}