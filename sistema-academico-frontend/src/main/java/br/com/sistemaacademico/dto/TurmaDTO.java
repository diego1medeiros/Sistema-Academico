package br.com.sistemaacademico.dto;

import br.com.sistemaacademico.enun.StatusTurma;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class TurmaDTO {

    private Long id;
    
    @NotNull(message = "Disciplina é obrigatória")
    private Long disciplinaId;

    @NotNull(message = "Vagas é obrigatório")
    private Integer vagas;

    @NotNull(message = "Vagas disponíveis é obrigatório")
    private Integer vagasDisponiveis;

    @NotNull(message = "Status é obrigatório")
    private StatusTurma status;

    private String disciplinaNome;
    
   // private StatusTurma status;

}
