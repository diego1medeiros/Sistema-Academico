package br.com.sistemaacademico.dto;

import br.com.sistemaacademico.enun.StatusMatricula;
import lombok.Data;

@Data
public class MatriculaResponseDTO {

    private Long id;

    private Long alunoId;

    private String alunoNome;

    private Long turmaId;

    private String disciplina;

    private String curso;

    private StatusMatricula status;

    private String dataMatricula;
}