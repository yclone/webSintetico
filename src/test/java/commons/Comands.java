package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Comands extends JSWaiter {

    private static WebDriver driver;
    public static WebDriverWait wait;

    public Comands(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitForLoadPage();
    }

    public static Comands getInstance() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
        }
        return new Comands(driver);
    }

    public Comands Navegate(String url) {
        driver.switchTo().defaultContent();
        driver.manage().deleteAllCookies();
        driver.get(url);
        return getInstance();
    }


    public Comands digitar(WebElement item, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(item));
        driver.switchTo().defaultContent();
        item.clear();
        item.sendKeys(value);
        item.sendKeys(Keys.TAB);

// Assert that the value was actually entered into the field
        assertEquals(value, item.getAttribute("value"));

        return getInstance();
    }


    public String getText(WebElement results) {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOf(results));
        String textoColetado = results.getText();
        System.out.println("Texto coletado : " + textoColetado);
        return textoColetado;
    }

    public Comands clicar(WebElement item) {
        wait.until(ExpectedConditions.elementToBeClickable(item));
        driver.switchTo().defaultContent();
        item.click();
        return getInstance();
    }

    public Comands selecionar(WebElement itemSelect, WebElement itemSelect1, String arg1) {
        wait.until(ExpectedConditions.visibilityOf(itemSelect));
        itemSelect.click();
        new Select(itemSelect1).selectByVisibleText(arg1);
        return getInstance();
    }

    public boolean validaResp(WebElement results, String resp) {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOf(results));
        boolean textoColetado = results.getText().equals(resp);
        return textoColetado;
    }

    public void scroll() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,150)");
    }

    public void limparCampos(By selector) {
        List<WebElement> campos = driver.findElements(selector);
        for (WebElement campo : campos) {
            campo.clear();
        }
    }
}
