package stepdefinition;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Step;

public class hooks extends context.util{

    public static Scenario scenario;

    public hooks(TestContext context) {
        super(context);
    }

    @Before
    public void beforeScenarioActions(Scenario scenario){
        this.scenario=scenario;
        System.out.println("before scenario");
        System.out.println(scenario.getName());
        System.out.println("TAGS : "+ scenario.getSourceTagNames());
        //scenario.log(scenario.getSourceTagNames().toString());

    }

    @After
    public void afterScenarioActions(Scenario scenario){
        System.out.println("after scenario");
        System.out.println(scenario.getName());
        System.out.println("STATUS "+ scenario.getStatus());
    }

    @BeforeStep
    public void beforeScenarioStepActions(Scenario scenario){
        System.out.println("before step");

    }
}
