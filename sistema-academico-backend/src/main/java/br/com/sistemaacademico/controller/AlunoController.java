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
	private final AlunoService alunoService;
	private final ModelMapper mapper;
	
	
	
	/**
     * Retorna todos os alunos cadastrados.
     *
     * @return lista de alunos
     */
	@GetMapping
	public List<AlunoDTO> listarAluno() {
		return alunoService.listarAlunos().stream().map(a -> mapper.map(a, AlunoDTO.class)).collect(Collectors.toList());
	}

	
	/**
     * Busca um aluno pelo seu identificador.
     *
     * @param id identificador do aluno
     * @return aluno encontrado
     */
	@GetMapping("/{id}")
	public AlunoDTO buscarAlunoPorId(@PathVariable("id") Long id) {
		return mapper.map(alunoService.buscarAluno(id), AlunoDTO.class);
	}

	
	 /**
     * Cadastra um novo aluno.
     *
     * @param dto dados do aluno
     * @return aluno cadastrado
     */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlunoDTO cadastrarAluno(@RequestBody @Valid AlunoDTO dto) {
		Aluno aluno = mapper.map(dto, Aluno.class);
		return mapper.map(alunoService.cadastrarAluno(aluno), AlunoDTO.class);
	}

	
	 /**
     * Atualiza os dados de um aluno.
     *
     * @param id identificador do aluno
     * @param dto novos dados
     * @return aluno atualizado
     */
	@PutMapping("/{id}")
	public AlunoDTO atualizarAluno(@PathVariable("id") Long id, @RequestBody @Valid AlunoDTO dto) {
		Aluno aluno = mapper.map(dto, Aluno.class);
		return mapper.map(alunoService.atualizarAluno(id, aluno), AlunoDTO.class);
	}

	
	 /**
     * Exclui um aluno pelo identificador.
     *
     * @param id identificador do aluno
     */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirAluno(@PathVariable("id") Long id) {
		alunoService.excluirAluno(id);
	}

	
	/**
     * Retorna a quantidade de alunos cadastrados.
     *
     * @return total de alunos
     */
	@GetMapping("/count")
	public Long contarAlunos() {
		return alunoService.contarAlunos();
	}
}