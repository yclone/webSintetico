#language:pt
#Author: Vinicius Marra Santos

@departamento
Funcionalidade: testes de cadastro e listagem de departamentos

  @listaDepartamentos @Chrome
  Cenario: pega a lista de departamentos
    Dado que eu acesse o sistema "http://192.168.0.197:8081/"
    Quando clico na aba departamentos no botão listar
    Entao devo apresentar a lista de departamentos cadastrados no sistema

  @criaDepartamentos @Chrome
  Cenario: adiciona um departamento novo
    Dado que eu acesse o sistema "http://192.168.0.197:8081/"
    Quando clico na aba departamentos no botão cadastrar
    E digito no campo Departamento o valor "dep cria sintetico"
    Entao devo apresentar a mensagem "Departamento inserido com sucesso."
    E removo o cadastro criado