package stepDefinition.Cadastro;

import commons.DriverFactory;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import stepDefinition.Config;
import suport.Cadastro.Cadastro_sup;

import java.nio.charset.Charset;
import java.util.Random;

public class Cadastro_stp extends Cadastro_sup {

    @Quando("digito os campos de cadastro corretamente")
    public void DigitaCamposCorretos() throws InterruptedException {
        // Armazena a imagem da tela
//        byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
//        Config.getScenario().attach(screenshot, "image/png", "cadastro_correto-antes.png");
        byte[] array = new byte[7]; // length is bounded by 7
        int alphabetSize = 26;

        for (int i = 0; i < array.length; i++) {
            array[i] = (byte) (new Random().nextInt(alphabetSize) + 'a');
        }

        String generatedString = new String(array, Charset.forName("UTF-8")); digitaUserName( generatedString );
        digitaNome(generatedString);
        digitaSobrenome(generatedString);
        digitaEmail(generatedString + "@teste.com");
        digitaSenha("123@" + generatedString);
        // Anexa a imagem ao cenário
//        Config.getScenario().attach(screenshot, "image/png", "cadastro_correto-depois.png");
        clicaBtnCadastrar();
        Thread.sleep(1000);
//        Config.getScenario().attach(screenshot, "image/png", "cadastro_correto-depois_btn.png");

    }

    @Entao("deve aparecer a mensagem {string}")
    public void validaCadastroComSucesso(String arg){
        String resp = getTextModal();
        Assert.assertEquals(arg, resp);
    }
}
