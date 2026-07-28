package br.com.sistemaacademico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sistemaacademico.dto.TurmaDTO;
import br.com.sistemaacademico.entity.Disciplina;
import br.com.sistemaacademico.entity.Turma;
import br.com.sistemaacademico.repository.DisciplinaRepository;
import br.com.sistemaacademico.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository repository;
    private final DisciplinaRepository disciplinaRepository;

    public List<Turma> listarTurma() {
        return repository.findAll();
    }

    public Turma buscarTurma(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada."));
    }

    public Turma cadastrarTurma(TurmaDTO dto) {

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Turma turma;

        if (dto.getId() == null) {
            turma = new Turma();
        } else {
            turma = buscarTurma(dto.getId());
        }

        turma.setDisciplina(disciplina);
        turma.setVagas(dto.getVagas());
        turma.setVagasDisponiveis(dto.getVagasDisponiveis());
        turma.setStatus(dto.getStatus());

        return repository.save(turma);

    }

    public void excluirTurma(Long id) {
        repository.deleteById(id);
    }

	public Long ContarTurmas() {
		return repository.count();
	}

}