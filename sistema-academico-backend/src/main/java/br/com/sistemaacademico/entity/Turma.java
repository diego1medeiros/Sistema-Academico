package br.com.sistemaacademico.entity;

import br.com.sistemaacademico.enun.StatusTurma;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="turmas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Turma {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="disciplina_id")
    private Disciplina disciplina;

    @Column(nullable=false)
    private Integer vagas;

    @Column(nullable=false)
    private Integer vagasDisponiveis;

    @Enumerated(EnumType.STRING)
    private StatusTurma status;
    
    @Column(nullable=false)
    private Boolean ativo = true;

}
