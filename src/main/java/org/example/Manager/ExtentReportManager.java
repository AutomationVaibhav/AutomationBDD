package org.example.Manager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("target/extent-reports/extent-report.html");
            reporter.config().setReportName("Automation Regression Report");
            reporter.config().setDocumentTitle("Automation BDD Framework");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
//            extent.setSystemInfo("Tester", "Vaibhav Bhatt");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

}
