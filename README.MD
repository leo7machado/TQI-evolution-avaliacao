
#Serviço de Empréstimo - Back-End

Desafio TQI para criação do back-end de um sistema de análise de crédito para uma empresa de empréstimo.

##Funcionalidadaes

- Cadastro de clientes
- Login
- Solicitação de empréstimo
- Acompanhamento das solicitações de empréstimo

###Utilizado
```
- Java 17
- PostgreSQL
- Spring Web
- Spring Data JPA
- Spring Security
```

###Registro
````
 POST /api/v1/registration - registrar usuário
````

###Empréstimo
````
POST /api/v1/loan       - requisitar um empréstimo
GET /api/v1/loan        - ver todos os empréstimos requisitados pelo usuário
GET /api/v1/loan/{id}   - ver em detalhes uma requisição específica
````