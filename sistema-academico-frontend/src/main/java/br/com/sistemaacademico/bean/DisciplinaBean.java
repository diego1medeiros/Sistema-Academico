package br.com.sistemaacademico.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.com.sistemaacademico.dto.CursoDTO;
import br.com.sistemaacademico.dto.DisciplinaDTO;
import br.com.sistemaacademico.exception.MensagemUtils;
import br.com.sistemaacademico.service.CursoService;
import br.com.sistemaacademico.service.DisciplinaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

/**
 * Bean responsável pelo gerenciamento da tela de disciplinas.
 *
 * <p>
 * Controla as operações realizadas pelo usuário na interface JSF, como
 * cadastro, edição, exclusão e carregamento de disciplinas.
 * </p>
 *
 * @author Diego Medeiros Jesus
 */
@Component
@RequiredArgsConstructor
@Getter
@Setter
@ViewScoped

public class DisciplinaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private final DisciplinaService disciplinaService;
	@Autowired
	private final CursoService cursoService;
	private List<CursoDTO> cursos;
	private List<DisciplinaDTO> disciplinas;
	private DisciplinaDTO disciplina = new DisciplinaDTO();

	/**
	 * Salva uma nova disciplina ou atualiza uma disciplina existente.
	 *
	 * <p>
	 * Quando o identificador da disciplina é nulo, uma nova disciplina é
	 * cadastrada. Caso contrário, realiza a atualização dos dados.
	 * </p>
	 *
	 * Trata mensagens de sucesso e erros retornados pela API.
	 */
	public void salvarDisciplina() {

		try {
			if (disciplina.getId() == null) {
				disciplinaService.salvarDisciplina(disciplina);
				MensagemUtils.info("Cadastro", "Disciplina cadastrado com sucesso.");
			} else {
				disciplinaService.atualizarDisciplina(disciplina);
				MensagemUtils.info("Atualizado", "Disciplina atualizado com sucesso.");
			}
			carregarDisciplinas();
			novaDisciplina();
		} catch (WebClientResponseException.BadRequest e) {

			MensagemUtils.erro("Erro", e.getResponseBodyAsString());

		} catch (Exception e) {

			MensagemUtils.erro("Erro", "Não foi possível salvar o disciplina.");
		}
	}

	/**
	 * Remove uma disciplina através do identificador informado.
	 *
	 * @param disciplina disciplina selecionada para exclusão
	 */
	public void excluirDisciplina(DisciplinaDTO disciplina) {

		try {
			disciplinaService.excluirDisciplina(disciplina.getId());
			carregarDisciplinas();
			MensagemUtils.info("Exclusão", "Disciplina excluído com sucesso.");
		} catch (Exception e) {
			MensagemUtils.erro("ERRO", "Não é possível Disciplina este curso porque existem matriculas vinculadas.");
		}

	}

	/**
	 * Carrega uma disciplina selecionada para edição.
	 *
	 * @param disciplina disciplina escolhida pelo usuário
	 */
	public void editarDisciplina(DisciplinaDTO disciplina) {
		this.disciplina = disciplinaService.buscarDisciplina(disciplina.getId());
	}

	/**
	 * Método executado automaticamente após a criação do Bean.
	 *
	 * Inicializa a tela carregando disciplinas cadastradas.
	 */
	@PostConstruct
	public void inicializar() {
		novaDisciplina();
		carregarDisciplinas();

	}

	/**
	 * Retorna a lista de cursos disponíveis para associação com uma disciplina.
	 *
	 * @return lista de cursos cadastrados
	 */
	public List<CursoDTO> getCursos() {
		if (cursos == null) {
			cursos = cursoService.listarCursos();
		}
		return cursos;
	}

	public List<DisciplinaDTO> Disciplinas() {
		if (disciplinas == null) {
			novaDisciplina();
		}
		return disciplinas;
	}

	/**
	 * Limpa o formulário para cadastro de uma nova disciplina.
	 */
	public void novaDisciplina() {
		disciplina = new DisciplinaDTO();
	}

	/**
	 * Atualiza a lista de disciplinas exibida na tela.
	 */
	public void carregarDisciplinas() {
		disciplinas = disciplinaService.listarDisciplinas();
	}

}
