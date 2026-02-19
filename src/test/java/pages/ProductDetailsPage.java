package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtil;

public class ProductDetailsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ✅ stable: open first product details page
    private final By firstViewProduct = By.xpath("(//a[contains(@href,'/product_details/')])[1]");

    // ✅ add to cart button on product details page
    private final By addToCartButton = By.cssSelector("button.cart");

    // ✅ modal "View Cart" link
    private final By viewCartLinkInModal = By.xpath("//u[normalize-space()='View Cart']/parent::a");

    // ✅ quantity input
    private final By quantityInput = By.id("quantity");

    public ProductDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openFirstProductDetails() {
        WaitUtil.click(driver, wait, firstViewProduct);
    }

    public void setQuantity(int qty) {
        WaitUtil.type(driver, wait, quantityInput, String.valueOf(qty));
    }

    public void addToCart() {
        // ✅ CI/headless safe: scroll into view + clickable wait
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});",
                wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton)));

        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public void clickViewCartFromModal() {
        WaitUtil.click(driver, wait, viewCartLinkInModal);
    }
}
