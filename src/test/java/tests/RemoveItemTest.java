package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.ProductDetailsPage;
import pages.CartPage;

public class RemoveItemTest extends BaseTest {

    @Test
    public void removeItemFromCart() {

        ProductsPage products = new ProductsPage(driver, wait);
        products.openProducts();

        ProductDetailsPage details = new ProductDetailsPage(driver, wait);
        details.openFirstProductDetails();

        details.addToCart();
        details.clickViewCartFromModal();

        CartPage cart = new CartPage(driver, wait);

        // remove item
        cart.removeFirstRow();

        cart.waitUntilCartEmpty();

        // verify cart empty
        Assert.assertTrue(cart.isCartEmpty(),
                "Cart still has items");
    }
}
