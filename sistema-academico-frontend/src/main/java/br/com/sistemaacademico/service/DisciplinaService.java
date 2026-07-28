package br.com.sistemaacademico.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import br.com.sistemaacademico.dto.DisciplinaDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

	private final WebClient webClient;

	public List<DisciplinaDTO> listarDisciplinas() {

		DisciplinaDTO[] disciplinas = webClient.get().uri("/disciplinas").retrieve().bodyToMono(DisciplinaDTO[].class)
				.block();
		return disciplinas != null ? Arrays.asList(disciplinas) : List.of();
	}

	public DisciplinaDTO buscarDisciplina(Long id) {

		return webClient.get().uri("/disciplinas/{id}", id).retrieve().bodyToMono(DisciplinaDTO.class).block();
	}

	public void salvarDisciplina(DisciplinaDTO disciplina) {
		webClient.post().uri("/disciplinas").bodyValue(disciplina).retrieve().bodyToMono(Void.class).block();

	}

	public void atualizarDisciplina(DisciplinaDTO disciplina) {
		webClient.put().uri("/disciplinas/{id}", disciplina.getId()).bodyValue(disciplina).retrieve()
				.bodyToMono(Void.class).block();

	}

	public void excluirDisciplina(Long id) {
		webClient.delete().uri("/disciplinas/{id}", id).retrieve().bodyToMono(Void.class).block();
	}

}