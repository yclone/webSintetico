package suport.departamento;

import commons.Comands;
import commons.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Departamento_sup {

    WebDriver driver;
    Comands comands;

    @FindBy(xpath = "(//*[normalize-space(text()) and normalize-space(.)='Cadastrar'])[1]/following::span[1]")
    private WebElement btnListaDepartamentos;

//            xpath=(.//*[normalize-space(text()) and normalize-space(.)='Departamentos'])[1]/following::span[1]
    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Departamentos'])[1]/following::span[1]")
    private WebElement btnCadastrarDepartamentos;

    @FindBy(id = "nome")
    private WebElement cpoNomeDepartamento;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSalvarDepartamentos;

    @FindBy(xpath = "//div[@id='cadastro']/div/div/span/strong")
    private WebElement respModalSalvar;

    @FindBy(id = "ok_confirm")
    private WebElement ok_confirm;




    public Departamento_sup() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void ClicaBtnListar(){
        comands = new Comands(driver);
        comands.clicar(btnListaDepartamentos);
    }

    public List<WebElement> getAllListaDepartamento(){
        comands = new Comands(driver);
        WebElement tableProducts = driver.findElement(By.id("listagem"));
        List<WebElement> rows = tableProducts.findElements(By.tagName("tr"));

        //Print data from each row
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
                System.out.print(col.getText() + "\t");
            }
            System.out.println();
        }

        return rows;
    }

    public void ClicaBtnCadastrar() {
        comands = new Comands(driver);
        comands.clicar(btnCadastrarDepartamentos);
    }

    public void digitaNomeDep(String arg){
        comands = new Comands(driver);
        comands.digitar(cpoNomeDepartamento, arg);
        comands.clicar(btnSalvarDepartamentos);
    }

    public String getTextModal(){
        comands = new Comands(driver);
        return comands.getText(respModalSalvar);
    }

    public void removeCadastro(){
        comands = new Comands(driver);
        comands.clicar(btnListaDepartamentos);
        List<WebElement> rows = getAllListaDepartamento();

        ArrayList<String> arr = new ArrayList<>();
        String idElemento = null;
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
                arr.add(col.getText());
            }
            for (String resp : arr) {
                if(resp.equals("dep cria sintetico")){
                    idElemento = arr.get(0);
                }
            }
            arr.clear();
        }
        driver.findElement(By.xpath("//button[@id='btn_departamentos/excluir/"+idElemento+"']/span")).click();
        comands.clicar(ok_confirm);

    }
}
