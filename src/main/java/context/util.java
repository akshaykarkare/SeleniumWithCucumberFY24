package context;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import stepdefinition.hooks;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class util {

    protected TestContext testContext;
    protected WebDriver driver;
    protected Properties prop;
    protected Properties locators;

    public util(TestContext context){
        testContext=context;
        //get browser name on which automation needs to be executed
        prop = testContext.getPropertyManager().getProp("config");
        locators = testContext.getPropertyManager().getProp("locators");

        //driver initialization
        driver = testContext.getWebDriverManager().getDriver(prop.getProperty("browser"));
    }

    public void openUrl(String url) throws IOException {
       driver.get(url);
       takeScreenshot();
    }
    public void click(String locator){
        driver.findElement(By.xpath(locator)).click();
    }

    public void quit(){
        driver.quit();
    }

    public void takeScreenshot() throws IOException {
        TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        System.out.println(System.getProperty("user.dir"));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate= formatter.format(date);
        System.out.println(strDate.substring(0,1));

        String path = System.getProperty("user.dir")+"/target/cucumber-reports/screenshots/"+hooks.scenario.getName()+"_"+strDate.substring(0,2)+".png";
        File DestFile=new File(path);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
        hooks.scenario.attach(scrShot.getScreenshotAs(OutputType.BYTES),"image/png",hooks.scenario.getName());
    }

    public void takeFullpageScreenshot() throws IOException {
        FirefoxDriver scrShot =((FirefoxDriver)driver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getFullPageScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        System.out.println(System.getProperty("user.dir"));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate= formatter.format(date);
        System.out.println(strDate.substring(0,1));

        String path = System.getProperty("user.dir")+"/target/cucumber-reports/screenshots/"+hooks.scenario.getName()+"_Full_page"+"_"+strDate.substring(0,2)+".png";
        File DestFile=new File(path);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
        hooks.scenario.attach(scrShot.getScreenshotAs(OutputType.BYTES),"image/png",hooks.scenario.getName());
    }


}
