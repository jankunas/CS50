package lt.jankunas.shop.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import lt.jankunas.shop.ConfigurationManager;

public class InFileConfigurationManager implements ConfigurationManager {

    FileInputStream fis = null;
    File configFile = null;
    private Properties propConfig;
    
    public InFileConfigurationManager(){
        propConfig = new Properties();
        try (FileInputStream fis = new FileInputStream("resources/config/properties");) {
            propConfig.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public String getParameter(String name) {
        if(propConfig.containsKey(name)){
            return propConfig.getProperty(name);
        }
        return null;
    }
}
