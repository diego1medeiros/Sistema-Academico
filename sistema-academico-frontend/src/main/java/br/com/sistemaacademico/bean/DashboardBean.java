package br.com.sistemaacademico.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.sistemaacademico.dto.MatriculaResponseDTO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;


@Component
@ViewScoped
@Getter
public class DashboardBean implements Serializable {


    private static final long serialVersionUID = 1L;


    private Long totalAlunos;

    private Long totalCursos;

    private Long totalTurmas;

    private Long totalMatriculas;


    private List<MatriculaResponseDTO> ultimasMatriculas;



    private final WebClient webClient;



    public DashboardBean(WebClient webClient) {

        this.webClient = webClient;

    }



    @PostConstruct
    public void iniciar(){

        carregarDados();

    }



    public void carregarDados(){


        totalAlunos = buscarQuantidade("/alunos/count");


        totalCursos = buscarQuantidade("/cursos/count");


        totalTurmas = buscarQuantidade("/turmas/count");


        totalMatriculas = buscarQuantidade("/matriculas/count");



        ultimasMatriculas = webClient.get()
                .uri("/matriculas/ultimas")
                .retrieve()
                .bodyToFlux(MatriculaResponseDTO.class)
                .collectList()
                .block();

    }



    private Long buscarQuantidade(String uri){
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Long.class)
                .block();

    }




}
