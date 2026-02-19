package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtil;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Signup / Login link
    private final By signupLoginLink = By.cssSelector("a[href='/login']");

    // Logout link (login success check)
    private final By loggedInAs = By.cssSelector("a[href='/logout']");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickSignupLogin() {
        WaitUtil.click(driver, wait, signupLoginLink);
    }

    public boolean isLoggedInAsVisible() {
        return WaitUtil.isVisible(wait, loggedInAs);
    }

    public String getLoggedInAsText() {
        return WaitUtil.visible(wait, loggedInAs).getText();
    }
}
