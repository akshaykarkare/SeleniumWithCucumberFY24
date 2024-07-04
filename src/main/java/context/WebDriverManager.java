package context;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    private WebDriver driver;

    public WebDriverManager(){
    }

    public WebDriver getDriver(){
        if (driver==null){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

}
