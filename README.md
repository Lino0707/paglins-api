# 💰 Paglins API

API REST desenvolvida em **Java Spring Boot** para gerenciamento de pendências financeiras e controle de saldo baseado no ciclo de pagamento semanal da Shopee.

---

## 📖 Sobre o Projeto

O **Paglins** nasceu de uma necessidade real: controlar os ganhos semanais de entregas realizadas pela Shopee, registrar dívidas e acompanhar o saldo de forma automatizada.

### 🔄 Ciclo de pagamento automático

| Dia | Ação |
|-----|------|
| **Seg - Sáb** | Usuário registra rotas com status `OPEN` |
| **Todo Domingo** | Rotas fecham automaticamente → `CLOSED` e somam no `nextBalance` |
| **Toda Quinta** | Valores são liberados → `PAID` e entram no `balance` do usuário |

> A transição de status é feita automaticamente por um **scheduler** no backend, sem necessidade de intervenção manual.

---

## 🚀 Tecnologias

- **Java 25**
- **Spring Boot 4.0.5**
- **Spring Data JPA**
- **Oracle Database XE 21c**
- **Swagger / OpenAPI** (SpringDoc)
- **Lombok**
- **JUnit 5 + Mockito**

---

## 📁 Estrutura do Projeto

```
src/
├── main/
│   └── java/com/example/demo/
│       ├── controller/
│       │   ├── UserController.java
│       │   ├── RouteController.java
│       │   └── DebtController.java
│       ├── model/
│       │   ├── User.java
│       │   ├── Route.java
│       │   └── Debt.java
│       ├── repository/
│       │   ├── UserRepository.java
│       │   ├── RouteRepository.java
│       │   └── DebtRepository.java
│       └── service/
│           ├── UserService.java
│           ├── RouteService.java
│           ├── DebtService.java
│           └── SchedulerService.java
└── test/
    └── java/com/example/demo/
        └── service/
            ├── DebtServiceTest.java
            └── RouteServiceTest.java
```

---

## 📌 Endpoints

### 👤 User Controller — `/paglins/users`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/paglins/users` | Listar todos os usuários |
| `POST` | `/paglins/users` | Criar novo usuário |
| `DELETE` | `/paglins/users/{id}` | Deletar usuário |

#### Modelo - User
```json
{
  "name": "Felipe",
  "lastName": "Lino",
  "email": "felipe@email.com",
  "balance": 0.0,
  "nextBalance": 0.0
}
```

---

### 🗺️ Route Controller — `/paglins/routes`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/paglins/routes` | Registrar nova rota |
| `GET` | `/paglins/routes/user/{userId}` | Listar rotas por usuário |
| `PATCH` | `/paglins/routes/{id}` | Atualizar dados de uma rota |
| `DELETE` | `/paglins/routes/{id}` | Deletar uma rota |

#### Modelo - Route
```json
{
  "userId": 1,
  "routeDate": "2026-04-05",
  "totalEarnings": 150.00,
  "packages": 50.00,
  "status": "OPEN"
}
```

---

### 💸 Debt Controller — `/paglins/debts`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/paglins/debts` | Registrar nova dívida |
| `GET` | `/paglins/debts/user/{userId}` | Listar dívidas por usuário |
| `PATCH` | `/paglins/debts/{id}/pay` | Pagar uma dívida (stored procedure) |
| `DELETE` | `/paglins/debts/{id}` | Deletar uma dívida |

#### Modelo - Debt
```json
{
  "userId": 1,
  "description": "Conta de luz",
  "amount": 200.00,
  "dueDate": "2026-04-10",
  "status": "PENDING"
}
```

---

## ⚙️ Como rodar o projeto

### Pré-requisitos

- Java 17+
- Maven
- Oracle Database XE 21c

### Configuração

1. Clone o repositório:
```bash
git clone https://github.com/Lino0707/paglins-api.git
cd paglins-api/demo
```

2. Copie o arquivo de variáveis de ambiente:
```bash
cp .env.example .env
```

3. Preencha o `.env` com suas credenciais:
```properties
DB_URL=jdbc:oracle:thin:@localhost:1521:xe
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```

4. Rode a aplicação:
```bash
./mvnw spring-boot:run
```

A API estará disponível em: **`http://localhost:3000`**

---

## 📚 Documentação Swagger

Acesse a documentação interativa em:

```
http://localhost:3000/swagger-ui/index.html
```

---

## 🧪 Testes

```bash
./mvnw test
```

O projeto conta com **9 testes unitários** cobrindo os services de `Debt` e `Route` com JUnit 5 e Mockito, sem necessidade de conexão com banco de dados.

---

## 👨‍💻 Autor

Desenvolvido por **Felipe Lino**

[![GitHub](https://img.shields.io/badge/GitHub-Lino0707-black?logo=github)](https://github.com/Lino0707)
