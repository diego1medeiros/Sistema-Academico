package br.com.sistemaacademico.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.sistemaacademico.dto.MatriculaResponseDTO;
import br.com.sistemaacademico.entity.Aluno;
import br.com.sistemaacademico.entity.Matricula;
import br.com.sistemaacademico.entity.Turma;
import br.com.sistemaacademico.enun.StatusMatricula;
import br.com.sistemaacademico.enun.StatusTurma;
import br.com.sistemaacademico.exception.RegraNegocioException;
import br.com.sistemaacademico.repository.AlunoRepository;
import br.com.sistemaacademico.repository.MatriculaRepository;
import br.com.sistemaacademico.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MatriculaService {

	private final MatriculaRepository matriculaRepository;
	private final AlunoRepository alunoRepository;
	private final TurmaRepository turmaRepository;

	
	@Transactional
	public MatriculaResponseDTO cadastrarMatricular(Long alunoId, Long turmaId) {

		Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
		Turma turma = turmaRepository.findById(turmaId).orElseThrow(() -> new RuntimeException("Turma não encontrada"));

		if (turma.getStatus() != StatusTurma.ABERTA) {
			throw new RegraNegocioException("Turma não está aberta");
		}
		if (turma.getVagasDisponiveis() <= 0) {
			throw new RegraNegocioException("Não há vagas disponíveis.");
		}

		if (matriculaRepository.existsByAlunoIdAndTurmaId(alunoId, turmaId)) {
			throw new RegraNegocioException("Aluno já matriculado nesta turma");
		}

		Matricula matricula = new Matricula();
		matricula.setAluno(aluno);
		matricula.setTurma(turma);
		matricula.setStatus(StatusMatricula.PENDENTE);
		matricula.setDataMatricula(LocalDateTime.now());
		Matricula salva = matriculaRepository.save(matricula);

		return converterParaDTO(salva);

	}

	public MatriculaResponseDTO confirmarMatricula(Long id) {

		Matricula matricula = matriculaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

		if (matricula.getStatus() == StatusMatricula.CONFIRMADA) {

			throw new RegraNegocioException("Matrícula já confirmada");
		}

		Turma turma = matricula.getTurma();

		if (turma.getVagasDisponiveis() <= 0) {
			throw new RegraNegocioException("Sem vagas");
		}

		turma.setVagasDisponiveis(turma.getVagasDisponiveis() - 1);
		matricula.setStatus(StatusMatricula.CONFIRMADA);

		turmaRepository.save(turma);
		Matricula salva = matriculaRepository.save(matricula);

		return converterParaDTO(salva);

	}

	public MatriculaResponseDTO cancelarMatricula(Long id) {

		Matricula matricula = matriculaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

		if (matricula.getStatus() == StatusMatricula.CANCELADA) {

			throw new RuntimeException("Matrícula já cancelada");

		}

		if (matricula.getStatus() == StatusMatricula.CONFIRMADA) {

			Turma turma = matricula.getTurma();
			turma.setVagasDisponiveis(turma.getVagasDisponiveis() + 1);
			turmaRepository.save(turma);

		}
		matricula.setStatus(StatusMatricula.CANCELADA);
		Matricula salva = matriculaRepository.save(matricula);

		return converterParaDTO(salva);

	}

	public List<MatriculaResponseDTO> listarAluno(Long id) {
		return matriculaRepository.findByAlunoId(id).stream().map(this::converterParaDTO).toList();

	}

	public List<MatriculaResponseDTO> listarTurma(Long id) {
		return matriculaRepository.findByTurmaId(id).stream().map(this::converterParaDTO).toList();

	}

	private MatriculaResponseDTO converterParaDTO(Matricula matricula) {

		
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		return new MatriculaResponseDTO(

				matricula.getId(),
				matricula.getAluno().getId(),
				matricula.getAluno().getNome(),
				matricula.getTurma().getId(),
				matricula.getTurma().getDisciplina().getNome(),
				matricula.getTurma().getDisciplina().getCurso().getNome(),
				matricula.getStatus(),
				matricula.getDataMatricula().format(formatter)
		);

	}

	public List<Matricula> listarMatriculas() {
		return matriculaRepository.findAll();
	}

	public Long contarMatriculas() {
		return matriculaRepository.count();
	}

	public List<MatriculaResponseDTO> ultimasMatriculas(){
		return matriculaRepository.buscarUltimas(
		        PageRequest.of(0,5)
		)
		.stream()
		.map(this::converterParaDTO)
		.toList();


		}
	

}
