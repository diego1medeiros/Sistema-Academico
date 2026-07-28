# 🎓 Sistema Acadêmico

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
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

## 🚀 Tecnologias

### Backend

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Flyway
- PostgreSQL
- Maven

### Frontend

- Java 21
- JSF
- PrimeFaces
- CDI
- Maven

### DevOps

- Git
- GitHub
- Docker
- GitHub Actions

---

## 📋 Funcionalidades

- Cadastro de Alunos
- Cadastro de Cursos
- Cadastro de Disciplinas
- Cadastro de Turmas
- Controle de Matrículas
- Dashboard

---

## 📂 Estrutura

```
Sistema-Academico
│
├── sistema-academico-backend
│
└── sistema-academico-frontend
```

---


## 🐳 Banco de Dados com Docker

O projeto utiliza PostgreSQL executado através do Docker Compose.

### Subindo o banco de dados

```bash
docker compose up -d

Serviços disponíveis
Serviço	Porta
PostgreSQL	5432
PgAdmin	8085
Acesso PostgreSQL

Banco:
matricula

Usuário:
postgres

Senha:
1234

## 📖 Documentação da API

A API REST do Sistema Acadêmico possui documentação interativa utilizando Swagger/OpenAPI.

A documentação permite visualizar e testar os endpoints diretamente pelo navegador.

Acesse: http://localhost:8080/swagger-ui/index.htm

## 🔗 Principais Endpoints

| Recurso | Método | Endpoint | Descrição |
|---|---|---|---|
| Alunos | GET | /alunos | Lista alunos cadastrados |
| Alunos | POST | /alunos | Cadastra aluno |
| Alunos | PUT | /alunos/{id} | Atualiza aluno |
| Alunos | DELETE | /alunos/{id} | Remove aluno |
| Cursos | GET | /cursos | Lista cursos |
| Disciplinas | GET | /disciplinas | Lista disciplinas |
| Turmas | GET | /turmas | Lista turmas |
| Matrículas | POST | /matriculas | Realiza matrícula |


Projeto em desenvolvimento.

---

## 👨‍💻 Desenvolvedor

Diego Medeiros Jesus

Desenvolvedor Java Backend / Full Stack

GitHub:
https://github.com/diego1medeiros
