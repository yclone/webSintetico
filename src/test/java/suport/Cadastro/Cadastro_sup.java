package suport.Cadastro;

import commons.Comands;
import commons.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cadastro_sup {


    WebDriver driver;
    Comands comands;

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "nome")
    private WebElement nome;

    @FindBy(name = "sobrenome")
    private WebElement sobrenome;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

//    @FindBy(name = "btnCadastrar")
    @FindBy(xpath = "//html/body/div/form/button")

    private WebElement btnCadastrar;

//    @FindBy(xpath = "//*[@id=\"mensagem\"]/div")
    @FindBy(xpath = "//*[@id=\"mensagem\"]/div")
    private WebElement lableCadastro;


    public Cadastro_sup() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void ClicaBtnCadastrar(){
        comands = new Comands(driver);
        comands.clicar(btnCadastrar);
    }

    public void digitaNome(String arg){
        comands = new Comands(driver);
        comands.digitar(nome, arg);
    }

    public void digitaSobrenome(String arg){
        comands = new Comands(driver);
        comands.digitar(sobrenome, arg);
    }

    public void digitaUserName(String arg){
        comands = new Comands(driver);
        comands.digitar(username, arg);
    }

    public void digitaEmail(String arg){
        comands = new Comands(driver);
        comands.digitar(email, arg);
    }

    public void digitaSenha(String arg){
        comands = new Comands(driver);
        comands.digitar(password, arg);
    }

    public void clicaBtnCadastrar(){
        comands = new Comands(driver);
        comands.clicar(btnCadastrar);
    }

    public String getTextModal(){
        comands = new Comands(driver);
        return comands.getText(lableCadastro);
    }

}
