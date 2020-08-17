# softplan
Teste para Softplan

Desenvolvedor: Marcelo Soares

# Informações
O projeto contempla todos os requisitos obrigatórios e grande parte
dos extras. Foi utilizado SpringBoot para desenvolvimeto da ApiRest, com banco de dados H2(memória).
Na parte dos testes, foi realizado unitários e integrações. Aa documentação está em swagger.
No front, foi usado AngularJs.

# Instalação e execução

Buscando no docker-hub

https://hub.docker.com/repository/docker/mbsoaresinfor/softplan

TAG: 4

docker pull mbsoaresinfor/softplan:4

docker run -p 8080:8080 mbsoaresinfor/softplan:4


# Autenticação
Conforme requisitos, existe autenticação básica http para alguns recursos:

usuario: softplan
 
senha: 123

# API
Foi desenvolvido as duas versões da Api de pessoas (v1,v2), seguindo rigorisamente os requisitos.
Pode ser utilizado as duas que o sistema vai continuar funcionando. Então é possível inserir
pessoas com endereço na v2, e listar na v1. Ou vice-versa. 

A documentação está no link: http://localhost:8080/swagger-ui.html

EndPoint:

V1: http://localhost:8080/v1/pessoas

V2: http://localhost:8080/v2/pessoas

Link gitHub: http://localhost:8080/source

# FrontEnd
http://localhost:8080/pessoasV1.html

# Código fonte
Pode ser visualizado o link acessando o endpoint:

http://localhost:8080/source

Senão o link é: https://github.com/mbsoaresinfor/softplan
