package br.com.sistemaacademico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemaacademico.entity.Curso;
import br.com.sistemaacademico.entity.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    List<Disciplina> findByCurso(Curso curso);
    

}
