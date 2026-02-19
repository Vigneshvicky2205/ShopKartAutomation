package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;


public class LoginTest extends BaseTest {

    @Test
    public void loginShouldShowLoggedInAs() {
        HomePage home = new HomePage(driver, wait);
        home.clickSignupLogin();

        LoginPage login = new LoginPage(driver, wait);

        login.login(
                ConfigReader.get("email"),
                ConfigReader.get("password")
        );


        // Validate "Logged in as"
        Assert.assertTrue(home.isLoggedInAsVisible(), "Logged in as text not visible after login");
        System.out.println("Logged In Text: " + home.getLoggedInAsText());

    }

}
