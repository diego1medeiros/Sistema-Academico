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

	private final DisciplinaService service;

	private final ModelMapper mapper;

	@GetMapping
	public List<DisciplinaDTO> listarDisciplinas() {
		return service.listarDisciplinas().stream().map(disciplina -> {
			DisciplinaDTO dto = mapper.map(disciplina, DisciplinaDTO.class);
			dto.setCursoId(disciplina.getCurso().getId());
			dto.setCursoNome(disciplina.getCurso().getNome());
			return dto;

		}).toList();

	}

	@GetMapping("/{id}")
	public DisciplinaDTO buscarDisciplina(@PathVariable("id") Long id) {
		Disciplina disciplina = service.buscarDisciplina(id);

		DisciplinaDTO dto = mapper.map(disciplina, DisciplinaDTO.class);
		dto.setCursoId(disciplina.getCurso().getId());
		dto.setCursoNome(disciplina.getCurso().getNome());

		return dto;
	}

	@PostMapping
	public DisciplinaDTO cadastrarDisciplina(@RequestBody @Valid DisciplinaDTO dto) {
		Disciplina disciplina = service.cadastrarDisciplina(dto);
		DisciplinaDTO retorno = mapper.map(disciplina, DisciplinaDTO.class);
		retorno.setCursoId(disciplina.getCurso().getId());

		return retorno;
	}

	@PutMapping("/{id}")
	public DisciplinaDTO atualizarDisciplina(@PathVariable("id") Long id, @RequestBody @Valid DisciplinaDTO dto) {

		dto.setId(id);

		Disciplina disciplina = service.cadastrarDisciplina(dto);

		DisciplinaDTO retorno = mapper.map(disciplina, DisciplinaDTO.class);
		retorno.setCursoId(disciplina.getCurso().getId());

		return retorno;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirDisciplina(@PathVariable("id") Long id) {
		service.excluirDisciplina(id);
	}

}
