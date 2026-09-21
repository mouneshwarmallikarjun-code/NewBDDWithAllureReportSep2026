package com.orangehrm.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.orangehrm.hooks", "com.orangehrm.stepdefinitions","stepdefinitions", "hooks"},
        tags = "@Login_Valid",
        plugin = {
                "pretty",
//                "html:target/cucumber-reports/cucumber.html",
//                "json:target/cucumber-reports/cucumber.json"
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

