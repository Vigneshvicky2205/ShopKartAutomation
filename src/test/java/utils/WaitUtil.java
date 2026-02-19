package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

    // ---------- basic helpers ----------
    public static WebElement visible(WebDriverWait wait, By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement presence(WebDriverWait wait, By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static boolean isVisible(WebDriverWait wait, By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void type(WebDriver driver, WebDriverWait wait, By locator, String text) {
        WebElement el = visible(wait, locator);
        el.clear();
        el.sendKeys(text);
    }

    public static void click(WebDriver driver, WebDriverWait wait, By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // ---------- office-style stable click ----------
    public static void safeClick(WebDriver driver, WebDriverWait wait, By locator) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            scrollIntoView(driver, el);
            el.click();
        } catch (ElementClickInterceptedException e) {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            scrollIntoView(driver, el);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        } catch (StaleElementReferenceException e) {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            scrollIntoView(driver, el);
            el.click();
        }
    }

    // ---------- hide overlay/iframe if present ----------
    public static void hideIfPresent(WebDriver driver, String cssSelector) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "var el=document.querySelector(arguments[0]); if(el){el.style.display='none'; el.style.visibility='hidden';}",
                    cssSelector
            );
        } catch (Exception ignored) {}
    }

    // ---------- utils ----------
    private static void scrollIntoView(WebDriver driver, WebElement el) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        } catch (Exception ignored) {}
    }

    // optional: small hard wait (avoid using too much)
    public static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    // if you need a quick short wait sometimes
    public static WebDriverWait shortWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
