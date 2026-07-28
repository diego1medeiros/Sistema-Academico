package br.com.sistemaacademico.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.com.sistemaacademico.dto.DisciplinaDTO;
import br.com.sistemaacademico.entity.Curso;
import br.com.sistemaacademico.entity.Disciplina;
import br.com.sistemaacademico.exception.RegraNegocioException;
import br.com.sistemaacademico.repository.CursoRepository;
import br.com.sistemaacademico.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

	private final DisciplinaRepository repository;

	private final CursoRepository cursoRepository;

	public List<Disciplina> listarDisciplinas() {
		return repository.findAll();
	}

	public Disciplina buscarDisciplina(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Disciplina não encontrada."));
	}

	public Disciplina cadastrarDisciplina(DisciplinaDTO dto) {

		Curso curso = cursoRepository.findById(dto.getCursoId())
				.orElseThrow(() -> new RegraNegocioException("Curso não encontrado"));

		Disciplina disciplina;

		if (dto.getId() != null) {
			disciplina = repository.findById(dto.getId())
					.orElseThrow(() -> new RegraNegocioException("Disciplina não encontrada."));
		} else {
			disciplina = new Disciplina();
		}

		disciplina.setNome(dto.getNome());
		disciplina.setCurso(curso);

		return repository.save(disciplina);
	}

	public void excluirDisciplina(Long id) {
		repository.deleteById(id);

	}

}