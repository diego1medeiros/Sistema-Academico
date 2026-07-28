package br.com.sistemaacademico.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import br.com.sistemaacademico.dto.MatriculaResponseDTO;
import br.com.sistemaacademico.dto.SolicitarMatriculaDTO;

@Service
public class MatriculaService {

	private final WebClient webClient;

	public MatriculaService(WebClient webClient) {
		super();
		this.webClient = webClient;
	}

	public List<MatriculaResponseDTO> listarMatriculas() {
		return webClient.get().uri("/matriculas").retrieve().bodyToFlux(MatriculaResponseDTO.class).collectList()
				.block();

	}

	public MatriculaResponseDTO matricularAluno(Long alunoId, Long turmaId) {

		SolicitarMatriculaDTO dto = new SolicitarMatriculaDTO();
		dto.setAlunoId(alunoId);
		dto.setTurmaId(turmaId);

		return webClient.post().uri("/matriculas").bodyValue(dto).retrieve().bodyToMono(MatriculaResponseDTO.class)
				.block();

	}

	public MatriculaResponseDTO confirmarMatricula(Long id) {

		return webClient.put().uri("/matriculas/{id}/confirmar", id).retrieve().bodyToMono(MatriculaResponseDTO.class)
				.block();

	}

	public MatriculaResponseDTO cancelarMatricula(Long id) {
		return webClient.put().uri("/matriculas/{id}/cancelar", id).retrieve().bodyToMono(MatriculaResponseDTO.class)
				.block();

	}

	public List<MatriculaResponseDTO> buscarPorAluno(Long id) {

		return webClient.get().uri("matriculas/aluno/{id}",id).retrieve().bodyToFlux(MatriculaResponseDTO.class).collectList()
				.block();

	}

	public List<MatriculaResponseDTO> buscarPorTurma(Long id) {
		return webClient.get().uri("matriculas/turma/{id}",id).retrieve().bodyToFlux(MatriculaResponseDTO.class).collectList()
				.block();
	}
}
