package br.com.sistemaacademico.dto;

import lombok.Data;

import java.time.LocalDateTime;

import br.com.sistemaacademico.enun.StatusMatricula;

@Data
public class MatriculaDTO {

	private Long id;

	private Long alunoId;

	private Long turmaId;

	private StatusMatricula status;

	private LocalDateTime dataMatricula;

}
