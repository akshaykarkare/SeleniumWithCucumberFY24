package stepdefinition;

import context.TestContext;
import io.cucumber.java.*;
import io.cucumber.plugin.event.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class hooks extends context.util{

    public static Scenario scenario;

    public hooks(TestContext context) {
        super(context);
    }

    @Before
    public void prerequisite(Scenario scenario){
        this.scenario=scenario;
        System.out.println("before scenario");
        System.out.println(scenario.getName());
        System.out.println("TAGS : "+ scenario.getSourceTagNames());
        //scenario.log(scenario.getSourceTagNames().toString());

    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        System.out.println("after scenario");
        System.out.println(scenario.getName());
        System.out.println("STATUS "+ scenario.getStatus());
        if (scenario.isFailed()){
            scenario.log("Failed! Please find attached screenshot");
            takeScreenshot();
            driver.quit();
        }
    }

    @BeforeStep
    public void beforeScenarioStepActions(Scenario scenario){
        System.out.println("before step");

    }
}
