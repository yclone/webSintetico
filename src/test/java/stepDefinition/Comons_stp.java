package stepDefinition;

import commons.Comands;
import commons.DriverFactory;
import io.cucumber.java.pt.Dado;

public class Comons_stp {

    Comands comands;
    @Dado("que eu acesse o sistema {string}")
    public void queEuAcesseOSistema(String arg0) {
        comands = new Comands(DriverFactory.getDriver());
        comands.Navegate(arg0);
    }
}
