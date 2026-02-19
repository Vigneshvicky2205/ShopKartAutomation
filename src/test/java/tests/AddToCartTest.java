package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductDetailsPage;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartShouldShowItemInCart() {

        ProductDetailsPage pdp = new ProductDetailsPage(driver, wait);

        // ✅ CI-stable: open product details directly (avoids flaky listing click)
        pdp.openProductDetailsById(1);

        pdp.addToCart();
        pdp.clickViewCartFromModal();

        CartPage cart = new CartPage(driver, wait);

        // ✅ Your CartPage supports this
        Assert.assertTrue(cart.hasItems(), "Cart has no items after Add To Cart");
        Assert.assertTrue(cart.isCartTableVisible(), "Cart table not visible");
    }
}
