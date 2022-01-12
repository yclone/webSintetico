package stepDefinition;


import commons.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Config {

    @Before("@Chrome")
    public void beforeChrome() {
        DriverFactory.setDriver(2);
    }
    @Before("@Firefox")
    public void beforeFirefox() {
        DriverFactory.setDriver(1);
    }

    @After
    public void FechaNavegador(Scenario scenario){
        if (scenario.isFailed()) {
//            scenario.write("FAIL_" + scenario.getName() + "_" + scenario.getStatus());
//            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.embed(screenshot, "image/png");
        } else {
//            scenario.write("SUCESS_" + scenario.getName() + "_" + scenario.getStatus());
//            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.embed(screenshot, "image/png");
        }
        DriverFactory.closeDriver();
    }
}
