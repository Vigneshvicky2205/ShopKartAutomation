package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtil;

public class PaymentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameOnCard = By.cssSelector("input[name='name_on_card']");
    private final By cardNumber = By.cssSelector("input[name='card_number']");
    private final By cvc = By.cssSelector("input[name='cvc']");
    private final By expiryMonth = By.cssSelector("input[name='expiry_month']");
    private final By expiryYear = By.cssSelector("input[name='expiry_year']");

    // Pay/Confirm button
    private final By payAndConfirm = By.cssSelector("button#submit");

    public PaymentPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void pay(String name, String number, String cvcVal, String mm, String yyyy) {
        WaitUtil.type(driver, wait, nameOnCard, name);
        WaitUtil.type(driver, wait, cardNumber, number);
        WaitUtil.type(driver, wait, cvc, cvcVal);
        WaitUtil.type(driver, wait, expiryMonth, mm);
        WaitUtil.type(driver, wait, expiryYear, yyyy);

        // ✅ Your error overlay: <div class="grippy-host"></div>
        WaitUtil.hideIfPresent(driver, ".grippy-host");

        // ✅ Robust click
        WaitUtil.safeClick(driver, wait, payAndConfirm);
    }
}
