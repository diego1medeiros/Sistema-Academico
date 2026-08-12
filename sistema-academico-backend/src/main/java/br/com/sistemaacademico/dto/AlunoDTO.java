package br.com.sistemaacademico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AlunoDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;
    
    @NotBlank(message = "Telefone é obrigatório")
	private String telefone;
	
	private EnderecoDTO endereco = new EnderecoDTO();

}