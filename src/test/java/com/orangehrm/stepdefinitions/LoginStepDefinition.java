package com.orangehrm.stepdefinitions;

import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.LogUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class LoginStepDefinition {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    private static final Logger logger = LogUtils.getLogger(LoginStepDefinition.class);

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        logger.info("Navigating to the OrangeHRM login page");
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed");
        System.out.println("Login page is displayed");
    }

    @When("User enters username as {string}")
    public void userEntersUsername(String username) {
        logger.info("Entering username: {}", username);
        loginPage.enterUsername(username);
    }

    @When("User enters password as {string}")
    public void userEntersPassword(String password) {
        logger.debug("Entering password (masked)");
        loginPage.enterPassword(password);
    }

    @When("User clicks the login button")
    public void userClicksLoginButton() {
        logger.info("Clicking the login button");
        loginPage.clickLoginButton();
    }

    @Then("User should be navigated to the home page")
    public void userNavigatedToHomePage() {
        homePage = new HomePage(driver);
        assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed after login");
        logger.info("User successfully logged in - Dashboard is displayed");
    }

    @Then("User should see an error message {string}")
    public void userSeesErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        logger.info("Expected: '{}' | Actual: '{}'", expectedMessage, actualMessage);
        assertTrue(actualMessage.contains(expectedMessage),
                "Expected error message '" + expectedMessage + "' but found '" + actualMessage + "'");
    }
}
