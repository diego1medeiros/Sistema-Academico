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

	@PostConstruct
	public void iniinicializar() {

		matricula = new MatriculaDTO();
		alunos = alunoService.listarAlunos();
		turmas = turmaService.listarTurmas();
		matriculas = matriculaService.listarMatriculas();

	}

	public void carregarMatriculas() {

		matriculas = matriculaService.listarMatriculas();
		alunos = alunoService.listarAlunos();
		turmas = turmaService.listarTurmas();

	}

	public void salvarMatricula() {

		if (alunoId == null || turmaId == null) {
			MensagemUtils.warr("Atenção", "Selecione um aluno e uma turma.");
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
	public void cancelarMatricula(Long id) {
		matriculaService.cancelarMatricula(id);
        MensagemUtils.info("Sucesso", "Matrícula cancelada com sucesso.");

		carregarMatriculas();

	}

	public void buscarPorAluno() {

		if (alunoFiltro == null) {
			MensagemUtils.warr("Atenção", "Selecione um aluno");

			return;
		}
		matriculas = matriculaService.buscarPorAluno(alunoFiltro);

		if (matriculas.isEmpty()) {
			MensagemUtils.erro("Consulta", "Aluno não possui matrículas");

		}

	}

	public void buscarPorTurma() {
		if (turmaFiltro == null) {
			MensagemUtils.warr("Atenção", "Selecione uma turma");
			return;
		}

		matriculas = matriculaService.buscarPorTurma(turmaFiltro);

		if (matriculas.isEmpty()) {
			MensagemUtils.erro("Consulta", "Turma não possui alunos matriculados");

		}

	}

	public void limparConsulta() {
		matriculas = null;
		alunoFiltro = null;
		turmaFiltro = null;
		carregarMatriculas();

	}

}