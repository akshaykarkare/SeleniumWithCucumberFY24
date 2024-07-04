import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"stepdefinition"},
        tags = "@demo",
        plugin = {"html:target/cucumber-reports/htmlReports.html","json:target/cucumber-reports/cucumber.json"},
        monochrome = true
)
public class runnerTest {


}
