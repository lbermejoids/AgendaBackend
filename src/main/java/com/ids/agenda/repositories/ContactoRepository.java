/**
 * 
 */
package com.ids.agenda.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ids.agenda.entities.Contacto;

/**
 * @author lbermejo
 *
 */
@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long>{
	
	Optional<Contacto> findByEmailIn(String email);
	Optional<Contacto> findByAliasIn(String alias);
	List<Contacto> findByNombreIn(String nombre);
	
}
