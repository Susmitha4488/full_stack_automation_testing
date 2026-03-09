package com.croma.tests;

import com.croma.framework.base.TestBase;
import com.croma.framework.pages.*;
import com.croma.framework.reports.ExtentManager;
import com.croma.framework.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.*;
import java.util.*;
import static org.testng.Assert.*;

public class DiscountSortAndAverageTest extends TestBase {
    @Test
    public void sortByDiscount_andComputeAverageTop10() {
        ExtentTest test = ExtentManager.getExtent().createTest("Sort by Discount and Average Price Test");

        HomePage home = new HomePage();
        home.search("Refrigerator");
        SearchResultsPage results = new SearchResultsPage();

        results.sortBy("Discount: High to Low");
        test.info("Applied sort by discount");

        List<Integer> discounts = results.getDiscountPercentagesForTopN(10);
        test.info("Top discounts: " + discounts);
        for (int i = 0; i < discounts.size() - 1; i++) {
            assertTrue(discounts.get(i) >= discounts.get(i + 1),
                       "Discounts not in descending order at index " + i);
        }

        List<Double> topPrices = results.getTopNPrices(10);
        test.info("Top prices: " + topPrices);
        double sum = 0.0;
        for (Double p : topPrices) sum += p;
        double average = topPrices.isEmpty() ? 0.0 : sum / topPrices.size();
        test.info("Average price of top " + topPrices.size() + " = " + average);
        System.out.println("Average price: " + average);

        String screenshot = ScreenshotUtil.takeScreenshot(driver, "discount_sorted");
        if (screenshot != null) test.addScreenCaptureFromPath(screenshot);
    }
}
