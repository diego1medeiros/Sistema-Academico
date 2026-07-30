package br.com.sistemaacademico.service;

import java.util.List;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import br.com.sistemaacademico.dto.MatriculaResponseDTO;
import br.com.sistemaacademico.dto.SolicitarMatriculaDTO;
import br.com.sistemaacademico.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;

/**
 * Serviço responsável pelo gerenciamento das matrículas acadêmicas.
 *
 * <p>
 * Contém as regras de negócio relacionadas ao processo de matrícula,
 * confirmação, cancelamento e consultas de matrículas.
 * </p>
 *
 * @author Diego Medeiros Jesus
 */
@Service
@RequiredArgsConstructor
public class MatriculaService {

	private final WebClient webClient;

	/**
	 * Retorna todas as matrículas cadastradas.
	 *
	 * @return lista completa de matrículas
	 */
	public List<MatriculaResponseDTO> listarMatriculas() {
		return webClient.get().uri("/matriculas").retrieve().bodyToFlux(MatriculaResponseDTO.class).collectList()
				.block();

	}

	/**
	 * Realiza o cadastro de uma solicitação de matrícula.
	 *
	 * <p>
	 * O método valida se o aluno e a turma existem, verifica se a turma está
	 * aberta, controla disponibilidade de vagas e impede matrícula duplicada.
	 * </p>
	 *
	 * @param alunoId identificador do aluno
	 * @param turmaId identificador da turma
	 *
	 * @return dados da matrícula criada
	 *
	 * @throws RegraNegocioException caso a turma esteja fechada, sem vagas ou o
	 *                               aluno já esteja matriculado
	 */
	

	public MatriculaResponseDTO matricularAluno(Long alunoId, Long turmaId) {

	    SolicitarMatriculaDTO dto = new SolicitarMatriculaDTO();
	    dto.setAlunoId(alunoId);
	    dto.setTurmaId(turmaId);

	    try {
	        return webClient.post()
	                .uri("/matriculas")
	                .bodyValue(dto)
	                .retrieve()
	                .bodyToMono(MatriculaResponseDTO.class)
	                .block();

	    } catch (WebClientResponseException e) {
	        throw new RegraNegocioException(e.getResponseBodyAsString());
	    }
	}

	

	/**
	 * Confirma uma matrícula pendente.
	 *
	 * <p>
	 * Ao confirmar a matrícula, uma vaga disponível da turma é consumida e o status
	 * da matrícula é alterado para CONFIRMADA.
	 * </p>
	 *
	 * @param id identificador da matrícula
	 *
	 * @return matrícula atualizada
	 *
	 * @throws RegraNegocioException caso a matrícula já esteja confirmada ou não
	 *                               existam vagas disponíveis
	 */
	public MatriculaResponseDTO confirmarMatricula(Long id) {

		return webClient.put().uri("/matriculas/{id}/confirmar", id).retrieve().bodyToMono(MatriculaResponseDTO.class)
				.block();

	}

	/**
	 * Cancela uma matrícula existente.
	 *
	 * <p>
	 * Caso a matrícula esteja confirmada, a vaga da turma é devolvida.
	 * </p>
	 *
	 * @param id identificador da matrícula
	 *
	 * @return matrícula com status CANCELADA
	 */
	public MatriculaResponseDTO cancelarMatricula(Long id) {
		return webClient.put().uri("/matriculas/{id}/cancelar", id).retrieve().bodyToMono(MatriculaResponseDTO.class)
				.block();

	}

	/**
	 * Lista todas as matrículas realizadas por um aluno.
	 *
	 * @param id identificador do aluno
	 *
	 * @return lista de matrículas do aluno informado
	 */
	public List<MatriculaResponseDTO> listarAluno(Long id) {

		return webClient.get().uri("matriculas/aluno/{id}", id).retrieve().bodyToFlux(MatriculaResponseDTO.class)
				.collectList().block();

	}

	/**
	 * Lista todas as matrículas pertencentes a uma turma.
	 *
	 * @param id identificador da turma
	 *
	 * @return lista de alunos matriculados na turma
	 */
	public List<MatriculaResponseDTO> listarTurma(Long id) {
		return webClient.get().uri("matriculas/turma/{id}", id).retrieve().bodyToFlux(MatriculaResponseDTO.class)
				.collectList().block();
	}
}
