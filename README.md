# CromaAutomationFramework

Java 17 + Selenium + TestNG automation framework for testing Croma (https://www.croma.com)

Structure:
- src/main/java: framework code (base, pages, utils, reports)
- src/test/java: test classes
- src/test/resources: testdata and log4j2 config
- pom.xml, testng.xml

How to run:
1. Import project in IntelliJ/Eclipse as Maven project.
2. Run `mvn test` or run TestNG suite via IDE.

Note:
- This project uses WebDriverManager to auto-download drivers.
- By default the `browser` parameter in `testng.xml` is `chrome`. You can set it to `firefox` or `edge`.
