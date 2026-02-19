package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.ProductDetailsPage;
import pages.CartPage;

public class CartQuantityUpdateTest extends BaseTest {

    @Test
    public void updateCartQuantity() {

        // open products
        ProductsPage products = new ProductsPage(driver, wait);
        products.openProducts();

        // open first product
        ProductDetailsPage details = new ProductDetailsPage(driver, wait);
        details.openFirstProductDetails();

        // change quantity to 2
        details.setQuantity(2);

        // add to cart
        details.addToCart();
        details.clickViewCartFromModal();

        CartPage cart = new CartPage(driver, wait);

        // verify quantity updated
        int qty = cart.getFirstRowQuantity();

        Assert.assertEquals(qty, 2, "Quantity not updated properly");
    }
}
