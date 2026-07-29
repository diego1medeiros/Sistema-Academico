package br.com.sistemaacademico.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import br.com.sistemaacademico.dto.TurmaDTO;
import br.com.sistemaacademico.entity.Turma;
import br.com.sistemaacademico.service.TurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controlador responsável pelo gerenciamento das turmas.
 *
 * <p>
 * Disponibiliza os endpoints para cadastro, consulta, atualização, exclusão e
 * contagem de turmas do sistema acadêmico.
 * </p>
 *
 * @author Diego Medeiros Jesus
 * @since 1.0
 */
@RestController
@RequestMapping("/turmas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TurmaController {

	private final TurmaService turmaService;
	private final ModelMapper mapper;

	/**
	 * Retorna a lista de todas as turmas cadastradas.
	 *
	 * @return lista de turmas
	 */
	@GetMapping
	public List<TurmaDTO> listarTurmas() {
		return turmaService.listarTurma().stream().map(t -> {
			TurmaDTO turmaDTO = mapper.map(t, TurmaDTO.class);
			turmaDTO.setDisciplinaId(t.getDisciplina().getId());
			turmaDTO.setDisciplinaNome(t.getDisciplina().getNome());
			return turmaDTO;

		}).toList();
	}

	/**
	 * Busca uma turma pelo seu identificador.
	 *
	 * @param id identificador da turma
	 * @return dados da turma encontrada
	 */
	@GetMapping("/{id}")
	public TurmaDTO buscarTurma(@PathVariable("id") Long id) {

		Turma turma = turmaService.buscarTurma(id);
		TurmaDTO turmaDTO = mapper.map(turma, TurmaDTO.class);
		turmaDTO.setDisciplinaId(turma.getDisciplina().getId());
		turmaDTO.setDisciplinaNome(turma.getDisciplina().getNome());

		return turmaDTO;
	}

	/**
	 * Cadastra uma nova turma.
	 *
	 * @param dto dados da turma
	 * @return turma cadastrada
	 */
	@PostMapping
	public TurmaDTO cadastrarTurma(@RequestBody @Valid TurmaDTO dto) {
		Turma turma = turmaService.cadastrarTurma(dto);
		TurmaDTO turmaDTO = mapper.map(turma, TurmaDTO.class);
		turmaDTO.setDisciplinaId(turma.getDisciplina().getId());
		turmaDTO.setDisciplinaNome(turma.getDisciplina().getNome());

		return turmaDTO;
	}

	/**
	 * Atualiza os dados de uma turma existente.
	 *
	 * @param id  identificador da turma
	 * @param dto novos dados da turma
	 * @return turma atualizada
	 */
	@PutMapping("/{id}")
	public TurmaDTO atualizarTurma(@PathVariable("id") Long id, @RequestBody @Valid TurmaDTO dto) {
		dto.setId(id);
		Turma turma = turmaService.cadastrarTurma(dto);
		TurmaDTO turmaDTO = mapper.map(turma, TurmaDTO.class);
		turmaDTO.setDisciplinaId(turma.getDisciplina().getId());
		turmaDTO.setDisciplinaNome(turma.getDisciplina().getNome());

		return turmaDTO;
	}
	
	
	/**
	 * Remove uma turma cadastrada.
	 *
	 * @param id identificador da turma
	 */
	@DeleteMapping("/{id}")
	public void excluirTurma(@PathVariable("id") Long id) {
		turmaService.excluirTurma(id);
	}
	
	
	/**
	 * Retorna a quantidade total de turmas cadastradas.
	 *
	 * @return total de turmas
	 */
	@GetMapping("/count")
	public Long contarTurma() {
		return turmaService.contarTurmas();

	}

}