/**
 * 
 */
package com.ids.agenda.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ids.agenda.entities.User;

/**
 * @author lbermejo
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmailIn(String email);
	Optional<User> findByUsuarioIn(String usuario);
	Optional<User> findByUsuarioAndEmailIn(String email, String usuario);
}
