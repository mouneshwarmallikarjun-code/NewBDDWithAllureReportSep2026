# Project Structure & Architecture

## Complete Directory Layout

```
CucumberBDDFramework17Sep2026/
│
├── src/
│   ├── main/
│   │   └── java/com/orangehrm/
│   │       ├── base/
│   │       │   └── BaseTest.java                    # Base class for all tests
│   │       ├── pages/
│   │       │   ├── LoginPage.java                   # Login page objects & methods
│   │       │   └── HomePage.java                    # Home/Dashboard page objects
│   │       └── utils/
│   │           ├── Config.java                      # Configuration reader
│   │           └── DriverManager.java               # WebDriver initialization & management
│   │
│   └── test/
│       ├── java/com/orangehrm/
│       │   ├── hooks/
│       │   │   └── Hooks.java                       # Setup & Teardown hooks
│       │   ├── runners/
│       │   │   └── TestRunner.java                  # Cucumber test runner
│       │   └── stepdefinitions/
│       │       └── LoginStepDefinition.java         # Step definition implementations
│       │
│       └── resources/
│           ├── features/
│           │   ├── login.feature                    # Login test scenarios
│           │   └── dashboard.feature                # Dashboard test scenarios
│           └── log4j2.xml                           # Logging configuration
│
├── pom.xml                                          # Maven configuration file
├── config.properties                                # Application configuration
├── testng.xml                                       # TestNG suite configuration
├── README.md                                        # Complete documentation
├── QUICKSTART.md                                    # Quick start guide
└── .gitignore                                       # Git ignore patterns
```

## Architecture Layers

### 1. Configuration Layer (Utilities)
- **Config.java**: Reads properties from config.properties
- **DriverManager.java**: Manages WebDriver instance lifecycle

### 2. Page Object Layer (Pages)
- **LoginPage.java**: Locators and methods for login functionality
- **HomePage.java**: Locators and methods for home page

### 3. Test Automation Layer (Step Definitions)
- **LoginStepDefinition.java**: BDD step implementations
- **Hooks.java**: Setup and teardown for each scenario

### 4. Execution Layer (Runners)
- **TestRunner.java**: Cucumber configuration and tag-based execution

### 5. Feature Layer (BDD)
- **.feature files**: Business readable test scenarios

## Design Patterns Used

### Page Object Model (POM)
- Separates page locators and methods from test logic
- Makes maintenance easier
- Improves code reusability

### Singleton Pattern (DriverManager)
- Ensures only one instance of WebDriver
- Manages driver lifecycle

### Configuration Pattern
- Externalized configuration in properties file
- Easy environment switching

### Hook Pattern
- Automatic setup and teardown
- Consistent test execution

## Key Components

### Configuration (config.properties)
```properties
browser=chrome
url=https://opensource-demo.orangehrm.com/web/index.php/auth/login
wait.timeout=10
implicit.wait=5
page.load.timeout=15
```

### POM Dependencies
- Selenium WebDriver 4.15.0
- Cucumber 7.14.0
- TestNG 7.8.1
- WebDriver Manager 5.6.3
- Log4j 2.21.1

## Test Execution Flow

```
1. Maven Test Command
   ↓
2. TestRunner.java (loads features & glue code)
   ↓
3. Hooks.java @Before (setup browser)
   ↓
4. Feature File (loads scenarios)
   ↓
5. Step Definition (calls page object methods)
   ↓
6. Page Objects (performs actions on UI)
   ↓
7. Hooks.java @After (teardown browser)
   ↓
8. Test Report Generated
```

## Separation of Concerns

```
                    TEST LAYER
                  (Test Runner)
                        ↓
           STEP DEFINITION LAYER
         (Implements BDD Steps)
                        ↓
               PAGE OBJECT LAYER
        (UI Locators & Actions)
                        ↓
              DRIVER MANAGER LAYER
          (WebDriver Management)
                        ↓
            CONFIGURATION LAYER
         (Properties & Config)
```

## Package Structure Explanation

### com.orangehrm.base
- Base classes for test infrastructure
- Common utilities for all tests

### com.orangehrm.pages
- Page Object classes
- Contains WebElement locators
- Contains page-specific methods

### com.orangehrm.utils
- Utility classes (Config, DriverManager)
- Helper methods
- Common functions

### com.orangehrm.hooks
- Cucumber hooks
- Setup and teardown logic
- Before and After scenario execution

### com.orangehrm.stepdefinitions
- Step definition implementations
- Maps feature file steps to code
- Uses page objects to perform actions

### com.orangehrm.runners
- Test runner class
- Cucumber configuration
- Tag-based execution

## Features Implemented

✅ **Modular Design**: Separate packages for different concerns
✅ **Reusability**: Page methods can be reused across steps
✅ **Maintainability**: Easy to update locators and methods
✅ **Scalability**: Simple to add new page objects and steps
✅ **Reporting**: HTML and JSON reports
✅ **Configuration**: Externalized configuration
✅ **Hooks**: Automatic setup and teardown
✅ **Tags**: Flexible test execution by tags
✅ **Logging**: Log4j logging configuration
✅ **Cross-browser**: Support for Chrome, Firefox, Edge

## How to Extend Framework

### Add New Page Object
1. Create new class in com.orangehrm.pages
2. Use @FindBy annotations for WebElements
3. Implement page-specific methods

### Add New Step Definition
1. Create new class in com.orangehrm.stepdefinitions
2. Implement @Given, @When, @Then methods
3. Call page object methods

### Add New Scenario
1. Add scenario to existing .feature file
2. Or create new .feature file with @Tag
3. Implement corresponding steps

### Add New Configuration
1. Add property to config.properties
2. Add getter method in Config.java
3. Use in relevant classes

## Best Practices Implemented

✅ DRY Principle - Reusable methods
✅ SOLID Principles - Single responsibility
✅ Explicit Waits - Better stability
✅ Proper Exception Handling
✅ Meaningful Naming - Clear class/method names
✅ Code Organization - Proper package structure
✅ Configuration Management - Externalized config
✅ Logging - Detailed logging
✅ Documentation - Clear comments
✅ Version Control - .gitignore included

## Testing Strategy

### Smoke Tests (@Smoke)
- Quick validation of critical functionality
- Login with valid credentials

### Regression Tests (@Regression)
- Comprehensive testing of features
- Login with invalid credentials
- Blank field validations

### Tag-Based Execution
- Execute specific test suites using tags
- Flexible test selection
- CI/CD integration ready

