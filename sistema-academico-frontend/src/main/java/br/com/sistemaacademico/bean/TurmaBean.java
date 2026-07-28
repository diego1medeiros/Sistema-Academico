package br.com.sistemaacademico.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sistemaacademico.dto.DisciplinaDTO;
import br.com.sistemaacademico.dto.TurmaDTO;
import br.com.sistemaacademico.exception.MensagemUtils;
import br.com.sistemaacademico.service.DisciplinaService;
import br.com.sistemaacademico.service.TurmaService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@Scope("view")
@RequiredArgsConstructor
@Getter
@Setter
public class TurmaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private final TurmaService turmaService;
	@Autowired
	private final DisciplinaService disciplinaService;
	private List<TurmaDTO> turmas;
	private List<DisciplinaDTO> disciplinas;
	private TurmaDTO turma = new TurmaDTO();

	@PostConstruct
	public void inicializar() {
		novoTurma();
		listarTurma();
	}

	public void editarTurma(TurmaDTO turma) {
		this.turma = turmaService.buscarTurma(turma.getId());
	}

	public void salvarTurma() {

		if (turma.getId() == null) {
			turmaService.salvarTurma(turma);
			MensagemUtils.info("Cadastro", "Turma cadastrado com sucesso.");
		} else {
			turmaService.atualizarTurma(turma);
			MensagemUtils.info("Atualizar", "Turma atualizada com sucesso.");

		}
		listarTurma();
		novoTurma();
	}

	public void excluirTurma(TurmaDTO turma) {
		try {
			turmaService.excluirTurma(turma.getId());
			MensagemUtils.info("Exclusão", "Turma excluído com sucesso.");

		} catch (Exception e) {
			MensagemUtils.erro("Erro", "Não é possível excluir este turma porque existem disciplinas vinculadas.");
		}
		listarTurma();
	}

	public List<TurmaDTO> getTurmas() {
		if (turmas == null) {
			listarTurma();
		}
		return turmas;
	}

	public List<DisciplinaDTO> getDisciplinas() {
		if (disciplinas == null) {
			disciplinas = disciplinaService.listarDisciplinas();
		}
		return disciplinas;
	}

	public void listarTurma() {
		turmas = turmaService.listarTurmas();
	}

	public void novoTurma() {
		turma = new TurmaDTO();
	}
}