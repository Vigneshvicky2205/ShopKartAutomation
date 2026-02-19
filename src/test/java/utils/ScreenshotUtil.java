package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            String folder = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "screenshots";
            Files.createDirectories(Path.of(folder));

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destPath = folder + File.separator + testName + "_" + System.currentTimeMillis() + ".png";
            Files.copy(src.toPath(), Path.of(destPath));

            return destPath;
        } catch (Exception e) {
            return null;
        }
    }
}
