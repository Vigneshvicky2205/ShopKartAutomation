package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPage;

public class SearchTest extends BaseTest {

    @Test
    public void searchShouldShowResults() {
        ProductsPage products = new ProductsPage(driver, wait);
        products.openProducts();
        products.search("Dress");

        Assert.assertTrue(products.isSearchedProductsHeaderVisible(), "Searched Products header not visible");
        Assert.assertTrue(products.getResultsCount() > 0, "No products found for search keyword");
    }
}
