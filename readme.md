# User wishlist service
## Requisitos
- CRUD de clientes
  - Nome
  - Endereço de e-mail que deve ser único
  - Senha para autenticação
- Lista de produtos
  - Cada client pode ter uma lista de produtos favoritos
  - Quantidade ilimitada de produtos
  - Um produto não pode ser adicionado caso ele não exista
  - Um produto não pode estar duplicado na lista de favoritos    
- Serviço de renderização da lista:
  - Título, Imagem, Preço e o ID do produto
  - Quando existir um review para o produto, o mesmo será exibido por este dispositivo.
- Autenticação e autorização.

## Considerações gerais
- Para o processo de autenticação e autorização é utilizado o cadastro de usuários.
- É necessario ter um usuário registrado e autenticado para acessar os demais endpoints da API.
- O controle de autentição esta sendo feito na api usando tokens jwt, o ideal seria externalizar essa responsabilidade, utilizando um produto como o KeyCloak.
- A Api de wishlist utiliza o padrão CQRS, é utilizado um banco sql para persistência primaria e por eventos assíncronos a base de leitura é atualizada.
- Para simplificar a implementação, a sincronização da base de leitura é feita por uma fila no Rabbitmq, para uma solução mais robusta o ideal seria utilizar um sistema de stream de dados como o Kafka com a funcionada kafka connectors. 

## Tecnologias utilizadas
- Java 11 e SpringBoot
- Banco de dados postgres
- Mongodb como banco de leitura
- RabbitMQ como message broker
- Docker para execução

## Instruções para execução
 - Necessário ter docker e docker-compose instalado
 - No terminal navegar até a pasta da aplicação e executar o comando: ```docker-compose up --build```

## Autenticação
- Todas as operações com exceção da criação do usuário possuem autenticação
- Após ter criado o usuário é necessário gerar token na api ```/login```
- O token gerado deve ser enviado com prefixo ```Bearer``` no header ```Authorization```
- Os endpoints de manipulação e consulta da wishlist utilizam o usuário autenticado para buscar a wishlist

## Documentação
- Documentação no formato OpenAPI 3.0: [api-docs.yaml](api-docs.yaml)
- Exemplos de requests: [request.http](request.http)
## Endpoints
### Create User:
```
  curl --location --request POST 'http://localhost:8080/api/user' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "name": "Eduardo dos Santos",
  "email": "duduardo231@gmail.com",
  "password": "teste12",
  "confirmPassword": "teste12"
  }'
```
### Login:
```
  curl --location --request POST 'http://localhost:8080/login' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "login": "duduardo23@gmail.com",
  "password": "teste12"
  }'
```

### Get All user:
```
  curl --location --request GET 'http://localhost:8080/api/user' \
  --header 'Content-Type: application/json' \
  --header 'Authorization: Bearer <token>'
```   

### Get user by id:
```
  curl --location --request GET 'http://localhost:8080/api/user/66894797-ae89-4a76-a3ef-435a3d07d182' \
  --header 'Content-Type: application/json' \
  --header 'Authorization: Bearer <token>'
```   
### Add item to wishlist:
```
  curl --location --request POST 'http://localhost:8080/api/user/wishlist' \
  --header 'Content-Type: application/json' \
  --header 'Authorization: Bearer <token>' \
  --data-raw '{
  "productId": "79b1c283-00ef-6b22-1c8d-b0721999e2f0"
  }'
```   

### Get user wishlist:
```
  curl --location --request GET 'http://localhost:8080/api/user/wishlist' \
  --header 'Content-Type: application/json' \
  --header 'Authorization: Bearer <token>'
``` 

### Remove item from wishlist:
```
  curl --location --request DELETE 'http://localhost:8080/api/user/wishlist' \
  --header 'Content-Type: application/json' \
  --header 'Authorization: Bearer <token>' \
  --data-raw '{
  "productId": "79b1c283-00ef-6b22-1c8d-b0721999e2f0"
  }'
``` 