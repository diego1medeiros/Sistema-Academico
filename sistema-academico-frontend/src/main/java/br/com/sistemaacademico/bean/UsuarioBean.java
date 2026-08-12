package br.com.sistemaacademico.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.com.sistemaacademico.dto.LoginRequestDTO;
import br.com.sistemaacademico.dto.LoginResponseDTO;
import br.com.sistemaacademico.dto.UsuarioDto;
import br.com.sistemaacademico.enun.Perfil;
import br.com.sistemaacademico.exception.MensagemUtils;
import br.com.sistemaacademico.service.UsuarioService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@RequiredArgsConstructor

@Getter
@Setter
@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private UsuarioDto usuario = new UsuarioDto();

	@Autowired
	private UsuarioService service;
	private List<UsuarioDto> listaUsuarios;

	private String filtro;

	@PostConstruct
	public void init() {
		listarUsuarios();

	}

	public void listarUsuarios() {
		listaUsuarios = service.listar();

	}

	public void cadastrar() {
		service.cadastrarUsuario(usuario);
		MensagemUtils.info("Usuário cadastrado com sucesso!", null);

	}

	public List<String> getPerfilEnum() {
		return Arrays.asList(Perfil.getDescricaoPerfil());
	}

	public String isLoginSenhaValida(String login, String senha) {

		try {

			LoginRequestDTO loginRequestDTO = new LoginRequestDTO();

			loginRequestDTO.setLogin(login);
			loginRequestDTO.setSenha(senha);

			LoginResponseDTO response = service.consultaUsuario(loginRequestDTO);

			FacesContext context = FacesContext.getCurrentInstance();

			context.getExternalContext().getSessionMap().put("NOME", response.getNome());
			context.getExternalContext().getSessionMap().put("LOGIN", response.getLogin());
			context.getExternalContext().getSessionMap().put("PERFIL", response.getPerfil());
			context.getExternalContext().getSessionMap().put("funcionarioLogado", response);
			MensagemUtils.info("Login realizado com sucesso!", null);

			return "/pages/dashboard.xhtml?faces-redirect=true";

		} catch (WebClientResponseException.BadRequest e) {

			// Login ou senha inválidos
			MensagemUtils.erro("Login", "Usuário ou senha inválidos.");

			return null;

		} catch (Exception e) {

			MensagemUtils.erro("Erro", "Não foi possível realizar o login.");

			return null;
		}
	}

	public String getPerfil() {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("PERFIL");

	}

	public String getNome() {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("NOME");

	}

	public String getLogin() {

		return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGIN");

	}

	public boolean isAdmin() {
		return "ADMIN".equals(getPerfil());

	}

	public boolean isFuncionario() {
		return "FUNCIONARIO".equals(getPerfil());

	}

	public LoginResponseDTO getUsuarioLogado() {

		return (LoginResponseDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("funcionarioLogado");
	}

	public String logout() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/pages/login.xhtml?faces-redirect=true";

	}

	public void excluir(UsuarioDto usuario) {
		service.excluir(usuario.getId());
		MensagemUtils.info("Usuário excluído com sucesso!", null);
		listarUsuarios();
	}

	public void novo() {
		usuario = new UsuarioDto();
	}

}
