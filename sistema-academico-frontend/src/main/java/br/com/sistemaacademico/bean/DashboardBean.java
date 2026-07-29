package br.com.sistemaacademico.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.sistemaacademico.dto.MatriculaResponseDTO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;

/**
 * Bean responsável pelo gerenciamento dos dados exibidos no Dashboard do
 * sistema acadêmico.
 *
 * <p>
 * Este componente integra a camada JSF com a API REST do backend, realizando
 * consultas para obter informações resumidas do sistema, como quantidade de
 * alunos, cursos, turmas, matrículas e últimas matrículas realizadas.
 * </p>
 *
 * <p>
 * O escopo {@link ViewScoped} mantém os dados enquanto o usuário permanece na
 * mesma página do dashboard.
 * </p>
 */
@Component
@ViewScoped
@Getter
public class DashboardBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Quantidade total de alunos cadastrados.
	 */
	private Long totalAlunos;

	/**
	 * Quantidade total de cursos cadastrados.
	 */
	private Long totalCursos;

	/**
	 * Quantidade total de turmas cadastradas.
	 */
	private Long totalTurmas;

	/**
	 * Quantidade total de matrículas cadastradas.
	 */
	private Long totalMatriculas;

	/**
	 * Lista contendo as últimas matrículas realizadas no sistema.
	 */
	private List<MatriculaResponseDTO> ultimasMatriculas;

	/**
	 * Cliente HTTP utilizado para comunicação com a API REST do backend.
	 */
	private final WebClient webClient;

	/**
	 * Construtor responsável pela injeção do WebClient.
	 *
	 * @param webClient cliente HTTP para chamadas à API REST
	 */
	public DashboardBean(WebClient webClient) {
		this.webClient = webClient;
	}

	/**
	 * Método executado automaticamente após a criação do Bean.
	 *
	 * <p>
	 * Realiza o carregamento inicial dos dados apresentados no dashboard.
	 * </p>
	 */
	@PostConstruct
	public void iniciar() {

		carregarDados();

	}

	/**
	 * Carrega os dados estatísticos utilizados pelo dashboard.
	 *
	 * <p>
	 * Realiza chamadas para os endpoints da API REST responsáveis por retornar os
	 * totais de alunos, cursos, turmas e matrículas, além de buscar as últimas
	 * matrículas cadastradas.
	 * </p>
	 */
	public void carregarDados() {

		totalAlunos = buscarQuantidade("/alunos/count");
		totalCursos = buscarQuantidade("/cursos/count");
		totalTurmas = buscarQuantidade("/turmas/count");
		totalMatriculas = buscarQuantidade("/matriculas/count");

		ultimasMatriculas = webClient.get().uri("/matriculas/ultimas").retrieve().bodyToFlux(MatriculaResponseDTO.class)
				.collectList().block();

	}

	/**
	 * Realiza uma consulta na API para obter uma quantidade numérica.
	 *
	 * @param uri endereço do endpoint responsável pelo retorno da quantidade
	 * @return quantidade retornada pela API
	 */
	private Long buscarQuantidade(String uri) {

		return webClient.get().uri(uri).retrieve().bodyToMono(Long.class).block();

	}

}