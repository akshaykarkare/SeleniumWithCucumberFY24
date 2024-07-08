package context;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                default:
                    System.out.println("browser name missing");
            }
        }
        return driver;
    }

}
