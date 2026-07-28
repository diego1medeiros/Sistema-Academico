package br.com.sistemaacademico.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.sistemaacademico.dto.CursoDTO;
import br.com.sistemaacademico.entity.Curso;
import br.com.sistemaacademico.service.CursoService;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CursoController {

	private final CursoService service;
	private final ModelMapper mapper;

	@GetMapping
	public List<Curso> listaruros() {
		return service.listarCursos();
	}

	@GetMapping("/{id}")
	public Curso buscarCurso(@PathVariable("id") Long id) {
		return service.buscarCurso(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CursoDTO cadastrarCurso(@RequestBody @Valid CursoDTO dto) {
		Curso curso = mapper.map(dto, Curso.class);
		return mapper.map(service.cadastrarCurso(curso), CursoDTO.class);

	}

	@PutMapping("/{id}")
	public CursoDTO atualizarCurso(@PathVariable ("id")Long id, @RequestBody CursoDTO dto) {
		Curso curso = mapper.map(dto, Curso.class);
		return mapper.map(service.atualizarCurso(id, curso), CursoDTO.class);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirCurso(@PathVariable ("id")Long id) {
		service.excluirCurso(id);

	}
	
	 @GetMapping("/count")
	    public Long contarCursos(){
	        return service.contarCursos();

}}
