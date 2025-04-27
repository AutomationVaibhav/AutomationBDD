package org.example.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties properties;

    public  static String getProperty(String key){
        if(properties==null){
            try {
                FileInputStream file=new FileInputStream("src/main/resources/config.properties");
                properties=new Properties();
                properties.load(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties.getProperty(key);
    }
}
