package br.com.sistemaacademico.bean;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.com.sistemaacademico.dto.AlunoDTO;
import br.com.sistemaacademico.dto.MatriculaDTO;
import br.com.sistemaacademico.dto.MatriculaResponseDTO;
import br.com.sistemaacademico.dto.TurmaDTO;
import br.com.sistemaacademico.exception.MensagemUtils;
import br.com.sistemaacademico.service.AlunoService;
import br.com.sistemaacademico.service.MatriculaService;
import br.com.sistemaacademico.service.TurmaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Bean responsável pelo gerenciamento das matrículas acadêmicas.
 *
 * <p>
 * Controla as operações realizadas na tela de matrículas, incluindo cadastro,
 * confirmação, cancelamento e consultas por aluno ou turma.
 * </p>
 *
 * <p>
 * Atua como camada de comunicação entre a interface JSF e os serviços
 * responsáveis pelas regras de negócio.
 * </p>
 *
 * @author Diego Medeiros Jesus
 */
@Getter
@Setter
@Component
@Named
@ViewScoped
@RequiredArgsConstructor

public class MatriculaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MatriculaService matriculaService;

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private TurmaService turmaService;
	private List<MatriculaResponseDTO> matriculas;
	private List<AlunoDTO> alunos;
	private List<TurmaDTO> turmas;
	private Long alunoId;
	private Long turmaId;
	private Long alunoFiltro;
	private Long turmaFiltro;
	private MatriculaDTO matricula;

	/**
	 * Inicializa os dados necessários para carregamento da tela.
	 *
	 * <p>
	 * Carrega alunos, turmas e matrículas existentes quando o Bean é criado pelo
	 * JSF.
	 * </p>
	 */
	@PostConstruct
	public void inicializar() {

		matricula = new MatriculaDTO();
		alunos = alunoService.listarAlunos();
		turmas = turmaService.listarTurmas();
		matriculas = matriculaService.listarMatriculas();

	}

	/**
	 * Atualiza os dados exibidos na tela de matrículas.
	 *
	 * <p>
	 * Realiza novamente a consulta de alunos, turmas e matrículas cadastradas.
	 * </p>
	 */
	public void carregarMatriculas() {

		matriculas = matriculaService.listarMatriculas();
		alunos = alunoService.listarAlunos();
		turmas = turmaService.listarTurmas();

	}

	/**
	 * Realiza o cadastro de uma nova matrícula.
	 *
	 * <p>
	 * Valida se aluno e turma foram selecionados antes de enviar a solicitação para
	 * o serviço.
	 * </p>
	 *
	 * <p>
	 * As regras de negócio como turma aberta, limite de vagas e matrícula duplicada
	 * são tratadas pelo backend.
	 * </p>
	 */
	public void salvarMatricula() {

		if (alunoId == null || turmaId == null) {
			MensagemUtils.warn("Atenção", "Selecione um aluno e uma turma.");
			return;
		}
		try {
			matriculaService.matricularAluno(alunoId, turmaId);
			MensagemUtils.info("Sucesso", "Aluno matriculado com sucesso!");
			limparConsulta();
			carregarMatriculas();

		} catch (Exception e) {
			MensagemUtils.erro("Erro", "Aluno já matriculado nesta turma");
		}
	}

	/**
	 * Confirma uma matrícula pendente.
	 *
	 * @param id identificador da matrícula
	 */
	public void confirmarMatricula(Long id) {

		try {

			matriculaService.confirmarMatricula(id);
			MensagemUtils.info("Sucesso", "Matrícula confirmada com sucesso.");
			carregarMatriculas();

		} catch (WebClientResponseException e) {

			MensagemUtils.erro("Erro", e.getResponseBodyAsString());

		} catch (Exception e) {

			MensagemUtils.erro("Erro", "Não foi possível confirmar a matrícula.");

		}
	}

	/**
	 * Cancela uma matrícula existente.
	 *
	 * @param id identificador da matrícula
	 */
	public void cancelarMatricula(Long id) {
		matriculaService.cancelarMatricula(id);
		MensagemUtils.info("Sucesso", "Matrícula cancelada com sucesso.");

		carregarMatriculas();

	}

	/**
	 * Busca matrículas realizadas por um aluno específico.
	 */
	public void buscarPorAluno() {

		if (alunoFiltro == null) {
			MensagemUtils.warn("Atenção", "Selecione um aluno");

			return;
		}
		matriculas = matriculaService.listarAluno(alunoFiltro);

		if (matriculas.isEmpty()) {
			MensagemUtils.erro("Consulta", "Aluno não possui matrículas");

		}
	}

	/**
	 * Busca matrículas vinculadas a uma turma específica.
	 */
	public void buscarPorTurma() {
		if (turmaFiltro == null) {
			MensagemUtils.warn("Atenção", "Selecione uma turma");
			return;
		}

		matriculas = matriculaService.listarTurma(turmaFiltro);

		if (matriculas.isEmpty()) {
			MensagemUtils.erro("Consulta", "Turma não possui alunos matriculados");

		}

	}

	/**
	 * Limpa os filtros de pesquisa e recarrega os dados da tela.
	 */
	public void limparConsulta() {
		matriculas = null;
		alunoFiltro = null;
		turmaFiltro = null;
		carregarMatriculas();

	}

}