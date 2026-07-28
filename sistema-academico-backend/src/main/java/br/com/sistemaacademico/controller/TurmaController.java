package br.com.sistemaacademico.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import br.com.sistemaacademico.dto.TurmaDTO;
import br.com.sistemaacademico.entity.Turma;
import br.com.sistemaacademico.service.TurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/turmas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TurmaController {

	private final TurmaService service;
	private final ModelMapper mapper;

	@GetMapping
	public List<TurmaDTO> listarTurmas() {
		return service.listarTurma().stream().map(t -> {
			TurmaDTO dto = mapper.map(t, TurmaDTO.class);
			dto.setDisciplinaId(t.getDisciplina().getId());
			dto.setDisciplinaNome(t.getDisciplina().getNome());
			return dto;

		}).toList();
	}

	@GetMapping("/{id}")
	public TurmaDTO buscarTurma(@PathVariable("id") Long id) {

		Turma turma = service.buscarTurma(id);
		TurmaDTO dto = mapper.map(turma, TurmaDTO.class);
		dto.setDisciplinaId(turma.getDisciplina().getId());
		dto.setDisciplinaNome(turma.getDisciplina().getNome());

		return dto;
	}

	@PostMapping
	public TurmaDTO cadastrarTurma(@RequestBody @Valid TurmaDTO dto) {
		Turma turma = service.cadastrarTurma(dto);
		TurmaDTO retorno = mapper.map(turma, TurmaDTO.class);
		retorno.setDisciplinaId(turma.getDisciplina().getId());
		retorno.setDisciplinaNome(turma.getDisciplina().getNome());

		return retorno;
	}

	@PutMapping("/{id}")
	public TurmaDTO atualizarTurma(@PathVariable("id") Long id, @RequestBody @Valid TurmaDTO dto) {

		dto.setId(id);
		Turma turma = service.cadastrarTurma(dto);
		TurmaDTO retorno = mapper.map(turma, TurmaDTO.class);
		retorno.setDisciplinaId(turma.getDisciplina().getId());
		retorno.setDisciplinaNome(turma.getDisciplina().getNome());

		return retorno;
	}

	@DeleteMapping("/{id}")
	public void excluirTurma(@PathVariable("id") Long id) {
		service.excluirTurma(id);
	}

	@GetMapping("/count")
	public Long contarTurma() {
		return service.ContarTurmas();

	}

}