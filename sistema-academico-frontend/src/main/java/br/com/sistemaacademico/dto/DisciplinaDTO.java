package br.com.sistemaacademico.dto;

import lombok.Data;

@Data
public class DisciplinaDTO {

    private Long id;
    private String nome;
    private Long cursoId;
private String cursoNome;
}
