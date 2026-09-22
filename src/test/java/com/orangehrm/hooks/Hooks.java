package com.orangehrm.hooks;

import com.orangehrm.utils.AllureLogCaptor;
import com.orangehrm.utils.Config;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.ScreenshotUtils;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    /**
     * This method will execute before each scenario
     */
    @Before
    public void setUp(Scenario scenario) {
        logger.info("============ Starting Browser ============");
        DriverManager.initializeDriver();
        WebDriver driver = DriverManager.getDriver();
        
        // Navigate to application URL
        String url = Config.getUrl();
        driver.navigate().to(url);
        logger.info("Navigated to URL: {}", url);
        
        // Wait for page to load - wait for common elements to be present
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//input[@name='username']")
            ));
            logger.info("Login page elements loaded successfully");
            Thread.sleep(1000); // Additional buffer time for page to fully load
        } catch (Exception e) {
            logger.warn("Warning: Page load verification failed: {}", e.getMessage());
        }
        
        logger.info("============ Browser Started & URL Navigated ============");
    }

    /**
     * This method will execute after each step
     */
    @AfterStep
    public void afterStep(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        // Logs generated during THIS step only, since the buffer is cleared each time
        String stepLogs = AllureLogCaptor.getLogsAndClear();
        if (!stepLogs.isBlank()) {
            Allure.addAttachment("Step Logs", "text/plain",
                    new ByteArrayInputStream(stepLogs.getBytes()), ".txt");
        }

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
    public void afterScenario(Scenario scenario) {
        String logs = AllureLogCaptor.getLogsAndClear();
        Allure.addAttachment(scenario.getName() + " - Logs", "text/plain",
                new ByteArrayInputStream(logs.getBytes()), ".txt");
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            logger.info("============ Browser Closed ============");
        }
    }
}

