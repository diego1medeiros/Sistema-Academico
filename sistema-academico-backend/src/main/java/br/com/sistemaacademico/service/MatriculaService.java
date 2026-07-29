package br.com.sistemaacademico.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.sistemaacademico.dto.MatriculaResponseDTO;
import br.com.sistemaacademico.entity.Aluno;
import br.com.sistemaacademico.entity.Matricula;
import br.com.sistemaacademico.entity.Turma;
import br.com.sistemaacademico.enun.StatusMatricula;
import br.com.sistemaacademico.enun.StatusTurma;
import br.com.sistemaacademico.exception.RegraNegocioException;
import br.com.sistemaacademico.repository.AlunoRepository;
import br.com.sistemaacademico.repository.MatriculaRepository;
import br.com.sistemaacademico.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


/**
 * Serviço responsável pelo gerenciamento das matrículas acadêmicas.
 *
 * <p>Implementa as principais regras de negócio relacionadas ao processo de
 * matrícula de alunos em turmas, incluindo cadastro, confirmação,
 * cancelamento, consultas e estatísticas.</p>
 *
 * <p>Regras implementadas:</p>
 * <ul>
 *     <li>Permite matrícula apenas em turmas abertas.</li>
 *     <li>Impede matrícula em turmas sem vagas disponíveis.</li>
 *     <li>Impede matrícula duplicada do mesmo aluno na mesma turma.</li>
 *     <li>Atualiza automaticamente o número de vagas ao confirmar ou cancelar uma matrícula.</li>
 * </ul>
 *
 * @author Diego Medeiros Jesus
 * 
 */

@Service
@RequiredArgsConstructor
@Transactional
public class MatriculaService {

	private final MatriculaRepository matriculaRepository;
	private final AlunoRepository alunoRepository;
	private final TurmaRepository turmaRepository;

	
	
