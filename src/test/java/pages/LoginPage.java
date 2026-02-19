package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtil;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginEmail = By.cssSelector("input[data-qa='login-email']");
    private final By loginPassword = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa='login-button']");

    // Server-side error (wrong credentials)
    private final By loginError = By.cssSelector("form[action='/login'] p");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void login(String email, String password) {
        WaitUtil.type(driver, wait, loginEmail, email);
        WaitUtil.type(driver, wait, loginPassword, password);

        // If ads iframe blocks clicks, your WaitUtil.safeClick should handle it.
        WaitUtil.click(driver, wait, loginButton);
    }

    public boolean isLoginErrorVisible() {
        return WaitUtil.isVisible(wait, loginError);
    }

    public String getLoginErrorText() {
        return WaitUtil.visible(wait, loginError).getText();
    }


}
