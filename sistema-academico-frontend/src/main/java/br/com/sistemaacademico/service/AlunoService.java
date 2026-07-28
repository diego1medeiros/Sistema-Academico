package br.com.sistemaacademico.service;

import br.com.sistemaacademico.dto.AlunoDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

	private final WebClient webClient;

	public List<AlunoDTO> listarAlunos() {
		AlunoDTO[] alunos = webClient.get().uri("/alunos").retrieve().bodyToMono(AlunoDTO[].class).block();
		return alunos != null ? Arrays.asList(alunos) : List.of();

	}

	public AlunoDTO salvarAluno(AlunoDTO aluno) {
		return webClient.post().uri("/alunos").bodyValue(aluno).retrieve().bodyToMono(AlunoDTO.class).block();

	}

	public AlunoDTO buscarAluno(Long id) {
		return webClient.get().uri("/alunos/{id}", id).retrieve().bodyToMono(AlunoDTO.class).block();

	}

	public void excluirAluno(Long id) {
		webClient.delete().uri("/alunos/{id}", id).retrieve().bodyToMono(AlunoDTO.class).block();

	}

	public AlunoDTO atualizarAluno(AlunoDTO aluno) {
		return webClient.put().uri("/alunos/" + aluno.getId()).bodyValue(aluno).retrieve().bodyToMono(AlunoDTO.class)
				.block();

	}

}
