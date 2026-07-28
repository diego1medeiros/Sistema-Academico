package br.com.sistemaacademico.entity;

import java.time.LocalDateTime;

import br.com.sistemaacademico.enun.StatusMatricula;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "matriculas", uniqueConstraints = { @UniqueConstraint(columnNames = { "aluno_id", "turma_id" }) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "aluno_id", nullable = false)
	private Aluno aluno;

	@ManyToOne
	@JoinColumn(name = "turma_id", nullable = false)
	private Turma turma;

	@Enumerated(EnumType.STRING)
	private StatusMatricula status;

	@Column(nullable=false)
	private LocalDateTime dataMatricula;

}