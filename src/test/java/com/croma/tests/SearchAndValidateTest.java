package com.croma.tests;

import com.croma.framework.base.TestBase;
import com.croma.framework.pages.*;
import com.croma.framework.reports.ExtentManager;
import com.aventstack.extentreports.*;
import com.croma.framework.utils.ScreenshotUtil;
import org.testng.annotations.*;
import java.util.List;
import static org.testng.Assert.*;

public class SearchAndValidateTest extends TestBase {
    private HomePage home;
    private SearchResultsPage results;
    private ExtentReports extent = ExtentManager.getExtent();

    @Test
    public void searchRefrigerator_shouldReturnRelevantResults() {
        ExtentTest test = extent.createTest("Search Refrigerator Test");
        home = new HomePage();
        home.search("Refrigerator");
        test.info("Searched Refrigerator");
        results = new SearchResultsPage();

        List<String> brands = results.getVisibleProductBrands();
        test.info("Found brands: " + brands.toString());
        assertTrue(brands.size() > 0, "No search results displayed");
        boolean anyRelevant = brands.stream().anyMatch(b -> b != null && !b.isEmpty());
        assertTrue(anyRelevant, "No relevant refrigerators found");

        String screenshot = ScreenshotUtil.takeScreenshot(driver, "search_refrigerator");
        if (screenshot != null) test.addScreenCaptureFromPath(screenshot);
    }
}
