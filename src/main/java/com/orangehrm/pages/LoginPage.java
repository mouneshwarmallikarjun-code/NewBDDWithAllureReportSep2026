package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for the OrangeHRM login page
    private final By usernameField = By.name("username");
//    @FindBy(name = "username")
//    WebElement usernameField;

    private final By passwordField = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");

    // Banner shown for wrong credentials e.g. "Invalid credentials"
    private final By alertMessage = By.cssSelector(".oxd-alert-content-text");

    // Inline field validation e.g. "Required"
    private final By fieldErrorMessage = By.cssSelector(".oxd-input-field-error-message");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Enter username
     */
    public void enterUsername(String username) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        field.clear();
        field.sendKeys(username);
        System.out.println("Username entered: " + (username.isEmpty() ? "(empty)" : username));
        //WebElement ele=usernameField.findElement(driver);
        //ele.sendKeys(username);
    }

    /**
     * Enter password
     */
    public void enterPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        field.clear();
        field.sendKeys(password);
        System.out.println("Password entered");
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        System.out.println("Login button clicked");
    }

    /**
     * Check if login page is displayed
     */
    public boolean isLoginPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
            return true;
        } catch (Exception e) {
            System.out.println("Login page not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Returns the error text shown after an invalid login attempt.
     * Handles both the credential alert banner and inline "Required" messages.
     */
    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage)).getText().trim();
        } catch (Exception ignored) {
            // fall through to inline field errors
        }

        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(fieldErrorMessage)).getText().trim();
        } catch (Exception e) {
            System.out.println("No error message found: " + e.getMessage());
            return "";
        }
    }

    /**
     * Perform login action
     */
    public void performLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
