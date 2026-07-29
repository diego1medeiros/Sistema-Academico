package br.com.sistemaacademico.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.sistemaacademico.dto.DisciplinaDTO;
import br.com.sistemaacademico.entity.Disciplina;
import br.com.sistemaacademico.service.DisciplinaService;

@RestController
@RequestMapping("/disciplinas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DisciplinaController {

	private final DisciplinaService disciplinaService;
	private final ModelMapper mapper;

	/**
	 * Retorna todas as disciplinas cadastradas.
	 *
	 * @return lista de disciplinas
	 */
	@GetMapping
	public List<DisciplinaDTO> listarDisciplinas() {
		return disciplinaService.listarDisciplinas().stream().map(disciplina -> {
			DisciplinaDTO dto = mapper.map(disciplina, DisciplinaDTO.class);
			dto.setCursoId(disciplina.getCurso().getId());
			dto.setCursoNome(disciplina.getCurso().getNome());
			return dto;

		}).toList();

	}

	/**
	 * Busca uma disciplina pelo identificador.
	 *
	 * @param id identificador da disciplina
	 * @return disciplina encontrada
	 */
	@GetMapping("/{id}")
	public DisciplinaDTO buscarDisciplina(@PathVariable("id") Long id) {
		Disciplina disciplina = disciplinaService.buscarDisciplina(id);

		DisciplinaDTO disciplinaDTO = mapper.map(disciplina, DisciplinaDTO.class);
		disciplinaDTO.setCursoId(disciplina.getCurso().getId());
		disciplinaDTO.setCursoNome(disciplina.getCurso().getNome());

		return disciplinaDTO;
	}

	/**
	 * Cadastra uma nova disciplina.
	 *
	 * @param dto dados da disciplina
	 * @return disciplina cadastrada
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DisciplinaDTO cadastrarDisciplina(@RequestBody @Valid DisciplinaDTO dto) {
		Disciplina disciplina = disciplinaService.cadastrarDisciplina(dto);
		DisciplinaDTO disciplinaDTO = mapper.map(disciplina, DisciplinaDTO.class);
		disciplinaDTO.setCursoId(disciplina.getCurso().getId());

		return disciplinaDTO;
	}

	/**
	 * Atualiza os dados de uma disciplina.
	 *
	 * @param id  identificador da disciplina
	 * @param dto novos dados da disciplina
	 * @return disciplina atualizada
	 */
	@PutMapping("/{id}")
	public DisciplinaDTO atualizarDisciplina(@PathVariable("id") Long id, @RequestBody @Valid DisciplinaDTO dto) {

		dto.setId(id);
		Disciplina disciplina = disciplinaService.atualizarDisciplina(dto);
		DisciplinaDTO disciplinaDTO = mapper.map(disciplina, DisciplinaDTO.class);
		disciplinaDTO.setCursoId(disciplina.getCurso().getId());

		return disciplinaDTO;
	}

	/**
	 * Exclui uma disciplina pelo identificador.
	 *
	 * @param id identificador da disciplina
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirDisciplina(@PathVariable("id") Long id) {
		disciplinaService.excluirDisciplina(id);
	}

}
