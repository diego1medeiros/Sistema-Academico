package br.com.sistemaacademico.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.sistemaacademico.dto.MatriculaResponseDTO;
import br.com.sistemaacademico.dto.SolicitarMatriculaDTO;
import br.com.sistemaacademico.entity.Matricula;
import br.com.sistemaacademico.service.MatriculaService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MatriculaController {

	private final MatriculaService matriculaService;
	private final ModelMapper mapper;

	
	 /**
     * Retorna todas as matrículas cadastradas.
     *
     * @return lista de matrículas
     */
	@GetMapping
	public List<MatriculaResponseDTO> listarMatriculas() {

		return matriculaService.listarMatriculas().stream().map(matricula -> {

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

	
	 /**
     * Realiza uma solicitação de matrícula para um aluno em uma turma.
     *
     * @param dto dados da solicitação de matrícula
     * @return matrícula criada
     */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MatriculaResponseDTO matricular(@RequestBody @Valid SolicitarMatriculaDTO dto) {
		return matriculaService.cadastrarMatricular(dto.getAlunoId(), dto.getTurmaId());

	}
	 /**
     * Confirma uma matrícula pendente.
     *
     * @param id identificador da matrícula
     * @return matrícula confirmada
     */
	@PutMapping("/{id}/confirmar")
	public MatriculaResponseDTO confirmarMatricula(@PathVariable("id") Long id) {
		return matriculaService.confirmarMatricula(id);
	}

	
	 /**
     * Cancela uma matrícula.
     *
     * @param id identificador da matrícula
     * @return matrícula cancelada
     */
	@PutMapping("/{id}/cancelar")
	public MatriculaResponseDTO cancelarMatricula(@PathVariable("id") Long id) {
		return matriculaService.cancelarMatricula(id);
	}

	
	 /**
     * Lista as matrículas de um aluno.
     *
     * @param id identificador do aluno
     * @return lista de matrículas do aluno
     */
	@GetMapping("/aluno/{id}")
	public List<MatriculaResponseDTO>  listarMatriculasPorAluno(@PathVariable("id") Long id) {
		return matriculaService.listarAluno(id);
	}

	
	 /**
     * Lista as matrículas de uma turma.
     *
     * @param id identificador da turma
     * @return lista de matrículas da turma
     */
	@GetMapping("/turma/{id}")
	public List<MatriculaResponseDTO> listarMatriculasPorTurma(@PathVariable("id") Long id) {
		return matriculaService.listarTurma(id);
	}

	
	 /**
     * Retorna a quantidade total de matrículas cadastradas.
     *
     * @return total de matrículas
     */
	@GetMapping("/count")
	public Long contarMatriculas() {
		return matriculaService.contarMatriculas();

	}
	
	
	  /**
     * Retorna as cinco matrículas mais recentes.
     *
     * @return lista das últimas matrículas
     */
	  @GetMapping("/ultimas")
	    public List<MatriculaResponseDTO> ultimasMatriculas(){
	        return matriculaService.ultimasMatriculas();

	    }
}