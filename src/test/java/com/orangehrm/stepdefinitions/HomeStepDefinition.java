package com.orangehrm.stepdefinitions;

import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.LogUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeStepDefinition {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    private static final Logger logger = LogUtils.getLogger(HomeStepDefinition.class);

    @Then("User should be navigated to the home page and validate elements")
    public void userNavigatedToHomePage() throws InterruptedException {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        logger.info("User successfully logged in - Dashboard is displayed");
        assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed after login");
        //Thread.sleep(5000); // Wait for elements to load
        logger.info("User successfully logged in - Dashboard is displayed");
        String dashboardText = homePage.getDashboardHeadingText();
        assertEquals(dashboardText, "Dashboard","Home page is not displayed after login");
        assertTrue(homePage.isUpgradeButtonDisplayed(),"Upgrade button is not displayed on the home page");
        logger.info("Upgrade button is displayed");
        logger.info("User successfully logged in - Dashboard elements are displayed");
    }

}
