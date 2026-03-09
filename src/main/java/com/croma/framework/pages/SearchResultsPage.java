package com.croma.framework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
import java.util.stream.Collectors;
import com.croma.framework.base.DriverFactory;

public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SearchResultsPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
    }

    // Note: selectors are best-effort placeholders; inspect site to refine
    @FindBy(css = "ul.products li") 
    private List<WebElement> productCards;

    @FindBy(css = "div[data-filter='brand']")
    private WebElement brandFilterArea;

    public List<String> getVisibleProductBrands() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productCards));
        List<String> brands = new ArrayList<>();
        for (WebElement card : productCards) {
            try {
                String brand = card.findElement(By.cssSelector(".brand, .product-brand")).getText();
                brands.add(brand.trim());
            } catch (Exception e) {
                brands.add("");
            }
        }
        return brands;
    }

    public void applyBrands(String... brands) {
        for (String b : brands) {
            try {
                WebElement checkbox = driver.findElement(By.xpath("//label[contains(.,'" + b + "')]"));
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            } catch (Exception e) {
                // ignore if not found
            }
        }
        // wait a bit for results to refresh
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    public void sortBy(String optionVisibleText) {
        try {
            WebElement sortDropdown = driver.findElement(By.cssSelector("select.sorting, select#sort"));
            Select s = new Select(sortDropdown);
            s.selectByVisibleText(optionVisibleText);
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        } catch (Exception e) {
            // fallback: try clickable sort button
        }
    }

    public List<Double> getTopNPrices(int n) {
        wait.until(ExpectedConditions.visibilityOfAllElements(productCards));
        List<Double> prices = productCards.stream().limit(n).map(card -> {
            try {
                String priceText = card.findElement(By.cssSelector(".price, .product-price")).getText();
                return parsePrice(priceText);
            } catch (Exception e) {
                return 0.0;
            }
        }).collect(Collectors.toList());
        return prices;
    }

    private Double parsePrice(String txt) {
        String cleaned = txt.replaceAll("[^0-9.]", "");
        if(cleaned.isEmpty()) return 0.0;
        try {
            return Double.parseDouble(cleaned);
        } catch (Exception e) {
            return 0.0;
        }
    }

    public List<Integer> getDiscountPercentagesForTopN(int n) {
        wait.until(ExpectedConditions.visibilityOfAllElements(productCards));
        List<Integer> discounts = new ArrayList<>();
        for (int i = 0; i < Math.min(n, productCards.size()); i++) {
            WebElement card = productCards.get(i);
            try {
                String discText = card.findElement(By.cssSelector(".discount, .product-discount")).getText();
                String digits = discText.replaceAll("[^0-9]", "");
                discounts.add(Integer.parseInt(digits));
            } catch (Exception e) {
                discounts.add(0);
            }
        }
        return discounts;
    }
}
