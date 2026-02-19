package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtil;

public class OrderConfirmationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By orderPlacedHeader =
            By.xpath("//*[contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'ORDER PLACED')]");

    public OrderConfirmationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isOrderPlacedVisible() {
        try {
            WaitUtil.visible(wait, orderPlacedHeader);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
