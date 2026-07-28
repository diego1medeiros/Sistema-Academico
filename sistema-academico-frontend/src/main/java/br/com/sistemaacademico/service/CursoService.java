package br.com.sistemaacademico.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.sistemaacademico.dto.CursoDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {

	private final WebClient webClient;

	public List<CursoDTO> listarCursos() {
	CursoDTO[] cursos = webClient.get().uri("/cursos").retrieve().bodyToMono(CursoDTO[].class).block();
	return cursos!= null ? Arrays.asList(cursos) : List.of(); 

	}

	public CursoDTO salvarCurso(CursoDTO dto) {
		return webClient.post().uri("/cursos").bodyValue(dto).retrieve().bodyToMono(CursoDTO.class).block();

	}

	public CursoDTO atualizarCurso(CursoDTO dto) {
		return webClient.put().uri("/cursos/" + dto.getId()).bodyValue(dto).retrieve().bodyToMono(CursoDTO.class)
				.block();
	}

	public void excluirCurso(Long id) {
		webClient.delete().uri("/cursos/" + id).retrieve().toBodilessEntity().block();

	}
	
	public CursoDTO buscarCurso(Long id) {
		return webClient.get().uri("/cursos/{id}", id).retrieve().bodyToMono(CursoDTO.class).block();

	}

}
