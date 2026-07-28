package br.com.sistemaacademico.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaacademico.entity.Aluno;
import br.com.sistemaacademico.entity.Matricula;
import br.com.sistemaacademico.enun.StatusMatricula;
import br.com.sistemaacademico.exception.RegraNegocioException;
import br.com.sistemaacademico.repository.AlunoRepository;
import br.com.sistemaacademico.repository.MatriculaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

	private final AlunoRepository repository;
	private final MatriculaRepository matriculaRepository;
	 
	 
	public List<Aluno> listarAlunos() {
		return repository.findAll();
	}

	public Aluno buscarAluno(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
	}

	public Aluno cadastrarAluno(Aluno aluno) {

		if (repository.existsByCpf(aluno.getCpf()))
			throw new RegraNegocioException("CPF já cadastrado.");

		if (repository.existsByEmail(aluno.getEmail()))
			throw new RegraNegocioException("Email já cadastrado.");

		return repository.save(aluno);
	}

	public Aluno atualizarAluno(Long id, Aluno aluno) {
		Aluno existente = buscarAluno(id);
		existente.setNome(aluno.getNome());
		existente.setEmail(aluno.getEmail());
		existente.setCpf(aluno.getCpf());

		return repository.save(existente);
	}

	@Transactional
	public void excluirAluno(Long id) {

	    List<Matricula> matriculas = matriculaRepository.findByAlunoId(id);

	    boolean possuiMatriculaAtiva = matriculas.stream()
	            .anyMatch(m -> m.getStatus() != StatusMatricula.CANCELADA);

	    if (possuiMatriculaAtiva) {
	        throw new RegraNegocioException(
	                "O aluno possui matrícula ativa e não pode ser excluído.");
	    }
	    matriculaRepository.deleteAll(matriculas);
	    repository.deleteById(id);
	}

	public Long ContarAlunos() {
		return repository.count();
	}

}
