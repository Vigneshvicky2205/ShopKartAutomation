package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class NegativeLoginTest extends BaseTest {

    @Test
    public void loginShouldFailWithWrongPassword() {

        HomePage home = new HomePage(driver, wait);
        home.clickSignupLogin();

        LoginPage login = new LoginPage(driver, wait);

        login.login(
                ConfigReader.get("email"),
                ConfigReader.get("wrongPassword")
        );

        Assert.assertTrue(
                login.isLoginErrorVisible(),
                "Expected an error message for invalid credentials."
        );

        Assert.assertEquals(
                login.getLoginErrorText().trim(),
                "Your email or password is incorrect!",
                "Error message text mismatch."
        );
    }

}
