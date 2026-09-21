@Login
Feature: OrangeHRM Login Functionality
  As a user
  I want to login to OrangeHRM application
  So that I can access the employee management features

  Background:
    Given User is on the login page

  @Smoke @Login_Valid
  Scenario: User should be able to login with valid credentials
    When User enters username as "Admin"
    And User enters password as "admin123"
    And User clicks the login button
    Then User should be navigated to the home page

  @Regression @Login_Invalid
  Scenario: User should not be able to login with invalid credentials
    When User enters username as "InvalidUser"
    And User enters password as "InvalidPassword"
    And User clicks the login button
    Then User should see an error message "Invalid credentials"

  @Smoke @Login_BlankUsername
  Scenario: User should see error when username is blank
    When User enters username as ""
    And User enters password as "admin"
    And User clicks the login button
    Then User should see an error message "Required"


