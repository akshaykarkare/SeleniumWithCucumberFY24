package context;

import io.cucumber.java.Scenario;

public class TestContext {

    private WebDriverManager webdrivermanager;
    private PropertyFileManager profilemanager;

    public TestContext(){
        webdrivermanager = new WebDriverManager();
        profilemanager = new PropertyFileManager();

    }

    public WebDriverManager getWebDriverManager(){
        return webdrivermanager;
    }
    public PropertyFileManager getPropertyManager(){
        return profilemanager;
    }
}
