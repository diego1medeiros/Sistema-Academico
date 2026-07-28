package br.com.sistemaacademico.bean;

import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import br.com.sistemaacademico.dto.AlunoDTO;
import br.com.sistemaacademico.service.AlunoService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import br.com.sistemaacademico.exception.MensagemUtils;

@Component
@RequiredArgsConstructor
@ViewScoped
@Getter
@Setter
public class AlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private final AlunoService alunoService;

	private AlunoDTO aluno = new AlunoDTO();
	private List<AlunoDTO> alunos;

	@PostConstruct
	public void inicializar() {
		novoAluno();
		carregarAlunos();

	}

	public void salvarAluno() {

		if (aluno.getId() == null) {
			alunoService.salvarAluno(aluno);
			MensagemUtils.info("Cadastro", "Aluno cadastrado com sucesso.");
		} else {
			alunoService.atualizarAluno(aluno);
			MensagemUtils.info("Atualizado", "Aluno atualizado com sucesso.");
		}
		novoAluno();
		carregarAlunos();

	}

	public void excluirAluno(AlunoDTO aluno) {

		try {

			alunoService.excluirAluno(aluno.getId());
			MensagemUtils.info("Sucesso", "Aluno excluído com sucesso.");
			carregarAlunos();

		} catch (WebClientResponseException.BadRequest e) {

			MensagemUtils.erro("Erro", e.getResponseBodyAsString());

		} catch (WebClientResponseException e) {

			MensagemUtils.erro("Erro", e.getResponseBodyAsString());

		} catch (Exception e) {

			MensagemUtils.erro("Erro", "Erro ao excluir aluno.");

		}
	}

	public void editarAluno(AlunoDTO aluno) {
		this.aluno = alunoService.buscarAluno(aluno.getId());
	}

	public void carregarAlunos() {
		alunos = alunoService.listarAlunos();
	}

	public void novoAluno() {
		aluno = new AlunoDTO();
	}

}