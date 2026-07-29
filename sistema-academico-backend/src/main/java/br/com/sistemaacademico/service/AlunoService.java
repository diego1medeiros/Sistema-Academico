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

	private final AlunoRepository alunoRepository;
	private final MatriculaRepository matriculaRepository;

	
	/**
	 * Retorna todos os alunos cadastrados.
	 *
	 * @return lista de alunos
	 */
	public List<Aluno> listarAlunos() {
		return alunoRepository.findAll();
	}

	
	/**
	 * Busca um aluno pelo identificador.
	 *
	 * @param id identificador do aluno
	 * @return aluno encontrado
	 * @throws RuntimeException caso o aluno não seja encontrado
	 */
	public Aluno buscarAluno(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
	}

	
	/**
	 * Cadastra um novo aluno após validar CPF e e-mail.
	 *
	 * @param aluno dados do aluno
	 * @return aluno cadastrado
	 */
	public Aluno cadastrarAluno(Aluno aluno) {

		if (alunoRepository.existsByCpf(aluno.getCpf()))
			throw new RegraNegocioException("CPF já cadastrado.");

		if (alunoRepository.existsByEmail(aluno.getEmail()))
			throw new RegraNegocioException("Email já cadastrado.");

		return alunoRepository.save(aluno);
	}

	
	/**
	 * Atualiza os dados de um aluno.
	 *
	 * @param id identificador do aluno
	 * @param aluno novos dados
	 * @return aluno atualizado
	 */
	public Aluno atualizarAluno(Long id, Aluno aluno) {

		Aluno alunoExistente = buscarAluno(id);

		if (alunoRepository.existsByCpfAndIdNot(aluno.getCpf(), id)) {
		    throw new RegraNegocioException("Já existe um aluno cadastrado com esse CPF.");
		}

		if (alunoRepository.existsByEmailAndIdNot(aluno.getEmail(), id)) {
		    throw new RegraNegocioException("Já existe um aluno cadastrado com esse e-mail.");
		}

		alunoExistente.setNome(aluno.getNome());
		alunoExistente.setEmail(aluno.getEmail());
		alunoExistente.setCpf(aluno.getCpf());

		return alunoRepository.save(alunoExistente);
	}

	
	/**
	 * Exclui um aluno caso não possua matrículas ativas.
	 *
	 * @param id identificador do aluno
	 * @throws RegraNegocioException caso existam matrículas ativas
	 */
	@Transactional
	public void excluirAluno(Long id) {

		List<Matricula> matriculas = matriculaRepository.findByAlunoId(id);

		boolean possuiMatriculaAtiva = matriculas.stream().anyMatch(m -> m.getStatus() != StatusMatricula.CANCELADA);

		if (possuiMatriculaAtiva) {
			throw new RegraNegocioException("O aluno possui matrícula ativa e não pode ser excluído.");
		}
		matriculaRepository.deleteAll(matriculas);
		alunoRepository.deleteById(id);
	}

	/**
	 * Retorna a quantidade total de alunos cadastrados.
	 *
	 * @return total de alunos
	 */
	public Long contarAlunos() {
		return alunoRepository.count();
	}

}
