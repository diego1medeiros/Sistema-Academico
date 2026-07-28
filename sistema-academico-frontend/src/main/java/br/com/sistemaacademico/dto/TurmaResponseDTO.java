package br.com.sistemaacademico.dto;

import br.com.sistemaacademico.enun.StatusTurma;
import lombok.Data;

@Data
public class TurmaResponseDTO {

    private Long id;

    private String disciplina;

    private String curso;

    private Integer vagas;

    private Integer vagasDisponiveis;

    private StatusTurma status;

}
