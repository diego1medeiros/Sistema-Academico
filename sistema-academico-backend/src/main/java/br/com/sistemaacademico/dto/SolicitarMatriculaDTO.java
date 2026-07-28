package br.com.sistemaacademico.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SolicitarMatriculaDTO {


    @NotNull(message="Aluno obrigatório")
    private Long alunoId;


    @NotNull(message="Turma obrigatória")
    private Long turmaId;


}
