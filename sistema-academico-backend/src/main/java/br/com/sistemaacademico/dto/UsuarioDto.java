package br.com.sistemaacademico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

	private Long id;
	private String nome;
	private String login;
	//private String senha;
	private String perfil;

}
