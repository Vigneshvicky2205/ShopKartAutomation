package utils;

import base.BaseTest;
import com.aventstack.extentreports.*;
import org.testng.*;

public class ExtentTestNGListener implements ITestListener {

    private static final ExtentReports extent = ExtentManager.getExtent();
    private static final ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        tlTest.set(test);
        test.log(Status.INFO, "Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        tlTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = tlTest.get();
        test.log(Status.FAIL, result.getThrowable());

        String path = ScreenshotUtil.takeScreenshot(BaseTest.getDriver(), result.getMethod().getMethodName());
        if (path != null) {
            try {
                test.addScreenCaptureFromPath(path, "Failure Screenshot");
            } catch (Exception e) {
                test.log(Status.WARNING, "Screenshot attach failed: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        tlTest.get().log(Status.SKIP, "Test Skipped");
        if (result.getThrowable() != null) {
            tlTest.get().log(Status.SKIP, result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
