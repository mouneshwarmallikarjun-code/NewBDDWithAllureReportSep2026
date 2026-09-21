# Quick Start Guide - OrangeHRM Cucumber BDD Framework

## Step 1: Prerequisites
- Java 11+ installed
- Maven installed
- Chrome browser installed (or Firefox/Edge)

## Step 2: Open Project
1. Open the project folder in your IDE (IntelliJ, Eclipse, VS Code)
2. Let Maven download all dependencies automatically

## Step 3: Configure Application
Edit `config.properties`:
```properties
browser=chrome
url=https://opensource-demo.orangehrm.com/web/index.php/auth/login
```

## Step 4: Run Tests

### Option 1: Run All Tests
```bash
mvn clean test
```

### Option 2: Run Only Smoke Tests
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

### Option 3: Run Only Valid Login Tests
```bash
mvn clean test -Dcucumber.filter.tags="@Login_Valid"
```

### Option 4: Run from IDE
1. Right-click on `TestRunner.java` in IDE
2. Click "Run" or "Run as TestNG Test"

## Step 5: View Reports
After test execution:
- HTML Report: `target/cucumber-reports/cucumber.html`
- JSON Report: `target/cucumber-reports/cucumber.json`
- Logs: `logs/automation.log`

## Test Credentials (OrangeHRM)
- **Username**: Admin
- **Password**: admin123

## Key Files Structure

```
Main Code (src/main/java/com/orangehrm/)
├── base/
│   └── BaseTest.java              # Base class for tests
├── pages/
│   ├── LoginPage.java             # Login page objects
│   └── HomePage.java              # Home page objects
└── utils/
    ├── Config.java                # Configuration reader
    └── DriverManager.java         # WebDriver manager

Test Code (src/test/java/com/orangehrm/)
├── hooks/
│   └── Hooks.java                 # Setup & Teardown
├── runners/
│   └── TestRunner.java            # Cucumber Test Runner
└── stepdefinitions/
    └── LoginStepDefinition.java   # Step implementations

Feature Files (src/test/resources/)
├── features/
│   └── login.feature              # BDD scenarios
└── log4j2.xml                     # Logging configuration
```

## Common Issues & Solutions

| Issue | Solution |
|-------|----------|
| Tests fail to start | Check if config.properties exists in project root |
| Element not found | Update XPath in page objects if UI changed |
| Tests timeout | Increase wait.timeout in config.properties |
| WebDriver error | Update WebDriver Manager or clear .m2 cache |
| Login fails | Verify credentials (Admin/admin123) |

## Running Specific Scenarios

Edit `TestRunner.java` to change tags:
```java
@CucumberOptions(
    tags = "@Smoke"  // Run only Smoke tests
)
```

## Adding New Test Cases

1. **Add feature file** in `src/test/resources/features/`
2. **Create step definitions** in `src/test/java/com/orangehrm/stepdefinitions/`
3. **Create page objects** in `src/main/java/com/orangehrm/pages/`
4. **Add tags** to feature file (@SmokeTag)
5. **Update TestRunner.java** to include new tags

## Framework Commands Cheat Sheet

```bash
# Clean and build
mvn clean install

# Run all tests
mvn test

# Run with specific tags
mvn test -Dcucumber.filter.tags="@Smoke"

# Run with multiple tags (AND)
mvn test -Dcucumber.filter.tags="@Smoke and @Login"

# Run with multiple tags (OR)
mvn test -Dcucumber.filter.tags="@Smoke or @Regression"

# Skip tests
mvn clean install -DskipTests

# Run with verbose output
mvn test -X
```

## Best Practices

✅ Always use Page Object Model
✅ Keep step definitions simple and reusable
✅ Use explicit waits instead of Thread.sleep()
✅ Maintain separate packages for pages and tests
✅ Use meaningful tag names
✅ Keep feature files readable and simple
✅ Update config.properties for different environments
✅ Check logs for debugging

## Next Steps

1. ✅ Run a test to verify setup
2. ✅ Explore feature files
3. ✅ Modify step definitions
4. ✅ Add more test scenarios
5. ✅ Integrate with CI/CD pipeline

## Support

Refer to README.md for complete documentation and troubleshooting guides.

Happy Testing! 🎉

