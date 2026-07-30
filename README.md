# 🎓 Sistema Acadêmico

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=springboot)
![JSF](https://img.shields.io/badge/JSF-4.0-blue)
![PrimeFaces](https://img.shields.io/badge/PrimeFaces-13-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-336791?logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apachemaven)


Sistema de Gestão Acadêmica desenvolvido como projeto de portfólio para demonstrar conhecimentos em desenvolvimento backend e frontend Java utilizando Spring Boot, JSF, PrimeFaces, PostgreSQL, Docker e arquitetura baseada em APIs REST.

A aplicação permite o gerenciamento completo de alunos, cursos, disciplinas, turmas e matrículas, implementando regras de negócio como controle de vagas, confirmação de matrículas e validações de integridade dos dados.
---
## 📑 Sumário

- 📚 Sobre o Projeto
- 🎥 Demonstração
- 🏗 Arquitetura
- 🚀 Tecnologias
- 📋 Funcionalidades
- 📂 Estrutura do Projeto
- 🐳 Banco de Dados
- 🚀 Como Executar
- 📚 Documentação Técnica
- 📖 Swagger
- 🔗 Endpoints
- 📸 Capturas de Tela
- 📝 Fluxo de Matrícula
- 🎯 Regras de Negócio
- 💡 Decisões
- ⚠️ Limitações
- 👨‍💻 Desenvolvedor





## 📚 Sobre o Projeto

O Sistema Acadêmico permite o gerenciamento de:

- 👨‍🎓 Alunos
- 📖 Cursos
- 📘 Disciplinas
- 🏫 Turmas
- 📝 Matrículas

O projeto foi desenvolvido utilizando arquitetura cliente-servidor, com backend em Spring Boot e frontend em JSF + PrimeFaces.

## ✨ Destaques

- Arquitetura Cliente-Servidor
- Backend REST desenvolvido com Spring Boot
- Frontend JSF + PrimeFaces
- Persistência com Spring Data JPA e PostgreSQL
- Controle de versão do banco com Flyway
- Docker Compose para ambiente de desenvolvimento
- Dashboard com indicadores do sistema
- Documentação da API com Swagger/OpenAPI



## 🎥 Demonstração do Sistema

Assista à demonstração completa do Sistema Acadêmico no YouTube:

▶️ https://www.youtube.com/watch?v=deqnvNc9f2Q

Ou clique na imagem abaixo:

[![Sistema Acadêmico](docs/imagens/dashboard.png)](https://www.youtube.com/watch?v=deqnvNc9f2Q)

---

## 🏗 Arquitetura

O projeto foi dividido em frontend e backend,
utilizando comunicação REST através de WebClient.
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

## 🏗 Diagrama de Arquitetura
                 
                 Usuário
                    │
                    ▼
      +---------------------------+
      | Frontend                  |
      | JSF + PrimeFaces          |
      +---------------------------+
                    │
              HTTP / REST
                    │
                    ▼
      +---------------------------+
      | Backend                   |
      | Spring Boot REST API      |
      | Spring Data JPA           |
      | Hibernate                 |
      | Flyway                    |
      +---------------------------+
                    │
                    ▼
      +---------------------------+
      | PostgreSQL                |
      | Docker                    |
      +---------------------------+

          Swagger/OpenAPI
                │
        GitHub Actions (CI)



# 🚀 Tecnologias

| Categoria | Tecnologias |
|-----------|-------------|
| Linguagem | Java 17 |
| Backend | Spring Boot, Spring MVC |
| Persistência | Spring Data JPA, Hibernate |
| Banco | PostgreSQL |
| Migrações | Flyway |
| Frontend | JSF, PrimeFaces |
| Documentação | Swagger/OpenAPI |
| Build | Maven |
| Containerização | Docker, Docker Compose |
| Versionamento | Git, GitHub |

---

# 📋 Funcionalidades

✅ Cadastro de alunos

✅ Cadastro de cursos

✅ Cadastro de disciplinas

✅ Cadastro de turmas

✅ Controle de matrículas

✅ Dashboard acadêmico

---

## 📂 Estrutura do Projeto

```text
Sistema-Academico
│
├── .github/
│   └── workflows/          
│        └── frontend.yml    # GitHub Actions
│        └── backend.yml 
│           
├── docs/
│   └── imagens/            # Capturas de tela do README
│
├── sistema-academico-backend/
│   ├── src/
│   ├── pom.xml
│   
│
├── sistema-academico-frontend/
│   ├── src/
│   ├── pom.xml
│   
│
├── docker-compose.yml
└── README.md
```
```

## 🤖 Uso de Inteligência Artificial

Durante o desenvolvimento deste projeto, ferramentas de Inteligência Artificial foram utilizadas apenas como apoio em atividades específicas, sem substituir o processo de implementação.

### Ferramenta utilizada

- ChatGPT (OpenAI)

### A IA auxiliou em:

- Investigação e resolução de alguns erros (bugs) encontrados durante o desenvolvimento.
- Revisão e melhoria da documentação do projeto (README).
- Apoio na elaboração da documentação Javadoc.
- Orientações sobre testes automatizados utilizando JUnit.
- Explicações sobre mensagens de erro e exceções do Spring Boot.
  
### Revisão manual

Todo o código-fonte, regras de negócio e arquitetura da aplicação foram desenvolvidos e revisados manualmente pelo autor do projeto.

As sugestões fornecidas pela IA foram analisadas, adaptadas e validadas antes de serem aplicadas, garantindo que o comportamento da aplicação atendesse aos requisitos definidos.



# 🐳 Banco de Dados

O projeto utiliza **PostgreSQL 16**.

Existem duas formas de configurar o banco de dados:

## Opção 1 - Docker (Recomendado)

Suba o banco utilizando o Docker Compose:

docker compose up -d

Serviços disponíveis:

| Serviço | Porta |
|---------|------:|
| PostgreSQL | 5432 |
| PgAdmin | 8085 |

Configuração do banco:

```text
Banco: matricula
Usuário: postgres
Senha: 1234
```

---

## Opção 2 - PostgreSQL instalado localmente

Caso não utilize Docker, instale o PostgreSQL 16 (ou versão compatível) e crie um banco de dados com a seguinte configuração:

```text
Banco: matricula
Usuário: postgres
Senha: 1234
Porta: 5432
```

Depois, execute a aplicação normalmente. O Flyway criará automaticamente as tabelas e aplicará as migrations na primeira execução.

Verifique também se o arquivo `application.properties` (ou `application.yml`) está configurado corretamente:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/matricula
spring.datasource.username=postgres
spring.datasource.password=1234
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

### 1. Clonar o projeto

```bash
git clone https://github.com/diego1medeiros/Sistema-Academico.git
```

### 2. Acessar o projeto

```bash
cd Sistema-Academico
```

### 3. Subir o banco

```bash
docker compose up -d
```

### 4. Executar o Backend

```bash
cd sistema-academico-backend
mvn spring-boot:run
```

A API ficará disponível em:

http://localhost:8080

### 5. Executar o Frontend

```bash
cd sistema-academico-frontend
mvn spring-boot:run
```

O sistema ficará disponível em:

http://localhost:8081

## 📚 Documentação Técnica

O projeto possui documentação Javadoc dos principais componentes:

- Controllers REST
- Services
- Beans JSF
- Integrações WebClient
- Regras de negócio de matrícula


## 📖 Documentação da API

A API REST do Sistema Acadêmico possui documentação interativa utilizando Swagger/OpenAPI.

A documentação permite visualizar e testar os endpoints diretamente pelo navegador.

Acesse a documentação interativa da API em:

http://localhost:8080/swagger-ui/index.html

## 🔗 Principais Endpoints

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

## 📸 Capturas de Tela

### Dashboard

![Dashboard](docs/imagens/dashboard.png)

---

### Cadastro de Alunos

![Cadastro de Alunos](docs/imagens/alunos.png)
![Cadastro de Alunos](docs/imagens/alunos1.png)
![Cadastro de Alunos](docs/imagens/alunos2.png)
![Cadastro de Alunos](docs/imagens/alunos4.png)

---

### Cadastro de Cursos

![Cadastro de Cursos](docs/imagens/cursos.png)
![Cadastro de Cursos](docs/imagens/cursos1.png)
![Cadastro de Cursos](docs/imagens/cursos2.png)
![Cadastro de Cursos](docs/imagens/cursos3.png)

---

### Cadastro de Turmas

![Cadastro de Turmas](docs/imagens/turmas.png)
![Cadastro de Turmas](docs/imagens/turmas1.png)

---

### Matrículas

![Matrículas](docs/imagens/matriculas.png)
![Matrículas](docs/imagens/matriculas2.png)

---

### Documentação da API (Swagger)

![Swagger](docs/imagens/swagger-home.png)
![Swagger](docs/imagens/swagger-home1.png)
![Swagger](docs/imagens/swagger-home2.png)
![Swagger](docs/imagens/swagger-home3.png)



## 📝 Fluxo de Matrícula

1. Cadastrar um curso.
2. Cadastrar uma disciplina vinculada ao curso.
3. Criar uma turma informando o limite de vagas.
4. Cadastrar um aluno.
5. Solicitar a matrícula.
6. Confirmar a matrícula.

Resultado esperado:

- Matrícula criada.
- Aluno vinculado à turma.
- Vagas disponíveis atualizadas.

## 📌 Principais Regras de Negócio

- Um aluno não pode ser matriculado duas vezes na mesma turma.
- Apenas turmas com status **ABERTA** aceitam matrículas.
- Matrículas confirmadas reduzem a quantidade de vagas disponíveis.
- O cancelamento de uma matrícula confirmada devolve a vaga à turma.
- Não é permitido excluir alunos com matrículas ativas.
- Cursos e disciplinas seguem relacionamento hierárquico.


## 🎯 Regra de Limite de Vagas

Cada turma possui um número máximo de vagas.

Exemplo:

- Limite da turma: **5 alunos**
- Matrículas confirmadas: **5**

Ao tentar confirmar a matrícula do sexto aluno:

- ❌ A matrícula é bloqueada.
- ❌ O sistema retorna a mensagem:

> Não há vagas disponíveis.

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

## 🚧 Status do Projeto

| Funcionalidade | Status |
|---------------|--------|
| Backend REST | ✅ |
| Frontend JSF | ✅ |
| Dashboard | ✅ |
| Docker | ✅ |
| Swagger | ✅ |
| Flyway | ✅ |
| CRUD Completo | ✅ |
| Testes Automatizados | 🔄 |
| Spring Security | 🔄 |
| Deploy | 🔄 |

## 🚀 Próximas Evoluções

- Spring Security + JWT
- Testes unitários (JUnit e Mockito)
- Testes de integração
- Deploy em nuvem
- Pipeline CI/CD
- Observabilidade (Actuator)


---

## 👨‍💻 Desenvolvedor

**Diego Medeiros Jesus**

Desenvolvedor Java Backend / Full Stack

- GitHub: https://github.com/diego1medeiros
- LinkedIn: www.linkedin.com/in/diego-medeiros-jesus-50746717a

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos e demonstração de conhecimentos em Java, Spring Boot, JSF e PostgreSQL.

---

## 📚 Aprendizados

Durante o desenvolvimento deste projeto foram aplicados conceitos como:

- Arquitetura Cliente-Servidor
- APIs REST
- DTOs
- ModelMapper
- Spring Data JPA
- Regras de Negócio
- Flyway
- Docker
- WebClient
- JSF + PrimeFaces
- Organização em camadas

⭐ Se este projeto foi útil para você, deixe uma estrela no repositório!
