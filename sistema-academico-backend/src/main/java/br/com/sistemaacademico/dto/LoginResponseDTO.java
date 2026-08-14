package br.com.sistemaacademico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

	private String token;
	private Long funcionarioId;
	private String nome;
	private String login;
	private String perfil;

}