	/**
	 * Realiza o cadastro de uma nova matrícula para um aluno em uma turma.
	 *
	 * <p>A matrícula é criada inicialmente com o status {@code PENDENTE}.</p>
	 *
	 * <p>Antes da criação são verificadas as seguintes regras:</p>
	 * <ul>
	 *     <li>A turma deve estar aberta.</li>
	 *     <li>A turma deve possuir vagas disponíveis.</li>
	 *     <li>O aluno não pode estar matriculado na mesma turma.</li>
	 * </ul>
	 *
	 * @param alunoId identificador do aluno
	 * @param turmaId identificador da turma
	 * @return dados da matrícula criada
	 * @throws RegraNegocioException quando alguma regra de negócio for violada
	 */
	@Transactional
	public MatriculaResponseDTO cadastrarMatricular(Long alunoId, Long turmaId) {

		Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
		Turma turma = turmaRepository.findById(turmaId).orElseThrow(() -> new RuntimeException("Turma não encontrada"));

		if (turma.getStatus() != StatusTurma.ABERTA) {
			throw new RegraNegocioException("Turma não está aberta");
		}
		if (turma.getVagasDisponiveis() <= 0) {
			throw new RegraNegocioException("Não há vagas disponíveis.");
		}

		if (matriculaRepository.existsByAlunoIdAndTurmaId(alunoId, turmaId)) {
			throw new RegraNegocioException("Aluno já matriculado nesta turma");
		}

		Matricula matricula = new Matricula();
		matricula.setAluno(aluno);
		matricula.setTurma(turma);
		matricula.setStatus(StatusMatricula.PENDENTE);
		matricula.setDataMatricula(LocalDateTime.now());
		Matricula matriculaNova = matriculaRepository.save(matricula);

		return converterParaDTO(matriculaNova);

	}

	
	/**
	 * Confirma uma matrícula pendente.
	 *
	 * <p>Ao confirmar uma matrícula, uma vaga é consumida da turma e
	 * o status passa para {@code CONFIRMADA}.</p>
	 *
	 * @param id identificador da matrícula
	 * @return matrícula confirmada
	 * @throws RegraNegocioException caso a matrícula já esteja confirmada
	 *                               ou a turma não possua vagas disponíveis
	 */
	public MatriculaResponseDTO confirmarMatricula(Long id) {

		Matricula matricula = matriculaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

		if (matricula.getStatus() == StatusMatricula.CONFIRMADA) {

			throw new RegraNegocioException("Matrícula já confirmada");
		}

		Turma turma = matricula.getTurma();

		if (turma.getVagasDisponiveis() <= 0) {
			throw new RegraNegocioException("Sem vagas");
		}

		turma.setVagasDisponiveis(turma.getVagasDisponiveis() - 1);
		matricula.setStatus(StatusMatricula.CONFIRMADA);

		turmaRepository.save(turma);
		Matricula matriculaNova = matriculaRepository.save(matricula);

		return converterParaDTO(matriculaNova);

	}

	
	/**
	 * Cancela uma matrícula.
	 *
	 * <p>Caso a matrícula esteja confirmada, a vaga é devolvida para a turma.</p>
	 *
	 * @param id identificador da matrícula
	 * @return matrícula cancelada
	 * @throws RuntimeException caso a matrícula já esteja cancelada
	 */
	public MatriculaResponseDTO cancelarMatricula(Long id) {

		Matricula matricula = matriculaRepository.findById(id)
				.orElseThrow(() -> new RegraNegocioException("Matrícula não encontrada"));

		if (matricula.getStatus() == StatusMatricula.CANCELADA) {
			throw new RegraNegocioException("Matrícula já cancelada");

		}

		if (matricula.getStatus() == StatusMatricula.CONFIRMADA) {

			Turma turma = matricula.getTurma();
			turma.setVagasDisponiveis(turma.getVagasDisponiveis() + 1);
			turmaRepository.save(turma);

		}
		matricula.setStatus(StatusMatricula.CANCELADA);
		Matricula matriculaNova = matriculaRepository.save(matricula);

		return converterParaDTO(matriculaNova);

	}

	
	/**
	 * Lista todas as matrículas de um aluno.
	 *
	 * @param id identificador do aluno
	 * @return lista de matrículas do aluno
	 */
	public List<MatriculaResponseDTO> listarAluno(Long id) {
		return matriculaRepository.findByAlunoId(id).stream().map(this::converterParaDTO).toList();

	}

	
	/**
	 * Lista todas as matrículas de uma turma.
	 *
	 * @param id identificador da turma
	 * @return lista de matrículas da turma
	 */
	public List<MatriculaResponseDTO> listarTurma(Long id) {
		return matriculaRepository.findByTurmaId(id).stream().map(this::converterParaDTO).toList();

	}

	
	/**
	 * Converte uma entidade  Matricula para seu DTO de resposta.
	 *
	 * @param matricula entidade de matrícula
	 * @return DTO contendo as informações da matrícula
	 */
	private MatriculaResponseDTO converterParaDTO(Matricula matricula) {

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return new MatriculaResponseDTO(

				matricula.getId(),
				matricula.getAluno().getId(),
				matricula.getAluno().getNome(),
				matricula.getTurma().getId(),
				matricula.getTurma().getDisciplina().getNome(),
				matricula.getTurma().getDisciplina().getCurso().getNome(),
				matricula.getStatus(),
				matricula.getDataMatricula().format(formatter)
		);

	}
	
	
	/**
	 * Retorna todas as matrículas cadastradas.
	 *
	 * @return lista de matrículas
	 */
	public List<Matricula> listarMatriculas() {
		return matriculaRepository.findAll();
	}

	
	/**
	 * Retorna a quantidade total de matrículas cadastradas.
	 *
	 * @return total de matrículas
	 */
	public Long contarMatriculas() {
		return matriculaRepository.count();
	}

	
	/**
	 * Retorna as cinco matrículas mais recentes.
	 *
	 * <p>Utilizado principalmente pelo dashboard do sistema.</p>
	 *
	 * @return lista das cinco últimas matrículas realizadas
	 */
	public List<MatriculaResponseDTO> ultimasMatriculas(){
		return matriculaRepository.buscarUltimas(
		        PageRequest.of(0,5)
		)
		.stream()
		.map(this::converterParaDTO)
		.toList();


		}
	

}
