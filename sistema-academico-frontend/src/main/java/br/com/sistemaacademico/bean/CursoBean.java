package br.com.sistemaacademico.bean;

import br.com.sistemaacademico.dto.CursoDTO;
import br.com.sistemaacademico.exception.MensagemUtils;
import br.com.sistemaacademico.service.CursoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.Serializable;
import java.util.List;

/**
 * Bean responsável pelo gerenciamento da tela de cursos.
 *
 * <p>
 * Realiza a comunicação entre a camada JSF e o backend através do
 * {@link CursoService}, permitindo cadastrar, atualizar, consultar e excluir
 * cursos.
 * </p>
 *
 * <p>
 * Utiliza o escopo {@link ViewScoped}, mantendo os dados enquanto o usuário
 * permanecer na mesma página.
 * </p>
 */
@Component
@ViewScoped
@RequiredArgsConstructor
@Getter
@Setter
public class CursoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CursoService cursoService;
	private CursoDTO curso;
	private List<CursoDTO> cursos;

	/**
	 * Método executado automaticamente após a criação do Bean.
	 *
	 * <p>
	 * Inicializa o formulário de curso e carrega os cursos cadastrados.
	 * </p>
	 */
	@PostConstruct
	public void inicializar() {
		novoCurso();
		carregarCursos();
	}

	/**
	 * Salva um curso no sistema.
	 *
	 * <p>
	 * Quando o curso não possui identificador, realiza um cadastro. Quando possui
	 * identificador, realiza uma atualização.
	 * </p>
	 *
	 * <p>
	 * As mensagens de retorno são exibidas utilizando {@link MensagemUtils}.
	 * </p>
	 */
	public void salvarCurso() {

		try {
			if (curso.getId() == null) {
				cursoService.salvarCurso(curso);
				MensagemUtils.info("Cadastro", "Curso cadastrado com sucesso.");
			} else {
				cursoService.atualizarCurso(curso);
				MensagemUtils.info("Atualizado", "Curso atualizado com sucesso.");
			}
			novoCurso();
			carregarCursos();

		} catch (WebClientResponseException.BadRequest e) {
			MensagemUtils.erro("Erro", e.getResponseBodyAsString());
		} catch (Exception e) {

			MensagemUtils.erro("Erro", "Não foi possível salvar o curso.");
		}
	}

	/**
	 * Exclui um curso do sistema.
	 *
	 * @param curso curso que será removido
	 *
	 *              <p>
	 *              A exclusão não é permitida quando existem disciplinas vinculadas
	 *              ao curso.
	 *              </p>
	 */
	public void excluirCurso(CursoDTO curso) {

		try {
			cursoService.excluirCurso(curso.getId());
			MensagemUtils.info("Exclusão", "Curso excluído com sucesso.");
			carregarCursos();

		} catch (Exception e) {
			MensagemUtils.erro("ERRO", "Não é possível excluir este curso porque existem disciplinas vinculadas.");
		}
	}

	/**
	 * Carrega um curso selecionado para edição.
	 *
	 * @param curso curso escolhido na tabela
	 */
	public void editarCurso(CursoDTO curso) {

		this.curso = cursoService.buscarCurso(curso.getId());

	}

	/**
	 * Busca todos os cursos cadastrados no backend.
	 */
	public void carregarCursos() {

		cursos = cursoService.listarCursos();

	}

	/**
	 * Limpa o formulário criando um novo objeto curso.
	 */
	public void novoCurso() {

		curso = new CursoDTO();

	}

}
