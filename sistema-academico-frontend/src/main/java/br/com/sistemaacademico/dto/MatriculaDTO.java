package br.com.sistemaacademico.dto;

import java.time.LocalDateTime;
import br.com.sistemaacademico.enun.StatusMatricula;
import lombok.Data;

@Data
public class MatriculaDTO {

    private Long id;

    private AlunoDTO aluno;

    private TurmaDTO turma;

    private StatusMatricula status;

    private LocalDateTime dataMatricula;

}
