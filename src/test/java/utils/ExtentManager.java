package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getExtent() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir")
                    + File.separator + "reports" + File.separator + "ExtentReport.html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("ShopKart Automation");
            spark.config().setDocumentTitle("Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
