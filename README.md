# 🎓 Sistema Acadêmico

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=springboot)
![JSF](https://img.shields.io/badge/JSF-4.0-blue)
![PrimeFaces](https://img.shields.io/badge/PrimeFaces-13-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-336791?logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apachemaven)


🎓 Sistema Acadêmico

Sistema de Gestão Acadêmica desenvolvido como projeto de portfólio para demonstrar conhecimentos em desenvolvimento Backend e Full Stack Java utilizando Spring Boot, JSF, PrimeFaces, PostgreSQL, Docker e arquitetura baseada em APIs REST.

A aplicação permite o gerenciamento de alunos, cursos, disciplinas, turmas e matrículas, implementando regras de negócio como controle de vagas, confirmação de matrículas, validações de integridade, autenticação de usuários e controle de acesso às páginas do sistema.
---
## 📑 Sumário

- 📚 Sobre o Projeto
- 🎥 Demonstração
- 🏗 Arquitetura
- 🚀 Tecnologias
- 📋 Funcionalidades
- 🔐 Autenticação e Controle de Acesso
- 📍 Busca de CEP
- 📂 Estrutura do Projeto
- 🐳 Banco de Dados
- 🚀 Como Executar
- 📚 Documentação Técnica
- 📖 Swagger
- 🔗 Endpoints
- 📸 Capturas de Tela
- 📝 Fluxo de Matrícula
- 🎯 Regras de Negócio
- 💡 Decisões de Implementação
- ⚠️ Limitações
- 🚧 Status do Projeto
- 🚀 Próximas Evoluções
- 👨‍💻 Desenvolvedor

## 📚 Sobre o Projeto

O Sistema Acadêmico permite o gerenciamento de:

- 👨‍🎓 Alunos
- 📖 Cursos
- 📘 Disciplinas
- 🏫 Turmas
- 📝 Matrículas
- 👤 Usuários

O projeto foi desenvolvido utilizando uma arquitetura **cliente-servidor**, com:

- Backend desenvolvido em Spring Boot
- Frontend desenvolvido em JSF + PrimeFaces
- Comunicação entre frontend e backend através de APIs REST
- Persistência utilizando Spring Data JPA
- Banco de dados PostgreSQL

---

# ✨ Destaques

- Arquitetura Cliente-Servidor
- Backend REST com Spring Boot
- Frontend JSF + PrimeFaces
- Persistência com Spring Data JPA
- PostgreSQL
- Flyway para versionamento do banco
- Docker Compose
- Dashboard acadêmico
- Swagger/OpenAPI
- Cadastro e gerenciamento de usuários
- Tela de login
- Controle de acesso às páginas
- Controle de perfis de usuário
- Busca automática de endereço através do CEP
- Controle de vagas em turmas
- Regras de negócio para matrículas

---


## 🎥 Demonstração do Sistema

Assista à demonstração completa do Sistema Acadêmico no YouTube:

▶️ https://www.youtube.com/watch?v=nJgUWgklqdI

Ou clique na imagem abaixo:

[![Sistema Acadêmico](docs/imagens/dashboard5.png)](https://www.youtube.com/watch?v=nJgUWgklqdI)

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

## 👨‍🎓 Alunos

- ✅ Cadastro de alunos
- ✅ Edição de alunos
- ✅ Exclusão de alunos
- ✅ Listagem de alunos
- ✅ Validação de campos obrigatórios
- ✅ Cadastro de endereço
- ✅ Busca automática de endereço através do CEP

## 📚 Cursos

- ✅ Cadastro de cursos
- ✅ Edição de cursos
- ✅ Exclusão de cursos
- ✅ Listagem de cursos

## 📘 Disciplinas

- ✅ Cadastro de disciplinas
- ✅ Associação com cursos
- ✅ Edição de disciplinas
- ✅ Exclusão de disciplinas
- ✅ Listagem de disciplinas

## 🏫 Turmas

- ✅ Cadastro de turmas
- ✅ Associação com disciplinas
- ✅ Definição de quantidade de vagas
- ✅ Controle de vagas disponíveis
- ✅ Controle de status da turma
- ✅ Ativação/desativação da turma

## 📝 Matrículas

- ✅ Solicitação de matrícula
- ✅ Consulta de matrículas
- ✅ Confirmação de matrícula
- ✅ Cancelamento de matrícula
- ✅ Controle de vagas
- ✅ Validação para evitar matrícula duplicada

## 👤 Usuários

- ✅ Cadastro de usuários
- ✅ Exclusão de usuários
- ✅ Login
- ✅ Logout
- ✅ Controle de perfil
- ✅ Controle de acesso às páginas
- ✅ Autorizador utilizando `PhaseListener`
- 🔄 Autenticação com JWT — planejado


## 🔐 Autenticação e Controle de Acesso

O sistema possui atualmente uma **tela de login** para controlar o acesso às páginas internas da aplicação.

### 🔑 Login

O usuário realiza a autenticação informando:

- **Login**
- **Senha**

Após a autenticação, as informações do usuário são armazenadas na **sessão HTTP**.

Exemplo:

```java
sessionMap.put("NOME", response.getNome());
sessionMap.put("LOGIN", response.getLogin());
sessionMap.put("PERFIL", response.getPerfil());

🛡️ Autorizador

Foi implementado um PhaseListener chamado Autorizador.

Sua função é verificar se existe um usuário autenticado na sessão antes de permitir o acesso às páginas protegidas.

Usuário acessa uma página
          │
          ▼
     Autorizador
          │
          ▼
Existe usuário na sessão?
       /       \
     SIM       NÃO
      │         │
      ▼         ▼
  Permite     Redireciona
   acesso      para Login

A página de login permanece pública:

/pages/login.xhtml

As demais páginas do sistema são protegidas pelo Autorizador.

👤 Controle de Perfil

O sistema também armazena o perfil do usuário autenticado na sessão:

sessionMap.put("PERFIL", response.getPerfil());

Atualmente são utilizados os perfis:

ADMIN
FUNCIONARIO

```

## 📍 Busca de CEP

O cadastro de alunos possui integração para consulta automática de endereço através do CEP.

Ao informar um CEP válido, o sistema consulta os dados e preenche automaticamente:

- 🏠 Rua
- 🏘️ Bairro
- 🌆 Cidade
- 🗺️ Estado

### Exemplo

```text
CEP: 27XXX-XXX

        ↓

🏠 Rua
🏘️ Bairro
🌆 Cidade
🗺️ Estado

```

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
```
Suba o banco utilizando o Docker Compose:

docker compose up -d

Serviços disponíveis:

| Serviço | Porta |
|---------|------:|
| PostgreSQL | 5432 |
| PgAdmin | 8085 |

Configuração do banco:

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



### Login
![login](docs/imagens/login.png)
![login](docs/imagens/login1.png)

---

### Usuario
![login](docs/imagens/usuario.png)

---

### Dashboard
![Dashboard](docs/imagens/dashboard5.png)
![Dashboard](docs/imagens/dashboard.png)

---

### Cadastro de Alunos
![Cadastro de Alunos](docs/imagens/aluno5.png)
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

### 🔒 Controle de Vagas

Cada turma possui uma quantidade máxima de vagas.

**Exemplo:**

| Informação | Quantidade |
|---|---:|
| Vagas | 5 |
| Vagas disponíveis | 5 |

Após a confirmação de uma matrícula:

| Informação | Quantidade |
|---|---:|
| Vagas | 5 |
| Vagas disponíveis | 4 |

Quando não existem mais vagas:

| Informação | Quantidade |
|---|---:|
| Vagas | 5 |
| Vagas disponíveis | 0 |

Nesse cenário, uma nova tentativa de confirmação de matrícula é bloqueada pelo sistema.

O sistema retorna a mensagem:

> ❌ **Não há vagas disponíveis.**

### 🔐 Controle de Concorrência

O controle de concorrência das confirmações de matrícula é importante para garantir a consistência das vagas quando **dois ou mais usuários tentam confirmar uma matrícula simultaneamente**.

O objetivo é impedir que duas confirmações sejam processadas ao mesmo tempo e ultrapassem a quantidade máxima de vagas disponível na turma.

---

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
| Testes unitários | ✅ |
| CRUD Completo | ✅ |
| Testes Automatizados | 🔄 |
| Spring Security | 🔄 |
| Deploy | 🔄 |

## 🚀 Próximas Evoluções

- Implementação de Spring Security.
- Autenticação utilizando JWT.
- Controle de autorização por perfil.
- Testes unitários.
- Testes de integração.
- Melhorias no controle de concorrência das matrículas.
- Deploy em nuvem.
- Pipeline CI/CD.
- Observabilidade com Spring Boot Actuator.
- Monitoramento da aplicação.
---

## 👨‍💻 Desenvolvedor

**Diego Medeiros Jesus**

Desenvolvedor Java Backend / Full Stack

- GitHub: https://github.com/diego1medeiros
- LinkedIn: www.linkedin.com/in/diego-medeiros-jesus-50746717a

## 📄 Licença

- Java
- Spring Boot
- JSF
- PrimeFaces
- PostgreSQL
- Docker
- APIs REST

---

## 📚 Aprendizados

Durante o desenvolvimento deste projeto foram aplicados conceitos como:

- Arquitetura Cliente-Servidor
- APIs REST
- DTOs
- ModelMapper
- Spring Boot
- Spring Data JPA
- Hibernate
- Regras de negócio
- Flyway
- PostgreSQL
- Docker
- WebClient
- JSF
- PrimeFaces
- PhaseListener
- Sessão HTTP
- Integração com serviços externos
- Validação de dados
- Controle de vagas
- Git e GitHub
- GitHub Actions

⭐ Se este projeto foi útil para você, deixe uma estrela no repositório!
