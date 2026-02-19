package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtil;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By proceedToCheckout = By.cssSelector("a.btn.btn-default.check_out");
    private final By addressDetailsHeader = By.xpath("//*[contains(text(),'Address Details')]");
    private final By reviewOrderHeader = By.xpath("//*[contains(text(),'Review Your Order')]");
    private final By placeOrderButton = By.xpath("//a[contains(.,'Place Order')]");

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void proceedToCheckout() {
        WaitUtil.click(driver, wait, proceedToCheckout);
    }

    public boolean isCheckoutLoaded() {
        return WaitUtil.isVisible(wait, addressDetailsHeader) && WaitUtil.isVisible(wait, reviewOrderHeader);
    }

    public void clickPlaceOrder() {
        WaitUtil.click(driver, wait, placeOrderButton);
    }
}
