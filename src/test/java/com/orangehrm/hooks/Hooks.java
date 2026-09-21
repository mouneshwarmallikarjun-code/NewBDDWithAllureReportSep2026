package com.orangehrm.hooks;

import com.orangehrm.utils.Config;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.ScreenshotUtils;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {
    
    /**
     * This method will execute before each scenario
     */
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("============ Starting Browser ============");
        DriverManager.initializeDriver();
        WebDriver driver = DriverManager.getDriver();
        
        // Navigate to application URL
        String url = Config.getUrl();
        driver.navigate().to(url);
        System.out.println("Navigated to URL: " + url);
        
        // Wait for page to load - wait for common elements to be present
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//input[@name='username']")
            ));
            System.out.println("Login page elements loaded successfully");
            Thread.sleep(1000); // Additional buffer time for page to fully load
        } catch (Exception e) {
            System.out.println("Warning: Page load verification failed: " + e.getMessage());
        }
        
        System.out.println("============ Browser Started & URL Navigated ============");
    }

    /**
     * This method will execute after each step
     */
    @AfterStep
    public void afterStep(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            byte[] screenshot = ScreenshotUtils.takeScreenshot(driver);
            // Name includes step status + scenario name so it's easy to identify in the report
            String label = (scenario.isFailed() ? "FAILED - " : "PASSED - ") + scenario.getName();
            scenario.attach(screenshot, "image/png", label);
        }
    }

    @After
    /**
     * This method will execute after each scenario
     */
    public void afterScenario() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            System.out.println("============ Browser Closed ============");
        }
    }
}

