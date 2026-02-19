package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtil;

public class ProductDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ✅ FIRST PRODUCT VIEW BUTTON (missing in your file — THIS caused error)
    private final By firstViewProduct =
            By.cssSelector(".choose a");   // automationexercise first view product

    private final By addToCartButton = By.cssSelector("button.cart");

    private final By viewCartLinkInModal =
            By.xpath("//u[normalize-space()='View Cart']/parent::a");

    private final By quantityInput = By.id("quantity");

    public ProductDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openFirstProductDetails() {
        WaitUtil.safeClick(driver, wait, firstViewProduct);
    }

    public void addToCart() {
        WaitUtil.click(driver, wait, addToCartButton);
    }

    public void clickViewCartFromModal() {
        WaitUtil.click(driver, wait, viewCartLinkInModal);
    }

    // ✅ FIXED quantity method
    public void setQuantity(int qty) {

        WebElement el = wait.until(
                ExpectedConditions.visibilityOfElementLocated(quantityInput)
        );

        el.click();
        el.clear();

        // force clear
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(Keys.DELETE);

        el.sendKeys(String.valueOf(qty));
    }

    public boolean isQuantityVisible() {
        return WaitUtil.isVisible(wait, quantityInput);
    }
}
