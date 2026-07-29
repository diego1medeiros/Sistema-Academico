package br.com.sistemaacademico.exception;


/**
 * Exceção utilizada para representar violações de regras de negócio da
 * aplicação.
 * <p>
 * Essa exceção é lançada quando uma operação não pode ser concluída devido
 * às regras definidas pelo domínio do sistema, como por exemplo:
 * <ul>
 *   <li>CPF ou e-mail já cadastrados;</li>
 *   <li>Turma sem vagas disponíveis;</li>
 *   <li>Aluno já matriculado na turma;</li>
 *   <li>Tentativa de excluir um aluno com matrícula ativa.</li>
 * </ul>
 * O tratamento dessa exceção é realizado pela classe
 * {@link GlobalExceptionHandler}, que retorna uma resposta HTTP adequada ao cliente.
 */
public class RegraNegocioException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	

    /**
     * Cria uma nova exceção de regra de negócio.
     *
     * @param mensagem descrição do erro ocorrido
     */
	public RegraNegocioException(String mensagem) {
        super(mensagem);
    }

}
