package br.com.sistemaacademico.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemaacademico.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	 List<Usuario> findByNomeContainingIgnoreCaseOrLoginContainingIgnoreCase(
	            String nome,
	            String login);
	
	Optional<Usuario> findByLoginIgnoreCase(String login);}

