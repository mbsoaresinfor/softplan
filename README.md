# Teste do marcelo


Desenvolvedor: Marcelo Soares

# Informações/Arquitetura
Foi utilizado SpringBoot para desenvolvimeto da ApiRest, com banco de dados H2(memória).
O projeto está como microserviço, atraves de um container docker. 
Na parte dos testes, foi realizado unitários e integrações. A documentação está em swagger.
Na camada da api, é realizado validações básicas de entrada, onde para acessar ela é necessário
autenticação básica. Após, isso é encaminhado para a camada de serviço. Nesta, é realizado
validações na  Vo (valueObject). Validado, é transformado para uma entity, onde é encaminhado
para a camada de dados. Por fim é persistido. 

# Requisitos
Java 7+
docker

# Bibliotecas usadas
Spring Boot
Spring jpa
spring security
BeanValidation
H2 (banco de dados memória)
swagger
modelmapper
junit


# Instalação e execução

Para executar o container, pode ser execuado o comando abaixo: (necessita do docker instalado)

docker  build --tag test_marcelo .

docker run -p 8080:8080 test_marcelo


# Autenticação
existe autenticação básica http para alguns recursos:

usuario: marcelo
 
senha: 123

# API
Foi desenvolvido uma Api de estudante (v1), seguindo rigorisamente os requisitos.
Esta api é generica, onde caso necessite evoluir ela, seria algo bem fácil.

A documentação está no link: http://localhost:8080/swagger-ui.html

EndPoint:

V1: http://localhost:8080/v1/student

