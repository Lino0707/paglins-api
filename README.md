💰 Paglins API
API REST desenvolvida em Java Spring Boot para gerenciamento de pendências e saldo baseado no método de pagamento semanal da Shopee.
📋 Sobre o Projeto
O Paglins nasceu de uma necessidade real: controlar os ganhos semanais de entregas realizadas pela Shopee, gerenciar dívidas e acompanhar o saldo disponível de forma simples e organizada.
Fluxo de pagamento Shopee:

Segunda a Sábado → Usuário registra rotas realizadas com status OPEN
Todo Domingo → Rotas da semana fecham automaticamente (CLOSED) e entram no nextBalance
Toda Quinta-feira → Valores são liberados (PAID) e entram no balance do usuário


🚀 Tecnologias

Java 25
Spring Boot 4.0.5
Spring Data JPA
Oracle Database XE 21c
Swagger / OpenAPI (SpringDoc)
Lombok
JUnit 5 + Mockito (testes unitários)


📌 Endpoints
👤 User Controller
MétodoEndpointDescriçãoGET/paglins/usersListar todos os usuáriosPOST/paglins/usersCriar usuárioDELETE/paglins/users/{id}Deletar usuário
🗺️ Route Controller
MétodoEndpointDescriçãoPOST/paglins/routesRegistrar nova rotaGET/paglins/routes/user/{userId}Listar rotas por usuárioPATCH/paglins/routes/{id}Atualizar rotaDELETE/paglins/routes/{id}Deletar rota
💸 Debt Controller
MétodoEndpointDescriçãoPOST/paglins/debtsRegistrar dívidaGET/paglins/debts/user/{userId}Listar dívidas por usuárioPATCH/paglins/debts/{id}/payPagar dívidaDELETE/paglins/debts/{id}Deletar dívida

⚙️ Como rodar o projeto
Pré-requisitos

Java 17+
Maven
Oracle Database XE

Configuração do banco
No arquivo src/main/resources/application.properties, configure:
propertiesspring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
Rodando a aplicação
bash# Clone o repositório
git clone https://github.com/Lino0707/paglins-api.git

# Entre na pasta
cd paglins-api/demo

# Rode com Maven
./mvnw spring-boot:run
A API estará disponível em: http://localhost:3000
Documentação Swagger
Acesse a documentação interativa em:
http://localhost:3000/swagger-ui/index.html

🧪 Testes
bash./mvnw test
O projeto conta com testes unitários cobrindo os services de Debt e Route utilizando JUnit 5 e Mockito.

🔄 Agendamento Automático
O sistema possui um scheduler que roda automaticamente:

Todo Domingo à meia-noite → Rotas OPEN → CLOSED e soma no nextBalance
Toda Quinta-feira à meia-noite → Rotas CLOSED → PAID e move para o balance


👨‍💻 Autor
Desenvolvido por Felipe Lino
