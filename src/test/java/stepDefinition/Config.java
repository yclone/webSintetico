package stepDefinition;


import commons.DriverFactory;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;


import java.net.MalformedURLException;

public class Config {

    private static Scenario scenario;

    public static Scenario getScenario() {
        return scenario;
    }

    @Before("@Chrome")
    public void beforeChrome(Scenario scenario) throws MalformedURLException {
        DriverFactory.setDriver(2);
        Config.scenario = scenario;
    }
    @Before("@Firefox")
    public void beforeFirefox() throws MalformedURLException {
        DriverFactory.setDriver(1);
    }
    @Before("@Teste")
    public void beforeteste() throws MalformedURLException {
        DriverFactory.setDriver(3);
    }


    @After
    public void FechaNavegador(Scenario scenario){
        if (scenario.isFailed()) {
//            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "text/plain", "fail_status.txt");
//            scenario.attach(screenshot, "image/png", scenario.getName());
            final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        } else {
//            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "text/plain", "success_status.txt");
            final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        DriverFactory.closeDriver();
    }
}
