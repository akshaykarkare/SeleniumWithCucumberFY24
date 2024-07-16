package context;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import stepdefinition.hooks;

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

    public void testDriverFunctions(){
        driver.switchTo().newWindow(WindowType.WINDOW);
        ChromeOptions cops = new ChromeOptions();
    }

    public void sendemail(){
        Properties props = new Properties();
        // this will set host of server you can change based on your requirement
        props.put("mail.smtp.host","smtp.gmail.com");

        // set the port of socket factory
        props.put("mail.smtp.socketFactory.port","465");

        // set socket factory
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        // set the authentication to true
        props.put("mail.smtp.auth", "true");

        // set the port of SMTP server
        props.put("mail.smtp.port", "465");


        //Session
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("akshay.karkare@gmail.com","!Aryan0305!");
            }
        });


        try {
            //From and to details
            Message message = new MimeMessage(session);
            message.setSubject("Selenium with Docker cucumber reports");
            message.setFrom(new InternetAddress("from"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("To"));

            //Set body
            BodyPart messageBodyPart1  = new MimeBodyPart();
            messageBodyPart1.setText("This is mail body");

            BodyPart messageBodyPart2 = new MimeBodyPart();
            String file = "/target/cucumber-reports/advance-reports/cucumber-html-reports/overview-feature.html";
            String filename = System.getProperty("user.dir")+ file;

            // Create data source and pass the filename

            DataSource source = new FileDataSource(filename);

// set the handler
            messageBodyPart2.setDataHandler(new DataHandler(source));

// set the file
            messageBodyPart2.setFileName(filename);

            // Create object of MimeMultipart class
            Multipart multipart = new MimeMultipart();

// add body part 1
            multipart.addBodyPart(messageBodyPart2);

// add body part 2
            multipart.addBodyPart(messageBodyPart1);

// set the content
            message.setContent(multipart);

// finally send the email
            Transport.send(message);



        }catch (MessagingException exception){
            System.out.println(exception.getStackTrace());
        }




    }
}
