package br.com.sistemaacademico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

	private String nome;
	private String login;
	private String senha;
	private String perfil;
    private Long id;

}
