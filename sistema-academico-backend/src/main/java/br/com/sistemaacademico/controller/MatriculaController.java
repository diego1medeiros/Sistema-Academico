package br.com.sistemaacademico.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.sistemaacademico.dto.MatriculaResponseDTO;
import br.com.sistemaacademico.dto.SolicitarMatriculaDTO;
import br.com.sistemaacademico.service.MatriculaService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MatriculaController {

	private final MatriculaService service;
	private final ModelMapper mapper;

	@GetMapping
	public List<MatriculaResponseDTO> listarMatriculas() {

		return service.listarMatriculas().stream().map(matricula -> {

			MatriculaResponseDTO dto = mapper.map(matricula, MatriculaResponseDTO.class);
			dto.setAlunoNome(matricula.getAluno().getNome());
			dto.setDisciplina(matricula.getTurma().getDisciplina().getNome());

			dto.setCurso(matricula.getTurma().getDisciplina().getCurso().getNome());
			if (matricula.getDataMatricula() != null) {
				dto.setDataMatricula(matricula.getDataMatricula().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			}

			return dto;

		}).collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MatriculaResponseDTO matricular(@RequestBody @Valid SolicitarMatriculaDTO dto) {

		return service.cadastrarMatricular(dto.getAlunoId(), dto.getTurmaId());

	}

	@PutMapping("/{id}/confirmar")
	public MatriculaResponseDTO confirmarMatricula(@PathVariable("id") Long id) {
		return service.confirmarMatricula(id);
	}

	@PutMapping("/{id}/cancelar")
	public MatriculaResponseDTO cancelarMatricula(@PathVariable("id") Long id) {
		return service.cancelarMatricula(id);
	}

	@GetMapping("/aluno/{id}")
	public List<MatriculaResponseDTO> listarAluno(@PathVariable("id") Long id) {
		return service.listarAluno(id);
	}

	@GetMapping("/turma/{id}")
	public List<MatriculaResponseDTO> listarTurma(@PathVariable("id") Long id) {
		return service.listarTurma(id);
	}

	@GetMapping("/count")
	public Long contarMatriculas() {
		return service.contarMatriculas();

	}
	
	  @GetMapping("/ultimas")
	    public List<MatriculaResponseDTO> ultimasMatriculas(){
	        return service.ultimasMatriculas();

	    }
}