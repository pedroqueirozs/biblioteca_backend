<h1 align="center">Biblioteca Digital — Backend</h1>

<p align="center">
  API REST do sistema de biblioteca digital acadêmico desenvolvido para a disciplina de Programação Orientada a Objetos (POO).
</p>

<p align="center">
  <img alt="License" src="https://img.shields.io/static/v1?label=license&message=MIT&color=49AA26&labelColor=000000">
</p>

## Tecnologias

- **Java 17**
- **Spring Boot 4.0.6** — web, JPA/Hibernate
- **H2** — banco em memória para desenvolvimento local
- **PostgreSQL (Neon)** — banco em produção
- **Docker** — containerização para deploy

## Pilares de POO implementados

| Pilar | Implementação |
|---|---|
| Classe abstrata | `Pessoa.java` — campos comuns e método abstrato `getTipoPessoa()` |
| Herança | `Usuario` e `Operador` estendem `Pessoa` |
| Interface | `Emprestavel.java` — contrato com `realizar()`, `devolver()`, `estaAtivo()` |
| Polimorfismo | `Emprestimo` implementa `Emprestavel` |
| Encapsulamento | `Livro.java` — todos os campos `private` com getters/setters |

## Endpoints disponíveis

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/livros` | Lista todos os livros |
| GET | `/api/livros/disponiveis` | Lista livros com estoque > 0 |
| GET | `/api/livros/buscar?titulo=X` | Busca por título ou autor |
| POST | `/api/livros` | Cadastra livro |
| PUT | `/api/livros/{id}` | Atualiza livro |
| DELETE | `/api/livros/{id}` | Remove livro |
| GET | `/api/usuarios` | Lista usuários |
| POST | `/api/usuarios` | Cadastra usuário |
| DELETE | `/api/usuarios/{id}` | Remove usuário |
| GET | `/api/emprestimos` | Lista todos os empréstimos |
| GET | `/api/emprestimos/ativos` | Lista empréstimos ativos |
| POST | `/api/emprestimos` | Realiza empréstimo `{ usuarioId, livroId }` |
| PUT | `/api/emprestimos/{id}/devolver` | Registra devolução |
| GET | `/api/operadores` | Lista operadores |
| POST | `/api/operadores` | Cadastra operador |

## Como rodar localmente

### Pré-requisitos

- Java 17
- Maven (ou use o wrapper `./mvnw` incluso no projeto)

### Passo a passo

1. **Clone o repositório:**

```bash
git clone https://github.com/pedroqueirozs/biblioteca_backend.git
cd biblioteca_backend
```

2. **Execute a aplicação:**

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

O banco H2 em memória é criado automaticamente. As tabelas são recriadas a cada reinicialização (`ddl-auto=create-drop`).

3. **Acesse o console do H2 (opcional):**

Abra `http://localhost:8080/h2-console` no navegador e use as credenciais:

```
JDBC URL:  jdbc:h2:mem:bibliotecadb
Username:  sa
Password:  (deixar em branco)
```

### Rodando os testes

```bash
./mvnw test
```

## Como rodar com Docker

```bash
docker build -t biblioteca-backend .
docker run -p 8080:8080 biblioteca-backend
```

## Deploy em produção (Render)

O backend é implantado como **Web Service via Docker** no Render.

### Variáveis de ambiente necessárias no Render

| Variável | Descrição |
|---|---|
| `SPRING_PROFILES_ACTIVE` | Deve ser `prod` para ativar o perfil de produção |
| `DATABASE_URL` | URL de conexão JDBC do PostgreSQL (Neon) |
| `DB_USERNAME` | Usuário do banco |
| `DB_PASSWORD` | Senha do banco |

Exemplo de `DATABASE_URL`:
```
jdbc:postgresql://<host>.neon.tech/neondb?sslmode=require
```

> O free tier do Render hiberna após 15 min de inatividade. O primeiro acesso pode levar 30–60 segundos (cold start).

URL de produção: `https://biblioteca-backend-13i8.onrender.com`

## Estrutura do projeto

```
src/main/java/com/biblioteca/digital/biblioteca/
├── model/
│   ├── Pessoa.java          ← classe abstrata (@MappedSuperclass)
│   ├── Usuario.java         ← extends Pessoa
│   ├── Operador.java        ← extends Pessoa
│   ├── Livro.java           ← encapsulamento
│   ├── Emprestimo.java      ← implements Emprestavel
│   └── Emprestavel.java     ← interface
├── controller/              ← endpoints REST (@CrossOrigin *)
├── service/                 ← regras de negócio
├── repository/              ← JPA repositories
└── exception/
    ├── RegraDeNegocioException.java   ← exceção de domínio (HTTP 400)
    └── GlobalExceptionHandler.java   ← captura e formata erros JSON
```

---

<p align="center">Feito por Pedro Douglas G. Queiroz</p>
