package com.croma.framework.utils;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String folder = System.getProperty("user.dir") + "/screenshots/";
            new File(folder).mkdirs();
            String path = folder + name + "_" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(src, new File(path));
            return path;
        } catch (Exception e) {
            return null;
        }
    }
}
