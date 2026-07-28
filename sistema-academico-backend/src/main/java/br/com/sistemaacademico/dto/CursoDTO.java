package br.com.sistemaacademico.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CursoDTO {

    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    private String descricao;

}
