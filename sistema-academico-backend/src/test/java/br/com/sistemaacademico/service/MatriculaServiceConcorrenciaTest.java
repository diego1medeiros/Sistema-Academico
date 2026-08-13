package br.com.sistemaacademico.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistemaacademico.entity.Aluno;
import br.com.sistemaacademico.entity.Curso;
import br.com.sistemaacademico.entity.Disciplina;
import br.com.sistemaacademico.entity.Matricula;
import br.com.sistemaacademico.entity.Turma;
import br.com.sistemaacademico.enun.StatusMatricula;
import br.com.sistemaacademico.enun.StatusTurma;
import br.com.sistemaacademico.exception.RegraNegocioException;
import br.com.sistemaacademico.repository.AlunoRepository;
import br.com.sistemaacademico.repository.CursoRepository;
import br.com.sistemaacademico.repository.DisciplinaRepository;
import br.com.sistemaacademico.repository.MatriculaRepository;
import br.com.sistemaacademico.repository.TurmaRepository;

@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class MatriculaServiceConcorrenciaTest {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private TurmaRepository turmaRepository;
    
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Test
    void devePermitirApenasUmaMatriculaQuandoDuasPessoasConfirmamAoMesmoTempo()
            throws Exception {

        // ==========================
        // ARRANGE
        // ==========================
    	Curso curso = new Curso();
    	curso.setNome("Curso Teste");

    	curso = cursoRepository.saveAndFlush(curso);

    	Disciplina disciplina = new Disciplina();
    	disciplina.setNome("Disciplina Teste");
    	disciplina.setCurso(curso);

    	disciplina = disciplinaRepository.saveAndFlush(disciplina);
    	
    	Turma turma = new Turma();

    	turma.setDisciplina(disciplina);
    	turma.setVagas(1);
    	turma.setVagasDisponiveis(1);
    	turma.setStatus(StatusTurma.ABERTA);
    	turma.setAtivo(true);

    	turma = turmaRepository.saveAndFlush(turma); 

    	Aluno aluno1 = new Aluno();
    	aluno1.setNome("Aluno 1");
    	aluno1.setEmail("alu1@teste.com");
    	aluno1.setCpf("11111191119");
    	aluno1.setTelefone("21999999960");
    	aluno1.getEndereco().setCep("20000000");
    	aluno1.getEndereco().setRua("Rua Teste");
    	aluno1.getEndereco().setNumero("100");
    	aluno1.getEndereco().setBairro("Centro");
    	aluno1.getEndereco().setCidade("Rio de Janeiro");
    	aluno1.getEndereco().setEstado("RJ");

    	aluno1 = alunoRepository.saveAndFlush(aluno1);

    	Aluno aluno2 = new Aluno();
    	
    	aluno2.setNome("Aluno 2");
    	aluno2.setEmail("aluddddd2@teste.com");
    	aluno2.setCpf("22292222229");
    	aluno2.setTelefone("21999999958");
    	aluno2.getEndereco().setCep("20000000");
    	aluno2.getEndereco().setRua("Rua Teste");
    	aluno2.getEndereco().setNumero("101");
    	aluno2.getEndereco().setBairro("Centro");
    	aluno2.getEndereco().setCidade("Rio de Janeiro");
    	aluno2.getEndereco().setEstado("RJ");

    	aluno2 = alunoRepository.saveAndFlush(aluno2);

    	Matricula matricula1 = new Matricula();
    	matricula1.setAluno(aluno1);
    	matricula1.setTurma(turma);
    	matricula1.setStatus(StatusMatricula.PENDENTE);
    	matricula1.setDataMatricula(LocalDateTime.now());


        matricula1 = matriculaRepository.saveAndFlush(matricula1);

        Matricula matricula2 = new Matricula();
        matricula2.setAluno(aluno2);
        matricula2.setTurma(turma);
        matricula2.setStatus(StatusMatricula.PENDENTE);
        matricula2.setDataMatricula(LocalDateTime.now());

        matricula2 = matriculaRepository.saveAndFlush(matricula2);

        final Long matriculaId1 = matricula1.getId();
        final Long matriculaId2 = matricula2.getId();

        // ==========================
        // CONCORRÊNCIA
        // ==========================

        ExecutorService executor = Executors.newFixedThreadPool(2);

        CountDownLatch inicio = new CountDownLatch(1);

        CompletableFuture<Boolean> resultado1 =
                CompletableFuture.supplyAsync(() -> {
                    try {

                        inicio.await();

                        System.out.println("THREAD 1: iniciando");

                        matriculaService.confirmarMatricula(matriculaId1);

                        System.out.println("THREAD 1: CONFIRMOU");

                        return true;

                    } catch (RegraNegocioException e) {

                        System.out.println(
                            "THREAD 1: REGRA -> " + e.getMessage()
                        );

                        return false;

                    } catch (Exception e) {

                        e.printStackTrace();

                        throw new RuntimeException(e);
                    }
                }, executor);


        CompletableFuture<Boolean> resultado2 =
                CompletableFuture.supplyAsync(() -> {
                    try {

                        inicio.await();

                        System.out.println("THREAD 2: iniciando");

                        matriculaService.confirmarMatricula(matriculaId2);

                        System.out.println("THREAD 2: CONFIRMOU");

                        return true;

                    } catch (RegraNegocioException e) {

                        System.out.println(
                            "THREAD 2: REGRA -> " + e.getMessage()
                        );

                        return false;

                    } catch (Exception e) {

                        e.printStackTrace();

                        throw new RuntimeException(e);
                    }
                }, executor);


        // Só agora libera as duas
        inicio.countDown();

        boolean confirmou1 = resultado1.join();
        boolean confirmou2 = resultado2.join();

        System.out.println("RESULTADO THREAD 1 = " + confirmou1);
        System.out.println("RESULTADO THREAD 2 = " + confirmou2);

        assertTrue(
            confirmou1 || confirmou2,
            "Apenas uma matrícula deveria ser confirmada"
        );

        assertFalse(
            confirmou1 && confirmou2,
            "Duas matrículas não podem ser confirmadas com apenas uma vaga"
        );}}