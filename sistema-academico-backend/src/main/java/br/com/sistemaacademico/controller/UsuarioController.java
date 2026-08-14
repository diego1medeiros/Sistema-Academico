package br.com.sistemaacademico.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaacademico.dto.LoginRequestDTO;
import br.com.sistemaacademico.dto.LoginResponseDTO;
import br.com.sistemaacademico.dto.UsuarioDto;
import br.com.sistemaacademico.entity.Usuario;
import br.com.sistemaacademico.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final ModelMapper mapper;

	 @GetMapping
	    public List<UsuarioDto> listar() {
	        return usuarioService.listar().stream().map(usuario -> mapper.map(usuario, UsuarioDto.class)).collect(Collectors.toList());
	    }

	
	@PostMapping
	public ResponseEntity<LoginResponseDTO> consultarUsuario(@RequestBody LoginRequestDTO loginRequestDTO) {
		LoginResponseDTO response = usuarioService.validarLogin(loginRequestDTO.getLogin(), loginRequestDTO.getSenha());
		return ResponseEntity.ok(response);

	}
	
	
	
	
	@GetMapping("/teste-admin")
	public String testeAdmin() {
	    return "Você é ADMIN!";
	}

	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDto cadastrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
	Usuario usuario = mapper.map(usuarioDto, Usuario.class);
	return mapper.map(usuarioService.cadastrarUsuario(usuario),UsuarioDto.class);
	

	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirUsuario(@PathVariable("id") Long id) {
		usuarioService.excluir(id);
	}
	
	
	
}