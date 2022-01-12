package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@CucumberOptions(features = "src/test/resources",
        glue = "stepDefinition",
        plugin = {"pretty", "html:target/cucumber-html-report.html", "json:target/cucumber.json"}
//				 ,tags = {"@CadCli"}
)
@RunWith(Cucumber.class)
public class RunCucumberTest {
}