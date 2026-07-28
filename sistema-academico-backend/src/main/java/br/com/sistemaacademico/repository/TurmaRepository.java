package br.com.sistemaacademico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemaacademico.entity.Disciplina;
import br.com.sistemaacademico.entity.Turma;
import br.com.sistemaacademico.enun.StatusTurma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

	List<Turma> findByDisciplina(Disciplina disciplina);
	List<Turma> findByAtivoTrue();
	List<Turma> findByStatus(StatusTurma status);
}
