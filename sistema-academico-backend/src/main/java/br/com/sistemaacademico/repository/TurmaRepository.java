package br.com.sistemaacademico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sistemaacademico.entity.Disciplina;
import br.com.sistemaacademico.entity.Turma;
import br.com.sistemaacademico.enun.StatusTurma;
import jakarta.persistence.LockModeType;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

	List<Turma> findByDisciplina(Disciplina disciplina);
	List<Turma> findByAtivoTrue();
	List<Turma> findByStatus(StatusTurma status);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("""
	    SELECT t
	    FROM Turma t
	    WHERE t.id = :id
	""")
	Optional<Turma> buscarParaConfirmacao(@Param("id") Long id);
	
	@Modifying
	@Query("""
	    UPDATE Turma t
	    SET t.vagasDisponiveis = t.vagasDisponiveis - 1
	    WHERE t.id = :id
	      AND t.vagasDisponiveis > 0
	""")
	int consumirVaga(@Param("id") Long id);
	
}
