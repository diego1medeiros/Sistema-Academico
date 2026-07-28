# 🎓 Sistema Acadêmico

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=springboot)
![JSF](https://img.shields.io/badge/JSF-4.0-blue)
![PrimeFaces](https://img.shields.io/badge/PrimeFaces-13-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-336791?logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apachemaven)



Sistema desenvolvido para gerenciamento acadêmico utilizando Java, Spring Boot, JSF e PrimeFaces.

---

## 📚 Sobre o Projeto

O Sistema Acadêmico permite o gerenciamento de:

- 👨‍🎓 Alunos
- 📖 Cursos
- 📘 Disciplinas
- 🏫 Turmas
- 📝 Matrículas

O projeto foi desenvolvido utilizando arquitetura cliente-servidor, com backend em Spring Boot e frontend em JSF + PrimeFaces.

---

## 🏗 Arquitetura

```
Sistema-Academico
│
├── sistema-academico-backend
│     ├── Spring Boot
│     ├── REST API
│     ├── JPA
│     ├── Flyway
│     └── PostgreSQL
│
└── sistema-academico-frontend
      ├── JSF
      ├── PrimeFaces
      └── Consome a API REST
```

---


---

# 🚀 Tecnologias

## Backend

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Flyway
- PostgreSQL
- Maven
- Swagger/OpenAPI


## Frontend

- Java 17
- JSF
- PrimeFaces
- CDI
- Maven


## DevOps

- Git
- GitHub
- GitHub Actions
- Docker
- Docker Compose

---

# 📋 Funcionalidades

✅ Cadastro de alunos

✅ Cadastro de cursos

✅ Cadastro de disciplinas

✅ Cadastro de turmas

✅ Controle de matrículas

✅ Dashboard acadêmico

---

# 📂 Estrutura do Projeto


---

## 📂 Estrutura

```
Sistema-Academico
│
├── sistema-academico-backend
│
├── sistema-academico-frontend
│
├── docker-compose.yml
│
└── README.md
```

---


## 🐳 Banco de Dados com Docker

O projeto utiliza PostgreSQL executado através do Docker Compose.

### Subindo o banco de dados

```bash
docker compose up -d
```

### Serviços disponíveis

| Serviço | Porta |
|---------|------:|
| PostgreSQL | 5432 |
| PgAdmin | 8085 |
Acesso PostgreSQL

### Configuração PostgreSQL

```text
Banco: matricula
Usuário: postgres
Senha: 1234
```

## 🚀 Como executar o projeto localmente

### Pré-requisitos

- Java 17
- Maven
- Docker
- Docker Compose
- Git

Executar Banco

Na raiz do projeto:

docker compose up -d

Executar Backend

Acesse:

cd sistema-academico-backend
Execute:

mvn spring-boot:run

Backend:

http://localhost:8080

Executar Frontend

Acesse:

cd sistema-academico-frontend

Execute:

mvn spring-boot:run

Frontend:

http://localhost:8081


## 📖 Documentação da API

A API REST do Sistema Acadêmico possui documentação interativa utilizando Swagger/OpenAPI.

A documentação permite visualizar e testar os endpoints diretamente pelo navegador.

Acesse:[ http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## 🔗 Principais Endpoints

Recurso Método Endpoint Descrição

| Recurso | Método | Endpoint | Descrição |
|---------|---------|----------|-----------|
| Alunos | GET | /alunos | Lista alunos |
| Alunos | POST | /alunos | Cadastra aluno |
| Alunos | PUT | /alunos/{id} | Atualiza aluno |
| Alunos | DELETE | /alunos/{id} | Remove aluno |
| Cursos | GET | /cursos | Lista cursos |
| Disciplinas | GET | /disciplinas | Lista disciplinas |
| Turmas | GET | /turmas | Lista turmas |
| Matrículas | POST | /matriculas | Realiza matrícula |


## 📝 Teste Manual do Fluxo de Matrícula

Fluxo esperado:

Cadastrar um curso.
Cadastrar uma disciplina vinculada ao curso.
Criar uma turma informando quantidade máxima de vagas.
Cadastrar um aluno.
Realizar matrícula do aluno na turma.

Resultado esperado:

Matrícula criada com sucesso.
Aluno associado à turma.
Quantidade de vagas atualizada.

## 🎯 Regra de Limite de Vagas

Cada turma possui um limite máximo de alunos.

Exemplo:

Turma:
Limite: 5 alunos

Ao tentar cadastrar o aluno número 6:

Resultado esperado:

Sistema bloqueia a matrícula.
Retorna mensagem informando que não existem vagas disponíveis.

## 💡 Decisões de Implementação

Algumas decisões tomadas:

- Separação entre frontend e backend.
- Uso de Spring Data JPA para persistência.
- Flyway para versionamento do banco.
- Docker para o PostgreSQL.
- Swagger para documentação da API.

## ⚠️ Limitações Conhecidas

- Não possui autenticação de usuários.
- Não possui controle de perfis de acesso.
- Testes automatizados ainda estão em evolução.
- Sistema preparado inicialmente para ambiente acadêmico de pequeno porte.

## Projeto em desenvolvimento.

---

## 👨‍💻 Desenvolvedor

Diego Medeiros Jesus

Desenvolvedor Java Backend / Full Stack

GitHub:
https://github.com/diego1medeiros

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos e demonstração de conhecimentos em Java, Spring Boot, JSF e PostgreSQL.
