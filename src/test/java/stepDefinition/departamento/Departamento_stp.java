package stepDefinition.departamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import suport.departamento.Departamento_sup;

import java.util.List;

public class Departamento_stp extends Departamento_sup {

    @Quando("clico na aba departamentos no botão listar")
    public void clicoNaAbaDepartamentosNoBotãoListar() {
        ClicaBtnListar();
    }

    @Entao("devo apresentar a lista de departamentos cadastrados no sistema")
    public void devoApresentarAListaDeDepartamentosCadastradosNoSistema() {
        List<WebElement> rows = getAllListaDepartamento();
        boolean resp;
        int tamRow = rows.size();
        if (tamRow > 0){
            resp = true;
        } else {
            resp = false;
        }
        Assert.assertTrue(resp);
    }

    @Quando("clico na aba departamentos no botão cadastrar")
    public void clicoNaAbaDepartamentosNoBotãoCadastrar() {
        ClicaBtnCadastrar();
    }

    @E("digito no campo Departamento o valor {string}")
    public void digitoNoCampoDepartamentoOValor(String arg0) {
        digitaNomeDep(arg0);
    }

    @Entao("devo apresentar a mensagem {string}")
    public void devoApresentarAMensagem(String arg0) {
        Assert.assertEquals(arg0, getTextModal());
    }

    @E("removo o cadastro criado")
    public void removoOCadastroCriado() {
        removeCadastro();
    }
}
