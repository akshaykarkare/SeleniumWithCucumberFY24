package context;


import io.cucumber.java.an.E;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class WebDriverManager {

    private WebDriver driver;

    public WebDriverManager(){
    }

    public WebDriver getDriverOld(){
        if (driver==null){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public WebDriver getDriver(String driverName){
        if (driver==null){
            switch (driverName){
                case "chrome":

                    ChromeOptions options = new ChromeOptions();
                    //options.addArguments("--headless=new");
                    try {
                        driver = new RemoteWebDriver(new URL("http://seleniumchrome:4444"), options);
                        //driver = new ChromeDriver();
                    }catch (Exception e){
                        System.out.println("unable to initialize remote webdriver with chrome");
                    }

                    driver.manage().window().maximize();
                    break;
                case "firefox":
                    FirefoxOptions foptions = new FirefoxOptions();
                    try{
                        driver = new RemoteWebDriver(new URL("http://localhost:4444"), foptions);
                    }catch (Exception e){}

                    //driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                default:
                    System.out.println("browser name missing");
            }
        }
        return driver;
    }

}
