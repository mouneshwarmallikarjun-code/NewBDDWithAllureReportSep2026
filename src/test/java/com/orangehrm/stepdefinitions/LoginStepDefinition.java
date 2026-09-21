package com.orangehrm.stepdefinitions;

import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class LoginStepDefinition {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed");
        System.out.println("Login page is displayed");
    }

    @When("User enters username as {string}")
    public void userEntersUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("User enters password as {string}")
    public void userEntersPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("User clicks the login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("User should be navigated to the home page")
    public void userNavigatedToHomePage() {
        homePage = new HomePage(driver);
        assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed after login");
        System.out.println("User successfully logged in - Dashboard is displayed");
    }

    @Then("User should see an error message {string}")
    public void userSeesErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        System.out.println("Expected: '" + expectedMessage + "' | Actual: '" + actualMessage + "'");
        assertTrue(actualMessage.contains(expectedMessage),
                "Expected error message '" + expectedMessage + "' but found '" + actualMessage + "'");
    }
}
