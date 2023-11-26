#language:pt
#Author: Vinicius Marra Santos

@CadastroCliente
Funcionalidade: eu como usuario do sistema
  desejo cadastrar um cliente
  de modo que esse cliente seja cadastrado no sistema

  @CadastroClienteComSucesso @Chrome
  Cenario: pega a lista de departamentos
    Dado que eu acesse o sistema "http://pet-front/"
#    Dado que eu acesse o sistema "http://localhost:8000/"
    Quando digito os campos de cadastro corretamente
    Entao deve aparecer a mensagem "Cadastrado com sucesso"
