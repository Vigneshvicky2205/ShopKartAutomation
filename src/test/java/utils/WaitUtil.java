package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class WaitUtil {

    public static WebElement visible(WebDriverWait wait, By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement clickable(WebDriverWait wait, By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean isVisible(WebDriverWait wait, By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void click(WebDriver driver, WebDriverWait wait, By locator) {
        try {
            visible(wait, locator).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {

            // hide ad iframes (AutomationExercise common)
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                        "document.querySelectorAll(\"iframe[id^='aswift'], iframe[title='Advertisement']\").forEach(f=>{f.style.display='none'; f.style.visibility='hidden';});"
                );
            } catch (Exception ignored) {}

            // JS click fallback
            WebElement el = visible(wait, locator);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    public static void type(WebDriver driver, WebDriverWait wait, By locator, String text) {
        WebElement el = visible(wait, locator);
        el.clear();
        el.sendKeys(text);
    }

    // ✅ NEW: safeClick - scroll + wait + retry + JS fallback (office stable)
    public static void safeClick(WebDriver driver, WebDriverWait wait, By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        // Scroll to center (reduces intercept)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});", el
        );

        // Wait clickable after scroll
        el = wait.until(ExpectedConditions.elementToBeClickable(locator));

        try {
            el.click();
        } catch (ElementClickInterceptedException e) {
            // Try Actions click
            try {
                new Actions(driver).moveToElement(el).pause(java.time.Duration.ofMillis(200)).click().perform();
            } catch (Exception ex) {
                // JS click fallback
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            }
        }
    }

    // ✅ NEW: hide overlay if present (specifically for your error)
    public static void hideIfPresent(WebDriver driver, String cssSelector) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "var el=document.querySelector(arguments[0]); if(el){el.style.display='none';}",
                    cssSelector
            );
        } catch (Exception ignored) {}
    }
}
