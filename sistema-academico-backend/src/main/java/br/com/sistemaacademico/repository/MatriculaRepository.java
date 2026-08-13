package br.com.sistemaacademico.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistemaacademico.entity.Aluno;
import br.com.sistemaacademico.entity.Matricula;
import br.com.sistemaacademico.entity.Turma;
import br.com.sistemaacademico.enun.StatusMatricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

	boolean existsByAlunoIdAndTurmaId(Long alunoId, Long turmaId);

	List<Matricula> findByAlunoId(Long alunoId);

	List<Matricula> findByTurmaId(Long turmaId);
	
	boolean existsByAlunoAndTurma(Aluno aluno, Turma turma);

	List<Matricula> findByAluno(Aluno aluno);

	List<Matricula> findByTurma(Turma turma);
	
	@Query("""
			SELECT m 
			FROM Matricula m
			ORDER BY m.id DESC
			""")
			List<Matricula> buscarUltimas(Pageable pageable);
	
	@Query("""
		       SELECT m 
		       FROM Matricula m
		       ORDER BY m.id DESC
		       """)
		List<Matricula> buscarUltimasMatriculas(Pageable pageable);

	 long countByTurmaIdAndStatus(Long turmaId, StatusMatricula status);
	
}