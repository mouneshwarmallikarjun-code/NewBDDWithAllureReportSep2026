package com.orangehrm.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverManager {

    private static WebDriver driver;

    /**
     * Initialize WebDriver based on browser from config.
     * Runs in HEADED MODE (browser window is visible).
     *
     * Driver binaries are resolved automatically by Selenium Manager,
     * which ships with selenium-java 4.6+.
     */
    public static void initializeDriver() {
        String browser = Config.getBrowser().trim().toLowerCase();

        switch (browser) {
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                edgeOptions.addArguments("--disable-dev-shm-usage");
                edgeOptions.addArguments("--no-sandbox");
                //edgeOptions.addArguments("--headless=new");
                // HEADED mode: no "--headless" argument is added
                driver = new EdgeDriver(edgeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless=new");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");
               // chromeOptions.addArguments("--headless=new"); // Use "--headless=new" for Chrome 109+; remove for headed mode)
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        // Timeouts (Duration based API)
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Config.getPageLoadTimeout()));

        // Maximize window
        driver.manage().window().maximize();

        System.out.println("===============================================");
        System.out.println("WebDriver initialized: " + browser.toUpperCase() + " BROWSER");
       // System.out.println("Mode: HEADED - Browser window is VISIBLE");
        System.out.println("===============================================");
    }

    /**
     * Get WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    /**
     * Close the browser
     */
    public static void closeDriver() {
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("WebDriver closed successfully");
            } catch (Exception e) {
                System.out.println("Error closing driver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}
