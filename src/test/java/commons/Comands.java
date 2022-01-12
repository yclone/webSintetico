package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Comands extends JSWaiter{

    WebDriver driver;
    public static WebDriverWait wait;

    public Comands(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        setDriver(driver);
        waitGifLoading(By.id("carregamento"));
        waitForLoadPage();
    }

    public Comands Navegate(String url) {
        driver.switchTo().defaultContent();
        driver.manage().deleteAllCookies();
        driver.get(url);
        return new Comands(driver);
    }


    public Comands digitar(WebElement item, String value) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(item));
            driver.switchTo().defaultContent();
            item.clear();
            item.sendKeys(value);
            item.sendKeys(Keys.TAB);
        } catch (NullPointerException e) {
            System.err.println(e);
        }
        return new Comands(driver);
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
        return new Comands(driver);
    }

    public Comands selecionar(WebElement itemSelect, WebElement itemSelect1, String arg1) {
        wait.until(ExpectedConditions.visibilityOf(itemSelect));
        itemSelect.click();
        new Select(itemSelect1).selectByVisibleText(arg1);
        return new Comands(driver);
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
}
