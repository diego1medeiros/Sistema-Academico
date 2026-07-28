package br.com.sistemaacademico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sistemaacademico.entity.Curso;
import br.com.sistemaacademico.repository.CursoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {

	private final CursoRepository repository;

	public List<Curso> listarCursos() {
		return repository.findAll();
	}

	public Curso buscarCurso(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado."));
	}

	public Curso cadastrarCurso(Curso curso) {
		return repository.save(curso);
	}

	public Curso atualizarCurso(Long id, Curso curso) {

		Curso existente = buscarCurso(id);

		existente.setNome(curso.getNome());
		existente.setDescricao(curso.getDescricao());

		return repository.save(existente);

	}

	public void excluirCurso(Long id) {
		repository.deleteById(id);
	}

	public Long contarCursos() {
		return repository.count();
	}

}
