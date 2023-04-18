
<hr>

# FinanFine - Spring Boot API + Angular Interface

API para gerenciamento de empréstimos completa + crud de cadastro de clientes com Angular.
<hr>

## Utilize o Swagger para testar a aplicação:
Com a aplicação rodando, acesse ->
http://localhost:8080/swagger
<hr>

## Funcionalidades
### Backend:
- Cadastro de clientes com validação de cpf, cep e telefone

Validation:

CPF: via API Caelum Stella

CEP e Telefone: via Regex

- Consulta, edição e deleção de clientes com tratamento personalizado de exceções
- Identificador único autênticado (CPF)

Utilize um gerador de cpf para testar a aplicação: https://www.4devs.com.br/gerador_de_cpf

Para cep: 
https://www.4devs.com.br/gerador_de_cep

- Cadastro de empréstimos para clientes previamente cadastrados
- Empréstimo vinculado ao cliente no banco de dados + lista com a relação cliente id <-> empréstimo id
- Variedade de juros de acordo com o relacionamento do cliente
- Edição, deleção e consultas de empréstimos via identificador único (CPF) + id

<hr>

### Frontend
- Manipulação de rotas
- Consumo de api com HttpClient
- Execução de operações CRUD com HttpClient
- Manipulação de formulário com ReactiveForms
- Exceções personalizadas e alertas estilosos com <strong>SweetAlert</strong>
- Layouts limpos com Bootstrap

<hr>

### Extra
Banco de dados utilizado: H2 database (Banco de dados em memória)

com a aplicação rodando basta acessar -> http://localhost:8080/h2-console
para ter acesso ao banco

<hr>