package br.com.sistemaacademico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	 /**
     * Trata exceções de regra de negócio.
     *
     * @param e exceção lançada
     * @return resposta padronizada
     */
	
    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<String> regraNegocio(RegraNegocioException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    
    /**
     * Trata exceções de execução.
     *
     * @param e exceção lançada
     * @return resposta padronizada
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtime(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}