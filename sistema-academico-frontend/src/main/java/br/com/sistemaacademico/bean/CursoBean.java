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

import java.io.Serializable;
import java.util.List;

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

	
	@PostConstruct
	public void inicializar() {
		novoCurso();
		carregarCursos();
	}

	public void salvarCurso() {

		if (curso.getId() == null) {
			cursoService.salvarCurso(curso);
			MensagemUtils.info("Cadastro", "Curso cadastrado com sucesso.");
		} else {
			cursoService.atualizarCurso(curso);
			MensagemUtils.info("Atualizado", "Curso atualizado com sucesso.");
		}
		novoCurso();
		carregarCursos();

	}

	public void excluirCurso(CursoDTO curso) {

		try {
			cursoService.excluirCurso(curso.getId());
			MensagemUtils.info("Exclusão", "Curso excluído com sucesso.");
			carregarCursos();

		} catch (Exception e) {
			MensagemUtils.erro("ERRO", "Não é possível excluir este curso porque existem disciplinas vinculadas.");
		}
	}
	
	public void editarCurso(CursoDTO curso) {
		this.curso = cursoService.buscarCurso(curso.getId());
	}

	public void carregarCursos() {
		cursos = cursoService.listarCursos();
	}

	public void novoCurso() {
		curso = new CursoDTO();

	}
}
