# Student Challenge

## Execução

 - Para execução do Projeto, deve ser executada apenas a classe main "MediaChallangerApplication.java"
 - O banco de dados é o H2 então não é necessario configurações.
 - Para inserção de uma nova media é necessario criar uma conta.
 - Após autenticado o token do tipo bearer deve ser enviado junto aos métodos HTTP para inserção de midia.
 - A documentação está localizado no painel padrão do swagger. Pode ser encontrada no endereço:
    - localhost:8080/swagger-ui.html
    
## Exemplos

### POST - Usuário
```javascript
{
    "nomeCompleto": "Matheus Bruggemann Vieira",
    "username": "noft",
    "email": "matheusbvieira@hotmail.com",
    "password" : "123456",
    "telefone": "48991466677",
    "sexo": "MASCULINO",
    "dataDeNascimento": "29/11/2000"
}
```

### PUT - Usuário
```javascript
{
    "nomeCompleto": "Matheus Bruggemann Vieira",
    "email": "matheusbvieira@hotmail.com",
    "telefone": "48991466677"
}
```
### POST - Midia

    -Para inserção de midia deve ser enviados 2 multipart.
    -Um deles com o nome "media" e uma media de no max 8mb para inserção.
    -O outro deve ter o nome "data" e passar os parametros da media ex:

```javascript
{
    "nome": "FotoSspring",
    "descricao": "é uma foto da logo do spring boot",
    "dataDeCriacao": "22/11/2020"
}
```

### PUT - Midia

    -Para inserção de midia deve ser enviados 2 multipart.
    -Um deles com o nome "media" e uma media de no max 8mb para inserção.
    -O outro deve ter o nome "data" e passar os parametros da media ex:

```javascript
{
    "nome": "FotoSpring",
    "descricao": "é uma foto da logo do spring boot"
}
```


### POST - Autenticação
```javascript
{
	"email" : "matheusbvieira@hotmail.com",
	"senha": "123456"
}
```
