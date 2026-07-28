package br.com.sistemaacademico.exception;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class MensagemUtils {

	public static void info(String info, String mesangem) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, info, mesangem);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void erro(String erro, String mesangem) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, erro, mesangem);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void warr(String warr, String mesangem) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, warr, mesangem);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
