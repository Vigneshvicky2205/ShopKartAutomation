package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

public class ProductDetailsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ✅ Add to cart button on product details
    private final By addToCartButton = By.cssSelector("button.btn.btn-default.cart");

    // ✅ Quantity input on product details
    private final By quantityInput = By.id("quantity");

    // ✅ Modal "View Cart" link
    private final By viewCartLinkInModal = By.xpath("//u[normalize-space()='View Cart']/parent::a");

    public ProductDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // =========================================================
    // ✅ IMPORTANT: KEEP OLD METHODS so other tests compile
    // =========================================================

    /** Old method used in your tests - keep it */
    public void openFirstProductDetails() {
        // CI-stable: open product details directly
        openProductDetailsById(1);
    }

    /** Old method used in your tests - keep it */
    public void setQuantity(int qty) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(String.valueOf(qty));
    }

    // =========================================================
    // ✅ New stable method (CI reliable)
    // =========================================================

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
