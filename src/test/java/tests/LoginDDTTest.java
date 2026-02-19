package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.TestData;

public class LoginDDTTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = TestData.class)
    public void loginScenarios(String scenario, String email, String password, String expected, String errorType) {

        HomePage home = new HomePage(driver, wait);
        home.clickSignupLogin();

        LoginPage login = new LoginPage(driver, wait);
        login.login(email, password);

        if (expected.equalsIgnoreCase("PASS")) {
            Assert.assertTrue(home.isLoggedInAsVisible(),
                    "Expected PASS but not logged in. Scenario: " + scenario);
            return;
        }

        if (errorType.equalsIgnoreCase("BROWSER_VALIDATION")) {
            String msg = login.getPasswordValidationMessage();
            Assert.assertTrue(msg != null && !msg.isBlank(),
                    "Expected browser validation message for blank password. Scenario: " + scenario);
        } else if (errorType.equalsIgnoreCase("SERVER_ERROR")) {
            Assert.assertTrue(login.isLoginErrorVisible(),
                    "Expected server error but not visible. Scenario: " + scenario);
        } else {
            Assert.fail("Unknown errorType for scenario: " + scenario);
        }
    }
}
