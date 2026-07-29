package br.com.sistemaacademico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sistemaacademico.entity.Curso;
import br.com.sistemaacademico.exception.RegraNegocioException;
import br.com.sistemaacademico.repository.CursoRepository;
import lombok.RequiredArgsConstructor;

/**
 * Serviço responsável pelo gerenciamento dos cursos.
 *
 * <p>
 * Realiza as operações de cadastro, consulta, atualização, exclusão e contagem
 * de cursos do sistema acadêmico.
 * </p>
 *
 * @author Diego Medeiros Jesus
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CursoService {

	private final CursoRepository repository;

	/**
	 * Retorna todos os cursos cadastrados.
	 *
	 * @return lista de cursos
	 */
	public List<Curso> listarCursos() {
		return repository.findAll();
	}

	/**
	 * Busca um curso pelo seu identificador.
	 *
	 * @param id identificador do curso
	 * @return curso encontrado
	 * @throws RuntimeException caso o curso não seja encontrado
	 */
	public Curso buscarCurso(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado."));
	}

	/**
	 * Cadastra um novo curso.
	 *
	 * @param curso dados do curso
	 * @return curso cadastrado
	 */
	public Curso cadastrarCurso(Curso curso) {

		if (repository.existsByNome(curso.getNome())) {
			throw new RegraNegocioException("Já existe um curso cadastrado com esse nome.");
		}

		return repository.save(curso);
	}

	/**
	 * Atualiza os dados de um curso existente.
	 *
	 * @param id    identificador do curso
	 * @param curso novos dados do curso
	 * @return curso atualizado
	 * @throws RuntimeException caso o curso não seja encontrado
	 */
	public Curso atualizarCurso(Long id, Curso curso) {

		Curso existente = buscarCurso(id);

		if (repository.existsByNomeAndIdNot(curso.getNome(), id)) {
			throw new RegraNegocioException("Já existe um curso cadastrado com esse nome.");
		}

		existente.setNome(curso.getNome());
		existente.setDescricao(curso.getDescricao());

		return repository.save(existente);
	}

	/**
	 * Remove um curso do sistema.
	 *
	 * @param id identificador do curso
	 */
	public void excluirCurso(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a quantidade total de cursos cadastrados.
	 *
	 * @return total de cursos
	 */
	public Long contarCursos() {
		return repository.count();
	}

}
