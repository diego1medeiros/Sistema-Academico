package br.com.sistemaacademico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.faces.context.FacesContext;

@Configuration
public class WebClientAuthFilter {

    @Bean
    public WebClient.Builder webClientBuilder() {

        return WebClient.builder()
                .filter(authFilter());
    }

    private ExchangeFilterFunction authFilter() {

        return (request, next) -> {

            FacesContext facesContext =
                    FacesContext.getCurrentInstance();

            if (facesContext == null) {
                return next.exchange(request);
            }

            Object token = facesContext
                    .getExternalContext()
                    .getSessionMap()
                    .get("TOKEN");

            if (token == null) {
                return next.exchange(request);
            }

            ClientRequest authenticatedRequest =
                    ClientRequest.from(request)
                            .header(
                                    "Authorization",
                                    "Bearer " + token
                            )
                            .build();

            return next.exchange(authenticatedRequest);
        };
    }
}
