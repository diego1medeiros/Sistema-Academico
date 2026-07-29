package br.com.sistemaacademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.sistemaacademico.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	boolean existsByNomeAndIdNot(String nome, Long id);

	boolean existsByNome(String nome);

}
