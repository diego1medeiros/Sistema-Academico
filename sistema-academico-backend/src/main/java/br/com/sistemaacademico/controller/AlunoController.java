package br.com.sistemaacademico.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaacademico.dto.AlunoDTO;
import br.com.sistemaacademico.entity.Aluno;
import br.com.sistemaacademico.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AlunoController {

	private final AlunoService service;
	private final ModelMapper mapper;

	@GetMapping
	public List<AlunoDTO> listarAluno() {
		return service.listarAlunos().stream().map(a -> mapper.map(a, AlunoDTO.class)).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public AlunoDTO buscarAlunoPorId(@PathVariable("id") Long id) {
		return mapper.map(service.buscarAluno(id), AlunoDTO.class);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlunoDTO cadastrarAluno(@RequestBody @Valid AlunoDTO dto) {
		Aluno aluno = mapper.map(dto, Aluno.class);
		return mapper.map(service.cadastrarAluno(aluno), AlunoDTO.class);

	}

	@PutMapping("/{id}")
	public AlunoDTO atualizarAluno(@PathVariable("id") Long id, @RequestBody @Valid AlunoDTO dto) {
		Aluno aluno = mapper.map(dto, Aluno.class);
		return mapper.map(service.atualizarAluno(id, aluno), AlunoDTO.class);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirAluno(@PathVariable("id") Long id) {
		service.excluirAluno(id);
	}

	@GetMapping("/count")
	public Long contarAlunos() {
		return service.ContarAlunos();

	}
}