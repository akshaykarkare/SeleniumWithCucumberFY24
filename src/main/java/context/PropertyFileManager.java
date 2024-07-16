package context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileManager {

    private Properties prop;

    public Properties getProp(String propType){
        String filePath=System.getProperty("user.dir");
        prop = new Properties();
        switch (propType){
            case "config":
                filePath = filePath+"/src/main/resources/config.properties";
                break;
            case "locators":
                filePath = filePath+"/src/main/resources/locators/locators.properties";
                break;
            default:
                System.out.println("Property file information which needs to be loaded missing");
        }

        try {
            File configFile = new File(filePath);
            FileInputStream fio = new FileInputStream(configFile);
            prop.load(fio);
        }catch (IOException ioException){
            System.out.println("config file not found");
        }

        return prop;
    }
}
