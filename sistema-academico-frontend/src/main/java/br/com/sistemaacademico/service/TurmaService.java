package br.com.sistemaacademico.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.sistemaacademico.dto.TurmaDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TurmaService {

	private final WebClient webClient;

	public List<TurmaDTO> listarTurmas() {
		TurmaDTO[] turmas = webClient.get().uri("/turmas").retrieve().bodyToMono(TurmaDTO[].class).block();
		return turmas != null ? Arrays.asList(turmas) : List.of();
	}

	public TurmaDTO buscarTurma(Long id) {
		return webClient.get().uri("/turmas/{id}", id).retrieve().bodyToMono(TurmaDTO.class).block();
	}

	public void salvarTurma(TurmaDTO turma) {
		webClient.post().uri("/turmas").bodyValue(turma).retrieve().bodyToMono(Void.class).block();

	}

	public void atualizarTurma(TurmaDTO turma) {
		webClient.put().uri("/turmas/{id}", turma.getId()).bodyValue(turma).retrieve().bodyToMono(Void.class).block();
	}

	public void excluirTurma(Long id) {
		webClient.delete().uri("/turmas/{id}", id).retrieve().bodyToMono(Void.class).block();
	}

}