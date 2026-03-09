package com.croma.framework.reports;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter spark;

    public static void initReports() {
        spark = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static ExtentReports getExtent() {
        if (extent == null) initReports();
        return extent;
    }

    public static void flushReports() {
        if (extent != null) extent.flush();
    }
}
