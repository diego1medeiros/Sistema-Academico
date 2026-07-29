package br.com.sistemaacademico.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.sistemaacademico.dto.CursoDTO;
import lombok.RequiredArgsConstructor;

/**
 * Serviço responsável pela comunicação entre o frontend JSF e a API REST de
 * cursos.
 *
 * <p>
 * Utiliza WebClient para executar chamadas HTTP ao backend, permitindo realizar
 * operações de consulta, cadastro, atualização e exclusão de cursos.
 * </p>
 *
 * @author Diego Medeiros Jesus
 */
@Service
@RequiredArgsConstructor
public class CursoService {

	private final WebClient webClient;

	/**
	 * Busca todos os cursos cadastrados no sistema.
	 *
	 * @return lista de cursos cadastrados
	 */
	public List<CursoDTO> listarCursos() {
		CursoDTO[] cursos = webClient.get().uri("/cursos").retrieve().bodyToMono(CursoDTO[].class).block();
		return cursos != null ? Arrays.asList(cursos) : List.of();

	}

	/**
	 * Cadastra um novo curso através da API REST.
	 *
	 * @param dto dados do curso que será cadastrado
	 * @return curso criado pelo backend
	 */
	public CursoDTO salvarCurso(CursoDTO dto) {
		return webClient.post().uri("/cursos").bodyValue(dto).retrieve().bodyToMono(CursoDTO.class).block();

	}

	/**
	 * Atualiza os dados de um curso existente.
	 *
	 * @param dto curso contendo os dados atualizados
	 * @return curso atualizado retornado pela API
	 */
	public CursoDTO atualizarCurso(CursoDTO dto) {
		return webClient.put().uri("/cursos/" + dto.getId()).bodyValue(dto).retrieve().bodyToMono(CursoDTO.class)
				.block();
	}

	/**
	 * Remove um curso do sistema.
	 *
	 * <p>
	 * A exclusão é realizada pelo identificador informado. As regras de integridade
	 * são tratadas pelo backend.
	 * </p>
	 *
	 * @param id identificador do curso
	 */
	public void excluirCurso(Long id) {
		webClient.delete().uri("/cursos/" + id).retrieve().toBodilessEntity().block();

	}

	/**
	 * Busca um curso pelo seu identificador.
	 *
	 * @param id identificador do curso
	 * @return curso encontrado
	 */
	public CursoDTO buscarCurso(Long id) {
		return webClient.get().uri("/cursos/{id}", id).retrieve().bodyToMono(CursoDTO.class).block();

	}

}
