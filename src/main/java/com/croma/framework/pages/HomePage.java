package com.croma.framework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import com.croma.framework.base.DriverFactory;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
    }

    @FindBy(css = "input[placeholder*='Search']")
    private WebElement searchInput;

    public void search(String text) {
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.clear();
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
    }
}
