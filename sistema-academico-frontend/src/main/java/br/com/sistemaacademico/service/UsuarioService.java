package br.com.sistemaacademico.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.sistemaacademico.dto.LoginRequestDTO;
import br.com.sistemaacademico.dto.LoginResponseDTO;
import br.com.sistemaacademico.dto.UsuarioDto;
import jakarta.faces.context.FacesContext;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final WebClient webClient;

	public void cadastrarUsuario(UsuarioDto usuarioDto) {
		webClient.post().uri("/usuarios/cadastrar").bodyValue(usuarioDto).retrieve().bodyToMono(UsuarioDto.class)
				.block();

	}

	public LoginResponseDTO consultaUsuario(LoginRequestDTO request) {
		return webClient.post().uri("/usuarios").bodyValue(request).retrieve().bodyToMono(LoginResponseDTO.class)
				.block();

	}

	public void excluir(Long id) {
		webClient.delete().uri("/usuarios/{id}", id).retrieve().bodyToMono(Void.class).block();
	}

	public List<UsuarioDto> listar() {

	    String token = (String) FacesContext
	            .getCurrentInstance()
	            .getExternalContext()
	            .getSessionMap()
	            .get("TOKEN");

	    return Arrays.asList(
	            webClient
	                    .get()
	                    .uri("/usuarios")
	                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
	                    .retrieve()
	                    .bodyToMono(UsuarioDto[].class)
	                    .block()
	    );
	}

	public LoginResponseDTO login(LoginRequestDTO request) {

	    return webClient
	            .post()
	            .uri("/usuarios")
	            .bodyValue(request)
	            .retrieve()
	            .bodyToMono(LoginResponseDTO.class)
	            .block();
	}
	}

