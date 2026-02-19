package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductDetailsPage;
import pages.ProductsPage;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartShouldShowItemInCart() {
        ProductsPage products = new ProductsPage(driver, wait);
        products.openProducts();

        ProductDetailsPage details = new ProductDetailsPage(driver, wait);
        details.openFirstProductDetails();
        details.addToCart();
        details.clickViewCartFromModal();

        CartPage cart = new CartPage(driver, wait);
        Assert.assertTrue(cart.isCartTableVisible(), "Cart table not visible");
    }
}
