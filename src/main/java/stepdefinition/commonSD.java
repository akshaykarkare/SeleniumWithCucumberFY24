package stepdefinition;

import context.TestContext;
import context.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class commonSD extends context.util{

    public commonSD(TestContext context){
        super(context);
    }

    @Given("^Open \"([^\"]*)\" URL$")
    public void open_url(String url) throws IOException {
        System.out.println("-------------------------STEP INIT-----------------");
        hooks.scenario.log(hooks.scenario.getName() + " ---storing output values");
        System.out.println(url + Thread.currentThread().getId());
        openUrl(prop.getProperty(url));
    }

    @And("^quite$")
    public void quite_brower(){
        quit();
    }
}
