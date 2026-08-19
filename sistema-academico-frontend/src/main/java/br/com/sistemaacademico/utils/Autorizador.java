
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

       

        // Permite acesso ao login
        if ("/pages/login.xhtml".equals(paginaAtual)) {
           
            return;
        }

        Object usuarioSessao = context
                .getExternalContext()
                .getSessionMap()
                .get("funcionarioLogado");

       

        // Verifica se existe usuário logado
        if (usuarioSessao instanceof LoginResponseDTO) {

            LoginResponseDTO usuario =
                    (LoginResponseDTO) usuarioSessao;

           

            return;
        }

        

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

