package br.com.sistemaacademico.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemaacademico.dto.LoginResponseDTO;
import br.com.sistemaacademico.entity.Usuario;
import br.com.sistemaacademico.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	@Autowired
	private final UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Usuario cadastrarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);

	}

	public LoginResponseDTO validarLogin(String login, String senha) {
		Usuario usuario = usuarioRepository.findByLoginIgnoreCase(login)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		if (!usuario.getSenha().equals(senha)) {
			throw new RuntimeException("Senha inválida");

		}

		return new LoginResponseDTO(usuario.getNome(), usuario.getLogin(), usuario.getPerfil().toString());

	}

	public void excluir(Long id) {
		usuarioRepository.deleteById(id);

	}
	
	public List<Usuario> listar() {
	    return usuarioRepository.findAll();

	}
	
}

