package context;

import io.cucumber.java.Scenario;

public class TestContext {

    private WebDriverManager webdrivermanager;

    public TestContext(){
        webdrivermanager = new WebDriverManager();
    }

    public WebDriverManager getWebDriverManager(){
        return webdrivermanager;
    }
}
