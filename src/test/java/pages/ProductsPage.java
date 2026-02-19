package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtil;

import java.util.List;

public class ProductsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By productsLink = By.cssSelector("a[href='/products']");
    private final By searchBox = By.cssSelector("input#search_product");
    private final By searchButton = By.cssSelector("button#submit_search");
    private final By allProductsHeader = By.xpath("//h2[contains(.,'All Products')]");


    private final By searchedProductsHeader = By.xpath("//*[contains(text(),'Searched Products')]");
    private final By productCards = By.cssSelector(".features_items .product-image-wrapper");

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openProducts() {

        // 1) first try normal safe click
        WaitUtil.safeClick(driver, wait, productsLink);

        // 2) if click didn't navigate, force navigate (NO ADS can block this)
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/products"));
        } catch (Exception e) {
            driver.navigate().to("https://automationexercise.com/products");
        }

        // 3) confirm page loaded using search box (more reliable than header)
        WaitUtil.visible(wait, searchBox);
    }



    public void search(String keyword) {
        WaitUtil.type(driver, wait, searchBox, keyword);
        WaitUtil.click(driver, wait, searchButton);
    }

    public boolean isSearchedProductsHeaderVisible() {
        return WaitUtil.isVisible(wait, searchedProductsHeader);
    }

    public int getResultsCount() {
        List<?> items = driver.findElements(productCards);
        return items.size();
    }
}
