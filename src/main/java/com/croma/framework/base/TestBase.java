package com.croma.framework.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import com.croma.framework.reports.ExtentManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestBase {
    protected WebDriver driver;
    protected Logger log = LogManager.getLogger(TestBase.class);

    @BeforeSuite
    public void beforeSuite() {
        ExtentManager.initReports();
        log.info("Before Suite - Reports initialized");
    }

    @Parameters({ "browser" })
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.initDriver(browser);
        log.info("Browser initialized: " + browser);
        driver.get("https://www.croma.com/");
        log.info("Navigated to croma homepage");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
        log.info("Driver quit");
    }

    @AfterSuite
    public void afterSuite() {
        ExtentManager.flushReports();
        log.info("Reports flushed");
    }
}
