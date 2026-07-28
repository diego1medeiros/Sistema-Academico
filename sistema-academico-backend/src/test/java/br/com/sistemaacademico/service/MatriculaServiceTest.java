package br.com.sistemaacademico.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.sistemaacademico.dto.MatriculaResponseDTO;
import br.com.sistemaacademico.entity.Aluno;
import br.com.sistemaacademico.entity.Curso;
import br.com.sistemaacademico.entity.Disciplina;
import br.com.sistemaacademico.entity.Matricula;
import br.com.sistemaacademico.entity.Turma;
import br.com.sistemaacademico.enun.StatusMatricula;
import br.com.sistemaacademico.enun.StatusTurma;
import br.com.sistemaacademico.exception.RegraNegocioException;
import br.com.sistemaacademico.repository.AlunoRepository;
import br.com.sistemaacademico.repository.MatriculaRepository;
import br.com.sistemaacademico.repository.TurmaRepository;

@ExtendWith(MockitoExtension.class)
class MatriculaServiceTest {

	@InjectMocks
	private MatriculaService service;

	@Mock
	private MatriculaRepository matriculaRepository;

	@Mock
	private AlunoRepository alunoRepository;

	@Mock
	private TurmaRepository turmaRepository;
	
	

//Não permitir matrícula em turma fechada
	@Test
	void deveLancarExcecaoQuandoTurmaEstiverFechada() {

		Aluno aluno = new Aluno();
		aluno.setId(1L);

		Turma turma = new Turma();
		turma.setId(1L);
		turma.setStatus(StatusTurma.FECHADA);

		when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

		when(turmaRepository.findById(1L)).thenReturn(Optional.of(turma));

		assertThrows(RegraNegocioException.class, () -> service.cadastrarMatricular(1L, 1L));
	}

//Não permitir matrícula duplicada

	@Test
	void naoDeveMatricularEmTurmaFechada() {

		Aluno aluno = new Aluno();
		aluno.setId(1L);

		Turma turma = new Turma();
		turma.setStatus(StatusTurma.FECHADA);

		when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

		when(turmaRepository.findById(1L)).thenReturn(Optional.of(turma));

		assertThrows(RegraNegocioException.class, () -> service.cadastrarMatricular(1L, 1L));

	}

//Sem vagas
	@Test
	void naoDeveMatricularSemVagas() {

		Aluno aluno = new Aluno();
		aluno.setId(1L);

		Turma turma = new Turma();
		turma.setStatus(StatusTurma.ABERTA);
		turma.setVagasDisponiveis(0);

		when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

		when(turmaRepository.findById(1L)).thenReturn(Optional.of(turma));

		assertThrows(RegraNegocioException.class, () -> service.cadastrarMatricular(1L, 1L));

	}
//Confirmar matrícula consome uma vaga

	@Test
	void deveConsumirUmaVagaAoConfirmarMatricula() {

		Aluno aluno = new Aluno();
		aluno.setId(1L);
		aluno.setNome("Diego");

		Curso curso = new Curso();
		curso.setId(1L);
		curso.setNome("Engenharia");

		Disciplina disciplina = new Disciplina();
		disciplina.setId(1L);
		disciplina.setNome("POO");
		disciplina.setCurso(curso);

		Turma turma = new Turma();
		turma.setId(1L);
		turma.setDisciplina(disciplina);
		turma.setVagasDisponiveis(5);

		Matricula matricula = new Matricula();
		matricula.setId(1L);
		matricula.setAluno(aluno);
		matricula.setTurma(turma);
		matricula.setStatus(StatusMatricula.PENDENTE);
		matricula.setDataMatricula(LocalDateTime.now());

		when(matriculaRepository.findById(1L)).thenReturn(Optional.of(matricula));

		when(matriculaRepository.save(any(Matricula.class))).thenReturn(matricula);

		when(turmaRepository.save(any(Turma.class))).thenReturn(turma);
		service.confirmarMatricula(1L);

		assertEquals(4, turma.getVagasDisponiveis());
		assertEquals(StatusMatricula.CONFIRMADA, matricula.getStatus());

	}

//Cancelar matrícula libera vaga

	@Test
	void deveLiberarVagaAoCancelarMatriculaConfirmada() {

		Aluno aluno = new Aluno();
		aluno.setId(1L);
		aluno.setNome("Diego");

		Curso curso = new Curso();
		curso.setNome("Java");

		Disciplina disciplina = new Disciplina();
		disciplina.setNome("POO");
		disciplina.setCurso(curso);

		Turma turma = new Turma();
		turma.setVagasDisponiveis(2);
		turma.setDisciplina(disciplina);

		Matricula matricula = new Matricula();
		matricula.setAluno(aluno);
		matricula.setTurma(turma);
		matricula.setStatus(StatusMatricula.CONFIRMADA);
		matricula.setDataMatricula(LocalDateTime.now());

		when(matriculaRepository.findById(1L)).thenReturn(Optional.of(matricula));

		when(matriculaRepository.save(any(Matricula.class))).thenReturn(matricula);

		service.cancelarMatricula(1L);

		assertEquals(3, turma.getVagasDisponiveis());
		assertEquals(StatusMatricula.CANCELADA, matricula.getStatus());

	}

//Consultar matrícula por aluno
	@Test
	void deveConsultarMatriculasPorAluno() {

	    when(matriculaRepository.findByAlunoId(1L))
	            .thenReturn(List.of(criarMatricula(), criarMatricula()));

	    List<MatriculaResponseDTO> lista =
	            service.listarAluno(1L);

	    assertEquals(2, lista.size());
	}

//Não confirmar matrícula sem vagas
	@Test
	void naoDeveConfirmarSemVagas() {

		Turma turma = new Turma();
		turma.setVagasDisponiveis(0);

		Matricula matricula = new Matricula();
		matricula.setTurma(turma);
		matricula.setStatus(StatusMatricula.PENDENTE);

		when(matriculaRepository.findById(1L)).thenReturn(Optional.of(matricula));

		assertThrows(RegraNegocioException.class, () -> service.confirmarMatricula(1L));
	}

	
	private Matricula criarMatricula() {

	    Aluno aluno = new Aluno();
	    aluno.setId(1L);
	    aluno.setNome("Diego");

	    Curso curso = new Curso();
	    curso.setId(1L);
	    curso.setNome("Engenharia de Software");

	    Disciplina disciplina = new Disciplina();
	    disciplina.setId(1L);
	    disciplina.setNome("Programação Java");
	    disciplina.setCurso(curso);

	    Turma turma = new Turma();
	    turma.setId(1L);
	    turma.setDisciplina(disciplina);
	    turma.setVagasDisponiveis(10);

	    Matricula matricula = new Matricula();
	    matricula.setId(1L);
	    matricula.setAluno(aluno);
	    matricula.setTurma(turma);
	    matricula.setStatus(StatusMatricula.PENDENTE);
	    matricula.setDataMatricula(LocalDateTime.now());

	    return matricula;
	}
	
	
}