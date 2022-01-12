package commons;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    DesiredCapabilities capability;
    static String resp = "";
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    static String gridAddress = "172.18.0.2:4444";

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().quit();
        driver.remove();
    }

    public static void setDriver(int i) {
        RemoteWebDriver rd = null;
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
                    rd = new RemoteWebDriver(new URL("http://" + gridAddress + "/wd/hub"), capabilitiesF);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                driver.set(rd);
                break;
            case 2:
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", "chrome");
                capabilities.setCapability("browserVersion", "96.0");
                capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                        "enableVNC", true
//                        ,"enableVideo", true
                ));
                try {
                    rd = new RemoteWebDriver(new URL("http://" + gridAddress + "/wd/hub"), capabilities);
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
