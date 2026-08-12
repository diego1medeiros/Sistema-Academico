
package br.com.sistemaacademico.utils;

import java.io.Serializable;

import br.com.sistemaacademico.dto.LoginResponseDTO;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

public class Autorizador implements PhaseListener, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent event) {

        FacesContext context = event.getFacesContext();

        String paginaAtual = context.getViewRoot().getViewId();

        System.out.println("===== AUTORIZADOR =====");
        System.out.println("Página: " + paginaAtual);

        // Permite acesso ao login
        if ("/pages/login.xhtml".equals(paginaAtual)) {
            System.out.println("Página de login. Acesso permitido.");
            return;
        }

        Object usuarioSessao = context
                .getExternalContext()
                .getSessionMap()
                .get("funcionarioLogado");

        System.out.println("Objeto na sessão: " + usuarioSessao);

        // Verifica se existe usuário logado
        if (usuarioSessao instanceof LoginResponseDTO) {

            LoginResponseDTO usuario =
                    (LoginResponseDTO) usuarioSessao;

            System.out.println("Usuário logado: " + usuario.getNome());
            System.out.println("Login: " + usuario.getLogin());
            System.out.println("Perfil: " + usuario.getPerfil());

            return;
        }

        System.out.println("NÃO existe usuário logado.");
        System.out.println("Redirecionando para login.");

        NavigationHandler handler =
                context.getApplication().getNavigationHandler();

        handler.handleNavigation(
                context,
                null,
                "/pages/login.xhtml?faces-redirect=true"
        );

        context.renderResponse();
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}

