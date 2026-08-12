

CREATE TABLE alunos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone varchar(100) not null unique,
    cpf VARCHAR(20) NOT NULL UNIQUE,
     rua varchar(100) not null,
    bairro varchar(100) not null,
    cidade varchar(100) not null,
    cep varchar(9) not null,
    numero varchar(20),
    estado varchar(2) not null
);


CREATE TABLE cursos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT
);

CREATE TABLE usuarios (

    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(30) NOT NULL
    
);
    

CREATE TABLE disciplinas (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    curso_id BIGINT,

    CONSTRAINT fk_disciplina_curso
        FOREIGN KEY (curso_id)
        REFERENCES cursos(id)
);



CREATE TABLE turmas (
    id BIGSERIAL PRIMARY KEY,
    disciplina_id BIGINT NOT NULL,
    vagas INTEGER NOT NULL,
    vagas_disponiveis INTEGER NOT NULL,

    status VARCHAR(20),
ativo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_turma_disciplina
        FOREIGN KEY (disciplina_id)
        REFERENCES disciplinas(id)
);


CREATE TABLE matriculas (
    id BIGSERIAL PRIMARY KEY,

    aluno_id BIGINT NOT NULL,
    turma_id BIGINT NOT NULL,

    status VARCHAR(20),

    data_matricula TIMESTAMP,

    CONSTRAINT fk_matricula_aluno
        FOREIGN KEY (aluno_id)
        REFERENCES alunos(id),

    CONSTRAINT fk_matricula_turma
        FOREIGN KEY (turma_id)
        REFERENCES turmas(id),

    CONSTRAINT uk_aluno_turma
        UNIQUE(aluno_id, turma_id)
);