package commons;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    DesiredCapabilities capability;
    static String resp = "";
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
//    static String gridAddress = "'127.0.0.1:4444/";
    static String gridAddress = "http://selenium-hub:4444";
//    static String gridAddress = "http://172.18.0.11:4444";

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().quit();
        driver.remove();
    }

    public static void setDriver(int i) throws MalformedURLException {
        WebDriver rd = null;
        switch (i) {
            case 1:
                FirefoxOptions optionsF = new FirefoxOptions();
                DesiredCapabilities capabilitiesF = new DesiredCapabilities();
                optionsF.addArguments("--start-maximized");
//			optionsF.addArguments("--headless");
                optionsF.setCapability("marionette", true);
                capabilitiesF.setBrowserName("firefox");
                capabilitiesF.setCapability("enableVNC", true);
                capabilitiesF.setCapability("enableVideo", false);
                capabilitiesF.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsF);
//			 optionsF.addArguments("--headless");
                try {
                    rd = new RemoteWebDriver(new URL("http://" + gridAddress + "/session"), capabilitiesF);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                driver.set(rd);
                break;
            case 2:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--ignore-ssl-errors=yes");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--remote-allow-origins=*");
//                options.addArguments("--headless=new");
                try {
                    rd = new RemoteWebDriver(new URL(gridAddress), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                driver.set(rd);
                break;
            case 3:
                ChromeOptions optionsTest = new ChromeOptions();
                optionsTest.addArguments("--ignore-ssl-errors=yes");
                optionsTest.addArguments("--ignore-certificate-errors");
                optionsTest.addArguments("--remote-allow-origins=*");
//                optionsTest.addArguments("--headless=new");
                try {
                    rd = new RemoteWebDriver(new URL("http://localhost:8888"), optionsTest);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                driver.set(rd);
                break;
            default:
                break;
        }
    }
}
