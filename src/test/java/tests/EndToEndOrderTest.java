package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;


public class EndToEndOrderTest extends BaseTest {

    @Test
    public void fullOrderFlow() {
        HomePage home = new HomePage(driver, wait);
        home.clickSignupLogin();

        LoginPage login = new LoginPage(driver, wait);
        login.login(
                ConfigReader.get("email"),
                ConfigReader.get("password")
        );


        Assert.assertTrue(home.isLoggedInAsVisible(), "Login failed: Logged in as not visible");

        ProductsPage products = new ProductsPage(driver, wait);
        products.openProducts();

        ProductDetailsPage details = new ProductDetailsPage(driver, wait);
        details.openFirstProductDetails();
        details.addToCart();
        details.clickViewCartFromModal();

        CheckoutPage checkout = new CheckoutPage(driver, wait);
        checkout.proceedToCheckout();

        Assert.assertTrue(checkout.isCheckoutLoaded(), "Checkout page not loaded (Address/Review missing)");

        checkout.clickPlaceOrder();

        PaymentPage payment = new PaymentPage(driver, wait);
        payment.pay(
                ConfigReader.get("cardName"),
                ConfigReader.get("cardNumber"),
                ConfigReader.get("cvc"),
                ConfigReader.get("expMonth"),
                ConfigReader.get("expYear")
        );


        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/payment_done/"));

        OrderConfirmationPage confirmation = new OrderConfirmationPage(driver, wait);
        Assert.assertTrue(confirmation.isOrderPlacedVisible(), "Order confirmation not visible");
    }
}
