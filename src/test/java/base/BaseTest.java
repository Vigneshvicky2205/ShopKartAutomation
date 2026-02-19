package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // Office standard: driver access for listeners + parallel safe
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /** Listener uses this to get current test's driver */
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        // 1) Setup driver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        tlDriver.set(driver);

        // 2) Waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 3) Open base URL from config
        String baseUrl = ConfigReader.get("baseUrl");
        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            // important to avoid memory leaks in threadlocal
            tlDriver.remove();
        }
    }
}
