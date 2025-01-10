# For Automation Activity

---

## Project Structure

- `src/test/java/SeleniumDemo`: Contains the Java source code for test automation.

## Execution

1. Clone Git project
```bash
git clone https://github.com/johnreymanto/SeleniumTest.git
```
2. Run the script
```bash
mvn test -Dtest=<TestScript>
```
**Note:** You should be at the root directory to run this command.
Make sure to replace `<TestScript>` with the name of the automation script you want to run.

## Test Reports

- **Reports**: After test execution, Reports are generated and stored in the `allure-results` directory.
  To generate this report temporarily, run this code below.

```bash
allure serve ./allure-results
```
**Note:** You should be at the allure-results directory to run this command.

## Dependencies

Ensure you have the following dependencies installed:

- Java 22 or latest
- Maven
- NodeJs
- Chrome Web Driver

**Note:** Update Chrome webdriver path in test script.
```declarative
/Users/*/*/chromedriver-mac-x64/chromedriver
```

---