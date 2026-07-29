package br.com.sistemaacademico.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import br.com.sistemaacademico.dto.DisciplinaDTO;
import lombok.RequiredArgsConstructor;

/**
 * Serviço responsável pela comunicação entre o frontend JSF e a API REST de
 * disciplinas.
 *
 * <p>
 * Utiliza WebClient para realizar chamadas HTTP ao backend, permitindo
 * consultar, cadastrar, atualizar e excluir disciplinas.
 * </p>
 *
 * @author Diego Medeiros Jesus
 */
@Service
@RequiredArgsConstructor
public class DisciplinaService {

	private final WebClient webClient;

	/**
	 * Lista todas as disciplinas cadastradas.
	 *
	 * @return lista de disciplinas disponíveis
	 */
	public List<DisciplinaDTO> listarDisciplinas() {

		DisciplinaDTO[] disciplinas = webClient.get().uri("/disciplinas").retrieve().bodyToMono(DisciplinaDTO[].class)
				.block();
		return disciplinas != null ? Arrays.asList(disciplinas) : List.of();
	}

	/**
	 * Busca uma disciplina pelo identificador.
	 *
	 * @param id identificador da disciplina
	 * @return disciplina encontrada
	 */
	public DisciplinaDTO buscarDisciplina(Long id) {

		return webClient.get().uri("/disciplinas/{id}", id).retrieve().bodyToMono(DisciplinaDTO.class).block();
	}

	/**
	 * Realiza o cadastro de uma nova disciplina.
	 *
	 * @param disciplina dados da disciplina que será cadastrada
	 */
	public void salvarDisciplina(DisciplinaDTO disciplina) {
		webClient.post().uri("/disciplinas").bodyValue(disciplina).retrieve().bodyToMono(Void.class).block();

	}

	/**
	 * Atualiza os dados de uma disciplina existente.
	 *
	 * @param disciplina disciplina contendo os dados atualizados
	 */
	public void atualizarDisciplina(DisciplinaDTO disciplina) {
		webClient.put().uri("/disciplinas/{id}", disciplina.getId()).bodyValue(disciplina).retrieve()
				.bodyToMono(Void.class).block();

	}

	/**
	 * Remove uma disciplina pelo identificador informado.
	 *
	 * @param id identificador da disciplina
	 */
	public void excluirDisciplina(Long id) {
		webClient.delete().uri("/disciplinas/{id}", id).retrieve().bodyToMono(Void.class).block();
	}

}