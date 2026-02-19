package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtil;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // --- Locators ---
    private final By cartRows = By.cssSelector("#cart_info_table tbody tr");

    // Quantity (AutomationExercise cart shows quantity inside a button in cart_quantity column)
    private final By firstRowQuantity =
            By.cssSelector("#cart_info_table tbody tr:first-child td.cart_quantity button");

    // Total price text in first row
    private final By firstRowTotal =
            By.cssSelector("#cart_info_table tbody tr:first-child p.cart_total_price");

    // Remove/delete icon in first row
    private final By firstRowRemove =
            By.cssSelector("#cart_info_table tbody tr:first-child a.cart_quantity_delete");

    // Empty cart text (sometimes site shows this)
    private final By emptyCartText =
            By.xpath("//*[contains(.,'Cart is empty')]");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ----- COMMON HELPERS -----
    public int getRowCount() {
        return driver.findElements(cartRows).size();
    }

    // For your existing AddToCartTest
    public boolean isCartTableVisible() {
        return getRowCount() > 0;
    }

    public boolean hasItems() {
        return getRowCount() > 0;
    }

    // ----- QUANTITY / TOTAL METHODS (for CartQuantityUpdateTest) -----
    public int getFirstRowQuantity() {
        String qty = wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowQuantity))
                .getText().trim();
        return Integer.parseInt(qty);
    }

    public String getFirstRowTotalText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowTotal))
                .getText().trim();
    }

    // ----- REMOVE METHODS (for RemoveItemTest) -----
    public void removeFirstRow() {
        int before = getRowCount();
        if (before == 0) return;

        WaitUtil.safeClick(driver, wait, firstRowRemove);

        // wait until row count decreases
        wait.until(d -> getRowCount() < before);
    }

    public void waitUntilCartEmpty() {
        wait.until(d -> getRowCount() == 0);
    }

    public boolean isCartEmpty() {
        return getRowCount() == 0 || WaitUtil.isVisible(wait, emptyCartText);
    }
}
