package Hooks;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
import org.apache.logging.log4j.Logger;
import org.example.Manager.DriverFactory;
import org.example.Manager.ExtentReportManager;
import org.example.Utils.LoggerUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class Hooks {

    private static ExtentReports extent = ExtentReportManager.getExtentReports();
    private static ThreadLocal<ExtentTest> scenarioThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> stepNodeThreadLocal = new ThreadLocal<>();
    private static final Logger log = LoggerUtils.getLogger(Hooks.class);


    WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        driver = DriverFactory.getDriver();
        log.info("Browser setup completed");
        ExtentTest test = extent.createTest(scenario.getName());
        scenarioThreadLocal.set(test);
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        // Create a new step node before each step
        stepNodeThreadLocal.set(scenarioThreadLocal.get().createNode(scenario.getName()));
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            // Capture Screenshot
            String screenshotPath = captureScreenshot();

            // Attach to Extent Report
            stepNodeThreadLocal.get().fail("Step failed")
                    .addScreenCaptureFromPath(screenshotPath);
        } else {
            stepNodeThreadLocal.get().pass("Step passed");
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                log.error("Scenario" + scenario.getName() + " Failed");
                scenarioThreadLocal.get().fail("Scenario Failed");
            } else {
                scenarioThreadLocal.get().pass("Scenario Passed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
            extent.flush();
        }
    }

    private String captureScreenshot() {
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        String screenshotPath = "target/extent-reports/screenshots/" + UUID.randomUUID() + ".png";

        try (FileOutputStream fos = new FileOutputStream(new File(screenshotPath))) {
            fos.write(screenshotBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}
