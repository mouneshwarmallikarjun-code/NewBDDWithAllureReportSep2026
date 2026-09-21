# OrangeHRM Cucumber BDD Framework

A comprehensive Cucumber BDD framework built with Selenium WebDriver and Java for automating tests on the OrangeHRM application. This framework follows industry standards and best practices.

## Project Structure

```
CucumberBDDFramework/
├── src/
│   ├── main/java/
│   │   └── com/orangehrm/
│   │       ├── pages/           # Page Object Classes
│   │       │   ├── LoginPage.java
│   │       │   └── HomePage.java
│   │       └── utils/           # Utility Classes
│   │           ├── Config.java
│   │           └── DriverManager.java
│   └── test/
│       ├── java/
│       │   └── com/orangehrm/
│       │       ├── hooks/           # Cucumber Hooks for setup/teardown
│       │       │   └── Hooks.java
│       │       ├── runners/         # Test Runner Classes
│       │       │   └── TestRunner.java
│       │       └── stepdefinitions/ # Step Definition Classes
│       │           └── LoginStepDefinition.java
│       └── resources/
│           └── features/        # Feature Files
│               └── login.feature
├── pom.xml                      # Maven Configuration
├── config.properties            # Application Configuration
├── testng.xml                   # TestNG Suite Configuration
└── README.md                    # Documentation
```

## Technologies Used

- **Selenium WebDriver**: 4.15.0
- **Cucumber**: 7.14.0
- **TestNG**: 7.8.1
- **Java**: 11
- **Maven**: Build Tool
- **WebDriver Manager**: For automatic driver management

## Key Features

✅ **Page Object Model (POM)**: Separate packages for pages and test classes
✅ **Configuration Management**: Externalized config properties for browser and URL
✅ **Hooks**: Automatic setup and teardown with Cucumber hooks
✅ **TestNG Integration**: Using TestNG instead of JUnit
✅ **Tag-Based Execution**: Run tests by tags (e.g., @Smoke, @Regression)
✅ **Web Driver Management**: Automatic driver initialization
✅ **Explicit Waits**: Using WebDriverWait for better stability
✅ **HTML Reports**: Cucumber HTML and JSON reports

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6.0 or higher
- Chrome/Firefox/Edge browser installed

## Installation & Setup

1. **Clone or Download the project**
   ```
   cd CucumberBDDFramework
   ```

2. **Install Maven Dependencies**
   ```
   mvn clean install
   ```

3. **Configure Browser & URL** (Edit `config.properties`)
   ```
   browser=chrome
   url=https://opensource-demo.orangehrm.com/web/index.php/auth/login
   ```

## Configuration File (config.properties)

```properties
# Browser Configuration
browser=chrome  # Options: chrome, firefox, edge

# Application URL
url=https://opensource-demo.orangehrm.com/web/index.php/auth/login

# WebDriver Wait Timeout (in seconds)
wait.timeout=10

# Implicit Wait (in seconds)
implicit.wait=5

# Page Load Timeout (in seconds)
page.load.timeout=15
```

## Running Tests

### Run all tests with @Login tag
```
mvn test
```

### Run tests with specific tag
```
mvn test -Dcucumber.filter.tags="@Smoke"
```

### Run tests with multiple tags
```
mvn test -Dcucumber.filter.tags="@Smoke and @Login"
```

### Run with specific browser
Edit `config.properties` and change browser value:
```
browser=firefox
```

## Feature File Tags

- `@Login`: Login feature tests
- `@Smoke`: Smoke test scenarios
- `@Regression`: Regression test scenarios
- `@Login_Valid`: Valid login tests
- `@Login_Invalid`: Invalid login tests
- `@Login_BlankUsername`: Blank username tests
- `@Login_BlankPassword`: Blank password tests

## Page Object Classes

### LoginPage
- `enterUsername(String username)`: Enter username
- `enterPassword(String password)`: Enter password
- `clickLoginButton()`: Click login button
- `isLoginPageDisplayed()`: Verify login page is displayed
- `isInvalidLoginMessageDisplayed()`: Check for error message
- `performLogin(String username, String password)`: Complete login action

### HomePage
- `isHomePageDisplayed()`: Verify home page is displayed
- `getDashboardHeadingText()`: Get dashboard heading text

## Utility Classes

### Config.java
Reads configuration from `config.properties` file:
- `getBrowser()`: Get browser type
- `getUrl()`: Get application URL
- `getWaitTimeout()`: Get explicit wait timeout
- `getImplicitWait()`: Get implicit wait
- `getPageLoadTimeout()`: Get page load timeout

### DriverManager.java
Manages WebDriver lifecycle:
- `initializeDriver()`: Initialize WebDriver
- `getDriver()`: Get WebDriver instance
- `closeDriver()`: Close WebDriver

## Hooks

The `Hooks.java` class handles:
- **@Before**: Initializes browser, navigates to URL
- **@After**: Closes browser after each scenario

## Test Reports

After test execution, reports are generated in:
- HTML Report: `target/cucumber-reports/cucumber.html`
- JSON Report: `target/cucumber-reports/cucumber.json`

## Sample Test Scenarios

### 1. Valid Login
```
Scenario: User should be able to login with valid credentials
  When User enters username as "Admin"
  And User enters password as "admin123"
  And User clicks the login button
  Then User should be navigated to the home page
```

### 2. Invalid Login
```
Scenario: User should not be able to login with invalid credentials
  When User enters username as "InvalidUser"
  And User enters password as "InvalidPassword"
  And User clicks the login button
  Then User should see an error message "Invalid credentials"
```

## Best Practices Implemented

✅ Page Object Model - Separation of pages and tests
✅ DRY Principle - Reusable methods
✅ Explicit Waits - Better element handling
✅ Configuration Management - Externalized properties
✅ Logging - Console output for debugging
✅ Proper Exception Handling
✅ WebDriver Management - Proper initialization and cleanup
✅ BDD Syntax - Readable feature files

## Extending the Framework

### Adding New Page Object
1. Create a new class in `src/main/java/com/orangehrm/pages/`
2. Use `@FindBy` annotations for WebElements
3. Implement page methods

### Adding New Step Definition
1. Create a new class in `src/test/java/com/orangehrm/stepdefinitions/`
2. Use `@Given`, `@When`, `@Then` annotations
3. Implement step logic

### Adding New Feature
1. Create new `.feature` file in `src/test/resources/features/`
2. Write BDD scenarios
3. Add tags for execution

## Troubleshooting

**Issue**: WebDriver not found
- **Solution**: Check if browser is installed, update WebDriver Manager version

**Issue**: Elements not found
- **Solution**: Update XPath/locators in page objects based on current UI

**Issue**: Tests timing out
- **Solution**: Increase wait timeout in `config.properties`

## Support & Documentation

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [TestNG Documentation](https://testng.org/doc/)
- [OrangeHRM Application](https://opensource-demo.orangehrm.com/)

## License

This framework is for educational and training purposes.

---

**Author**: Automation Team
**Date**: September 17, 2026
**Version**: 1.0

