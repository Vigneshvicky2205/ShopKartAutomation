package pages;

import org.openqa.selenium.By;
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
    private final By loginError = By.cssSelector("form[action='/login'] p");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void login(String email, String password) {

        WebElement emailEl = WaitUtil.visible(wait, loginEmail);
        emailEl.clear();
        emailEl.sendKeys(email);

        WebElement passEl = WaitUtil.visible(wait, loginPassword);
        passEl.clear();

        if (password != null && !password.isBlank()) {
            passEl.sendKeys(password);
        }

        WaitUtil.visible(wait, loginButton).click();
    }

    public boolean isLoginErrorVisible() {
        return WaitUtil.isVisible(wait, loginError);
    }

    public String getLoginErrorText() {
        return WaitUtil.visible(wait, loginError).getText();
    }

    public String getPasswordValidationMessage() {
        return driver.findElement(loginPassword).getAttribute("validationMessage");
    }
}
