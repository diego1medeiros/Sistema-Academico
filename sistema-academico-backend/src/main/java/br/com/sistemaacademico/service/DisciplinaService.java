package br.com.sistemaacademico.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.com.sistemaacademico.dto.DisciplinaDTO;
import br.com.sistemaacademico.entity.Curso;
import br.com.sistemaacademico.entity.Disciplina;
import br.com.sistemaacademico.exception.RegraNegocioException;
import br.com.sistemaacademico.repository.CursoRepository;
import br.com.sistemaacademico.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

	private final DisciplinaRepository disciplinaRepository;
	private final CursoRepository cursoRepository;

	/**
	 * Retorna todas as disciplinas cadastradas.
	 *
	 * @return lista de disciplinas
	 */
	public List<Disciplina> listarDisciplinas() {
		return disciplinaRepository.findAll();
	}

	/**
	 * Busca uma disciplina pelo identificador.
	 *
	 * @param id identificador da disciplina
	 * @return disciplina encontrada
	 * @throws RegraNegocioException caso a disciplina não exista
	 */
	public Disciplina buscarDisciplina(Long id) {
		return disciplinaRepository.findById(id).orElseThrow(() -> new RuntimeException("Disciplina não encontrada."));
	}

	/**
	 * Cadastra uma nova disciplina.
	 *
	 * @param dto dados da disciplina
	 * @return disciplina cadastrada
	 */
	public Disciplina cadastrarDisciplina(DisciplinaDTO dto) {

		Curso curso = cursoRepository.findById(dto.getCursoId())
				.orElseThrow(() -> new RegraNegocioException("Curso não encontrado."));

		Disciplina disciplina = new Disciplina();
		disciplina.setNome(dto.getNome());
		disciplina.setCurso(curso);

		return disciplinaRepository.save(disciplina);
	}

	/**
	 * Atualiza uma disciplina existente.
	 *
	 * @param id  identificador da disciplina
	 * @param dto novos dados
	 * @return disciplina atualizada
	 */
	/**
	 * Atualiza os dados de uma disciplina.
	 *
	 * @param dto objeto contendo os dados atualizados da disciplina
	 * @return disciplina atualizada
	 * @throws RegraNegocioException caso a disciplina ou o curso não sejam encontrados
	 */
	public Disciplina atualizarDisciplina(DisciplinaDTO dto) {

	    Disciplina disciplina = disciplinaRepository.findById(dto.getId())
	            .orElseThrow(() -> new RegraNegocioException("Disciplina não encontrada."));

	    Curso curso = cursoRepository.findById(dto.getCursoId())
	            .orElseThrow(() -> new RegraNegocioException("Curso não encontrado."));

	    disciplina.setNome(dto.getNome());
	    disciplina.setCurso(curso);

	    return disciplinaRepository.save(disciplina);
	}
	/**
	 * Exclui uma disciplina.
	 *
	 * @param id identificador da disciplina
	 */
	public void excluirDisciplina(Long id) {
		disciplinaRepository.deleteById(id);

	}

}