package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for the OrangeHRM dashboard
    private final By userDropdown = By.cssSelector(".oxd-userdropdown");
    private final By topBar = By.cssSelector(".oxd-topbar");
    private final By breadcrumbHeading = By.cssSelector(".oxd-topbar-header-breadcrumb h6");

    @FindBy(css = ".oxd-userdropdown")
    private WebElement userDropdownElement;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Check if home page (dashboard) is displayed after a successful login.
     */
    public boolean isHomePageDisplayed() {
        try {
            // 1. URL must move away from the login page
            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/auth/login")));

            // 2. Dashboard chrome must be present
            wait.until(ExpectedConditions.visibilityOfElementLocated(topBar));
            wait.until(ExpectedConditions.visibilityOfElementLocated(userDropdown));

            System.out.println("Home page displayed. URL: " + driver.getCurrentUrl());
            return true;
        } catch (Exception e) {
            System.out.println("Home page NOT displayed. Current URL: " + driver.getCurrentUrl());
            return false;
        }
    }

    /**
     * Get dashboard heading text.
     */
    public String getDashboardHeadingText() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(breadcrumbHeading)).getText();
        } catch (Exception e) {
            System.out.println("Error getting dashboard heading text: " + e.getMessage());
            return "";
        }
    }
}
