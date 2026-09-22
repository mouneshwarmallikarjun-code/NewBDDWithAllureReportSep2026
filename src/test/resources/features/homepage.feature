@HomePage
Feature: OrangeHRM HomePage Functionality

  Background:
    Given User is on the login page

  @Smoke @HomePage_Elements @Regression1
  Scenario: Validate the Home page functionality with valid credentials
    When User enters username as "Admin"
    And User enters password as "admin123"
    And User clicks the login button
    Then User should be navigated to the home page
    Then User should be navigated to the home page and validate elements


