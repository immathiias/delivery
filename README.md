# Projeto Delivery
Um projeto back-end para aplicativos de Delivery feito por Mathias.

## Índice
- <a href="#funcionalidades-do-projeto">Funcionalidades do Projeto</a>
- <a href="#como-rodar-o-projeto">Como executar a aplicação</a>
- <a href="#metodos-de-cliente">Métodos do Cliente</a>
- <a href="#metodos-de-autenticacao">Métodos de Autenticação</a>
- <a href="#metodos-de-pedido">Métodos de Pedido</a>
- <a href="#metodos-de-entrega">Métodos de Entrega</a>
- <a href="#tecnologias-utilizadas">Tecnologias utilizadas</a>
- <a href="#proximos-passos">Próximos passos</a>
- <a href="#autor">Autor</a>

## 🖥️Funcionalidades do Projeto
- [x] Cadastro de cliente
- [x] Cadastro de endereço
- [x] Login
- [x] Fazer pedido
- [x] Entrega do pedido

## 🌍Como rodar o projeto
- Necessário possuir o JVM e o JAVA instalado na máquina
- Baixar o seguinte arquivo [Delivery](https://mediafire.com)
- Para executar basta abrir e o projeto irá iniciar na porta 8080

#  📱Metodos do Cliente
```
# Criar um cliente
Para criar um cliente siga estes passos:

1. Utilizar a seguinte URL com o método POST:
- https://hostname.com/delivery/api/public/v1/cliente
2. Enviar na requisição um body com as seguintes informações:
- email, senha, nome e telefone;
3. Deve retornar o status code 201 CREATED.

# Buscar todos os Clientes
Para buscar todos os clientes siga estes passos:

1. Utilizar a seguinte URL com o método GET:
- https://hostname.com/delivery/api/public/v1/cliente
2. Deve retonar o status code 200 OK.

# Buscar um Cliente através do ID
Para buscar um cliente atráves do ID siga estes passos:

1. Deve ser utilizado um ID de Cliente válido;
2. O cliente deve estar logado;
3. Utilizar a seguinte URL com o método GET:
- https://hostname.com/delivery/api/public/v1/cliente/{idCliente}
4. Deve retonar o status code 200 OK.

# Alterar um cliente através do ID
Para alterar um cliente existente siga estes passos:

1. Deve ser utilizado um ID de Cliente válido;
2. O cliente deve estar logado;
3. Utilizar a seguinte URL com o método PATCH:
- https://hostname.com/delivery/api/public/v1/cliente/{idCliente}
4. Pode alterar na requisição body as seguintes informações:
- nome e telefone;
5. Deve retornar o status code 204 NO_CONTENT.

# Deletar um cliente através do ID
Para deletar um cliente existente siga estes passos:

1. Deve ser utilizado um ID de Cliente válido;
2. O cliente deve estar logado;
3. Utilizar a seguinte URL com o método DELETE:
- https://hostname.com/delivery/api/public/v1/cliente/{idCliente}
4. Deve retornar o status code 204 NO_CONTENT.
```

#  📱Metodos de Autenticacao
```
# Autenticar um Cliente
Para autenticar um cliente siga estes passos:

1. É necessário possuir o email e senha válidos
2. Utilizar a seguinte URL com o método POST:
- https://hostname.com/delivery/api/public/v1/autenticacao

# Reativar autenticação de um Cliente
1. É necessário possuir o token expirado
2. Utilizar a seguinte URL com o método POST:
- https://hostname.com/delivery/api/public/v1/autenticacao/reativacao
```

#  📱Metodos de Pedido
```
# Cliente realiza um Pedido
Para um cliente realizar um pedido deve seguir estes passos:

(Caso o cliente não tenha nenhum endereço cadastrado)
1. É necessário o cliente estar logado;
2. Utilizar a seguinte URL com o método POST:
- https://hostname.com/delivery/api/v1/pedido/criaEndereco/{idCliente}
3. Enviar na requisição um body com as seguintes informações:
- produto, detalhesPedido, endereçoEntrega;
4. Deve retornar o status code 201 CREATED.

# Buscar todos os Pedidos de um Cliente
Para buscar todos os pedidos de um cliente siga estes passos:

1. É necessário o cliente estar logado;
2. Utilizar a seguinte URL com o método GET:
- https://hostname.com/delivery/api/v1/pedido/{idCliente}
3. Deve retonar o status code 200 OK.

# Buscar um Pedido de um Cliente através do ID
Para buscar um pedido de um cliente atráves do ID siga estes passos:

1. É necessário o cliente estar logado;
3. Utilizar a seguinte URL com o método GET:
- https://hostname.com/delivery/api/v1/pedido/{idCliente}/{idPedido}
4. Deve retonar o status code 200 OK.

# Alterar um pedido de um cliente através do ID
Para alterar um pedido de um cliente siga estes passos:

1. É necessário o cliente estar logado;
2. Utilizar a seguinte URL com o método PATCH:
- https://hostname.com/delivery/api/v1/pedido/{idCliente}/{idPedido}
3. Pode alterar na requisição body as seguintes informações:
- produto e detalhesPedido;
4. Deve retornar o status code 204 NO_CONTENT.

# Deletar um pedido de um cliente através do ID
Para deletar um pedido de um cliente existente siga estes passos:

1. É necessário o cliente estar logado;
2. Utilizar a seguinte URL com o método DELETE:
- https://hostname.com/delivery/api/v1/pedido/{idCliente}/{idPedido}
3. Deve retornar o status code 204 NO_CONTENT.
```

#  📱Metodos de Entrega
```
# Entrega do Pedido é realizada
Para realizar uma entrega deve seguir estes passos:

1. É necessário o pedido estar atribuido a um cliente;
2. Utilizar a seguinte URL com o método POST:
- https://hostname.com/delivery/api/v1/pedido/entrega/{idCliente}/{idPedido}
3. Enviar na requisição um body com as seguintes informações:
- dataHoraDaEntrega;
4. Deve retornar o status code 201 CREATED.

# Verificar horário que um Pedido foi entregue
Para verificar o horário que o pedido foi entregue siga estes passos:
(mesmo passo de buscar um pedido por id)

1. Utilizar a seguinte URL com o método GET:
- https://hostname.com/delivery/api/v1/pedido/{idCliente}/{idPedido}
2. Deve retonar o status code 200 OK.

# Alterar uma entrega de um pedido
Para alterar uma entrega de um pedido siga estes passos:

1. Utilizar a seguinte URL com o método PATCH:
- https://hostname.com/delivery/api/v1/pedido/entrega/{idCliente}/{idPedido}
3. Pode alterar na requisição body as seguintes informações:
- dataHoraDaEntrega;
4. Deve retornar o status code 204 NO_CONTENT.

# Deletar a entrega de um pedido
Para deletar a entrega de um pedido existente siga estes passos:

1. Utilizar a seguinte URL com o método DELETE:
- https://hostname.com/delivery/api/v1/pedido/entrega/{idCliente}/{idPedido}
2. Deve retornar o status code 204 NO_CONTENT.
```

## ⚙️Tecnologias utilizadas
- Java
- Maven Spring Boot
- MongoDB

# Proximos passos
- Sistema de Produtos
- Calcular frete
- Adicionar rastreamento de entrega

## Autor
Manoel Mathias