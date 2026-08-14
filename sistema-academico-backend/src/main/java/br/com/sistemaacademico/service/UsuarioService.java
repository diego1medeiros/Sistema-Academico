package br.com.sistemaacademico.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sistemaacademico.dto.LoginResponseDTO;
import br.com.sistemaacademico.entity.Usuario;
import br.com.sistemaacademico.repository.UsuarioRepository;
import br.com.sistemaacademico.security.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public Usuario cadastrarUsuario(Usuario usuario) {

        usuario.setSenha(
            passwordEncoder.encode(usuario.getSenha())
        );

        return usuarioRepository.save(usuario);
    }

    public LoginResponseDTO validarLogin(String login, String senha) {

        Usuario usuario = usuarioRepository
                .findByLoginIgnoreCase(login)
                .orElseThrow(() ->
                    new RuntimeException("Usuário ou senha inválidos")
                );

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        String token = jwtService.gerarToken(
                usuario.getLogin(),
                usuario.getPerfil().name()
        );

        return new LoginResponseDTO(
                token,
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getPerfil().name()
        );
    }

    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
}