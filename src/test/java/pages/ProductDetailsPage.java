package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

public class ProductDetailsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By addToCartButton = By.cssSelector("button.btn.btn-default.cart");
    private final By viewCartLinkInModal = By.xpath("//u[normalize-space()='View Cart']/parent::a");

    public ProductDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ✅ open product directly (CI stable)
    public void openProductDetailsById(int productId) {
        String baseUrl = System.getProperty("baseUrl", ConfigReader.get("baseUrl"));
        driver.get(baseUrl + "/product_details/" + productId);
    }

    public void addToCart() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        btn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public void clickViewCartFromModal() {
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(viewCartLinkInModal));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        link = wait.until(ExpectedConditions.elementToBeClickable(viewCartLinkInModal));

        try {
            link.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }
    }
}
