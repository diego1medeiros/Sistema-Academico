package br.com.sistemaacademico.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.sistemaacademico.dto.CursoDTO;
import br.com.sistemaacademico.entity.Curso;
import br.com.sistemaacademico.service.CursoService;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * Controlador responsável pelo gerenciamento dos cursos.
 *
 * <p>
 * Disponibiliza os endpoints para cadastro, consulta, atualização, exclusão e
 * listagem de cursos do sistema acadêmico.
 * </p>
 *
 * @author Diego Medeiros Jesus
 * @since 1.0
 */
@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(
		 name = "Cursos",
		 description = "Gerenciamento dos cursos"
		)
public class CursoController {

	private final CursoService cursoService;
	private final ModelMapper mapper;

	/**
	 * Retorna todos os cursos cadastrados.
	 *
	 * @return lista de cursos
	 */
	@GetMapping
	public List<CursoDTO> listarCursos() {
		return cursoService.listarCursos().stream().map(curso -> mapper.map(curso, CursoDTO.class)).toList();
	}

	/**
	 * Busca um curso pelo seu identificador.
	 *
	 * @param id identificador do curso
	 * @return curso encontrado
	 */
	@GetMapping("/{id}")
	public CursoDTO buscarCurso(@PathVariable("id") Long id) {
		 Curso curso = cursoService.buscarCurso(id);
		    return mapper.map(curso, CursoDTO.class);	}

	/**
	 * Cadastra um novo curso.
	 *
	 * @param dto dados do curso
	 * @return curso cadastrado
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CursoDTO cadastrarCurso(@RequestBody @Valid CursoDTO dto) {
		Curso curso = mapper.map(dto, Curso.class);
		return mapper.map(cursoService.cadastrarCurso(curso), CursoDTO.class);

	}

	/**
	 * Atualiza os dados de um curso existente.
	 *
	 * @param id  identificador do curso
	 * @param dto novos dados do curso
	 * @return curso atualizado
	 */
	@PutMapping("/{id}")
	public CursoDTO atualizarCurso(@PathVariable("id") Long id, @RequestBody CursoDTO dto) {
		Curso curso = mapper.map(dto, Curso.class);
		return mapper.map(cursoService.atualizarCurso(id, curso), CursoDTO.class);

	}

	/**
	 * Remove um curso do sistema.
	 *
	 * @param id identificador do curso
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirCurso(@PathVariable("id") Long id) {
		cursoService.excluirCurso(id);

	}

	/**
	 * Retorna a quantidade total de cursos cadastradas.
	 *
	 * @return total de cursos
	 */
	@GetMapping("/count")
	public Long contarCursos() {
		return cursoService.contarCursos();

	}
}
