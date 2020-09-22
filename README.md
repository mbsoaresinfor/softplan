# Teste do marcelo


Desenvolvedor: Marcelo Soares

# Informações
O projeto contempla todos os requisitos obrigatórios e grande parte
dos extras. Foi utilizado SpringBoot para desenvolvimeto da ApiRest, com banco de dados H2(memória).
Na parte dos testes, foi realizado unitários e integrações. Aa documentação está em swagger.
No front, foi usado AngularJs.

# Instalação e execução

Buscando no docker-hub

https://hub.docker.com/repository/docker/mbsoaresinfor/test_marcelo

TAG: 4

docker pull mbsoaresinfor/softplan:4

docker run -p 8080:8080 mbsoaresinfor/softplan:4


# Autenticação
existe autenticação básica http para alguns recursos:

usuario: marcelo
 
senha: 123

# API
Foi desenvolvido as 1 Api de estudante (v1), seguindo rigorisamente os requisitos.
Esta api é generica, onde caso necessite evoluir ela, seria algo bem fácil.

A documentação está no link: http://localhost:8080/swagger-ui.html

EndPoint:

V1: http://localhost:8080/v1/student
