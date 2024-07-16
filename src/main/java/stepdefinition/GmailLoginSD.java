package stepdefinition;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import context.TestContext;
import context.WebDriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.plugin.event.Node;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class GmailLoginSD extends context.util{


    public GmailLoginSD(TestContext context){
        super(context);
    }

    @Then("^click on search$")
    public void click_search() throws IOException {
        hooks.scenario.log(hooks.scenario.getName() + " ---storing output values");
        System.out.println("click on google search");
        driver.switchTo().frame("callout");
        click(locators.getProperty("GmailHomePageGmailPopUp"));
        driver.switchTo().defaultContent();
        click(locators.getProperty("GmailHomePageGmailLink"));
        fail();
    }
}
