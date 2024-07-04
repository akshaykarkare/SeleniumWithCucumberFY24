package context;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class util {

    protected TestContext testContext;
    protected WebDriver driver;

    public util(TestContext context){
        testContext=context;
        driver = testContext.getWebDriverManager().getDriver();
    }

    public void openUrl(String url){
       driver.get(url);
    }
    public void click(String locator){
        driver.findElement(By.xpath(locator)).click();
    }

    public void quit(){
        driver.quit();
    }


}
