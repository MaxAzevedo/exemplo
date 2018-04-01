# CRUD com Spring Boot - Cliente

#Tecnologias

- JAVA 1.8
- Spring Boot 1.5.9
- Spring JPA
- Jersey
- MySQL
- Maven
- JUnit

#Observações Gerais
- O Projeto foi criado utilizando a versão mais recente do Spring Boot
- Os testes são realizados passando através de requisições GET, POST, PUT ou DELETE.
- Os serviços diponíveis são:
	- /api/cliente [GET]
	- /api/clientes [GET]
	- /api/cliente [POST]
	- /api/cliente [PUT]
	- /api/cliente [DELETE]
- Para executar o serviço, vá até o diretório e execute o seguinte comando
	$ mvn spring-boot:run

#/api/cliente [GET]
- Serviço que busca um cliente por email. Esse email é passado como query parameter na requição.
	Ex: /api/cliente?email=exemplo@exemplo.com

#/api/clientes [GET]
- Serviço que busca slista de todos os clientes.
	Ex: /api/clientes
	
#/api/cliente [POST]
- Serviço responsável por persistir um cliente na base de dados.
	Ex: /api/cliente
		{
		   "nome": "Exemplo",
		   "email": "exemplo@gmail.com",
		   "celular": "83988888888"
		}
	
#/api/cliente [PUT]
- - Serviço responsável por alterar um cliente na base de dados.
	Ex: /api/cliente
		{
		   "nome": "Exemplo Alterado",
		   "email": "exemplo.alterado@gmail.com",
		   "celular": "83999999999"
		}
		
#/api/cliente [DELETE]
- Serviço que exclui um cliente por email. Esse email é passado como query parameter na requição.
	Ex: /api/cliente?email=exemplo@exemplo.com