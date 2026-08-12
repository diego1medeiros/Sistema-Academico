package br.com.sistemaacademico.bean;

import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import br.com.sistemaacademico.dto.AlunoDTO;
import br.com.sistemaacademico.dto.EnderecoDTO;
import br.com.sistemaacademico.service.AlunoService;
import br.com.sistemaacademico.utils.BuscaCep;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import br.com.sistemaacademico.exception.MensagemUtils;

/**
 * Bean responsável pelo gerenciamento da tela de cadastro de alunos.
 *
 * <p>
 * Realiza a comunicação entre a interface JSF e o backend através do
 * {@link AlunoService}, permitindo listar, cadastrar, atualizar e excluir
 * alunos.
 * </p>
 *
 * <p>
 * Utiliza o escopo {@link ViewScoped}, mantendo os dados enquanto o usuário
 * permanecer na mesma tela.
 * </p>
 */
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

	/**
	 * Método executado automaticamente após a criação do Bean.
	 *
	 * <p>
	 * Inicializa o formulário de aluno e realiza o carregamento dos alunos
	 * cadastrados.
	 * </p>
	 */
	@PostConstruct
	public void inicializar() {
		novoAluno();
		carregarAlunos();

	}

	/**
	 * Salva um aluno no sistema.
	 *
	 * <p>
	 * Caso o aluno possua ID, realiza uma atualização. Caso contrário, realiza um
	 * novo cadastro.
	 * </p>
	 *
	 * <p>
	 * As mensagens de sucesso ou erro são exibidas utilizando a classe
	 * {@link MensagemUtils}.
	 * </p>
	 */
	public void salvarAluno() {

		try {

			if (aluno.getId() == null) {
				alunoService.salvarAluno(aluno);
				MensagemUtils.info("Cadastro", "Aluno cadastrado com sucesso.");
			} else {
			
				alunoService.atualizarAluno(aluno);
				MensagemUtils.info("Atualizado", "Aluno atualizado com sucesso.");
			}

			novoAluno();
			carregarAlunos();

		} catch (WebClientResponseException.BadRequest e) {

			MensagemUtils.erro("Erro", e.getResponseBodyAsString());

		} catch (Exception e) {

			MensagemUtils.erro("Erro", "Não foi possível salvar o aluno.");
		}
	}

	/**
	 * Exclui um aluno cadastrado.
	 *
	 * @param aluno aluno que será removido do sistema
	 *
	 *              <p>
	 *              Caso exista alguma regra de negócio impedindo a exclusão, a
	 *              mensagem retornada pelo backend será apresentada ao usuário.
	 *              </p>
	 */
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

	/**
	 * Carrega os dados de um aluno selecionado para edição.
	 *
	 * @param aluno aluno escolhido na tabela
	 */
	public void editarAluno(AlunoDTO aluno) {

		this.aluno = alunoService.buscarAluno(aluno.getId());

	}
	
	
	// busca Cep do cliente
			public void encontraCEP() {
				BuscaCep buscaCep = new BuscaCep(aluno.getEndereco().getCep());
				
				
				System.out.println("CEP informado: " + aluno.getEndereco().getCep());
				
				  System.out.println("Resultado CEP: " + buscaCep.getResultado());
				    System.out.println("Rua: " + buscaCep.getLogradouro());
				    System.out.println("Bairro: " + buscaCep.getBairro());
				    System.out.println("Cidade: " + buscaCep.getCidade());
				    System.out.println("Estado: " + buscaCep.getEstado());

				if (buscaCep.getResultado() == 1) {
					aluno.getEndereco().setRua(buscaCep.getTipoLogradouro() + " " + buscaCep.getLogradouro());
					aluno.getEndereco().setEstado(buscaCep.getEstado());
					aluno.getEndereco().setCidade(buscaCep.getCidade());
					aluno.getEndereco().setBairro(buscaCep.getBairro());
				} else {
					MensagemUtils.erro("Erro","CEP não foi encontrado");
				}
			}

	/**
	 * Consulta todos os alunos cadastrados no backend.
	 */
	public void carregarAlunos() {

		alunos = alunoService.listarAlunos();

	}

	/**
	 * Limpa o formulário criando um novo objeto aluno.
	 */
	public void novoAluno() {
	    aluno = new AlunoDTO();
	    aluno.setEndereco(new EnderecoDTO());
	
	}

}