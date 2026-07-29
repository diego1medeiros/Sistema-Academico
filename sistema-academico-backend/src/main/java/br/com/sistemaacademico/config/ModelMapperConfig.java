package br.com.sistemaacademico.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	
	 /**
     * Cria uma instância do ModelMapper para conversão
     * entre entidades e objetos DTO.
     *
     * <p>
     * O ModelMapper é utilizado para facilitar a transformação
     * de objetos, evitando a necessidade de realizar conversões
     * manuais entre Entity e DTO.
     *
     * @return uma instância configurada do ModelMapper
     */
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
