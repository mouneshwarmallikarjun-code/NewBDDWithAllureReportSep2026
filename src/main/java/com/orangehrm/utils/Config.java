package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    
    private static Properties properties;
    
    static {
        properties = new Properties();
        try {
            String configFilePath = System.getProperty("user.dir") + "/config.properties";
            FileInputStream file = new FileInputStream(configFilePath);
            properties.load(file);
            file.close();
        } catch (IOException e) {
            System.out.println("Config file not found: " + e.getMessage());
        }
    }
    
    /**
     * Get the browser name from config
     */
    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }
    
    /**
     * Get the application URL from config
     */
    public static String getUrl() {
        return properties.getProperty("url");
    }
    
    /**
     * Get explicit wait timeout
     */
    public static long getWaitTimeout() {
        return Long.parseLong(properties.getProperty("wait.timeout", "10"));
    }
    
    /**
     * Get implicit wait
     */
//    public static long getImplicitWait() {
//        return Long.parseLong(properties.getProperty("implicit.wait", "5"));
//    }
    
    /**
     * Get page load timeout
     */
    public static long getPageLoadTimeout() {
        return Long.parseLong(properties.getProperty("page.load.timeout"));
    }
}

