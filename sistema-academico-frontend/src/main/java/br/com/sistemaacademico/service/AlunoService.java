package br.com.sistemaacademico.service;

import br.com.sistemaacademico.dto.AlunoDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Arrays;
import java.util.List;

/**
 * Serviço responsável pela comunicação entre o frontend JSF e a API REST de
 * alunos.
 *
 * <p>
 * Utiliza WebClient para realizar chamadas HTTP ao backend, permitindo listar,
 * cadastrar, consultar, atualizar e excluir alunos.
 * </p>
 *
 * @author Diego Medeiros Jesus
 */
@Service
@RequiredArgsConstructor
public class AlunoService {

	private final WebClient webClient;

	/**
	 * Consulta todos os alunos cadastrados no sistema.
	 *
	 * @return lista contendo os alunos cadastrados
	 */
	public List<AlunoDTO> listarAlunos() {
		AlunoDTO[] alunos = webClient.get().uri("/alunos").retrieve().bodyToMono(AlunoDTO[].class).block();
		return alunos != null ? Arrays.asList(alunos) : List.of();

	}

	/**
	 * Realiza o cadastro de um novo aluno.
	 *
	 * @param aluno dados do aluno que será cadastrado
	 * @return aluno cadastrado retornado pela API
	 */
	public AlunoDTO salvarAluno(AlunoDTO aluno) {
		return webClient.post().uri("/alunos").bodyValue(aluno).retrieve().bodyToMono(AlunoDTO.class).block();

	}

	/**
	 * Busca um aluno pelo seu identificador.
	 *
	 * @param id identificador do aluno
	 * @return dados do aluno encontrado
	 */
	public AlunoDTO buscarAluno(Long id) {
		return webClient.get().uri("/alunos/{id}", id).retrieve().bodyToMono(AlunoDTO.class).block();

	}

	/**
	 * Remove um aluno do sistema.
	 *
	 * <p>
	 * A exclusão depende das regras de negócio implementadas no backend.
	 * </p>
	 *
	 * @param id identificador do aluno
	 */
	public void excluirAluno(Long id) {
		webClient.delete().uri("/alunos/{id}", id).retrieve().bodyToMono(Void.class).block();

	}

	/**
	 * Atualiza os dados de um aluno existente.
	 *
	 * @param aluno aluno contendo os dados atualizados
	 * @return aluno atualizado retornado pela API
	 */
	public AlunoDTO atualizarAluno(AlunoDTO aluno) {
		return webClient.put().uri("/alunos/" + aluno.getId()).bodyValue(aluno).retrieve().bodyToMono(AlunoDTO.class)
				.block();

	}

}
