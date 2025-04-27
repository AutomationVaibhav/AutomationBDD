package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",   // Path to feature files
        glue = {"stepdefinitions", "Hooks"},         // Correct package names (should match folder names exactly)
        tags = "@Login",                             // Which tag to run
        plugin = {"pretty", "html:target/cucumber-reports.html"}, // Default + HTML report
        monochrome = true                            // Cleaner console output
)
public class TestRunner {
}
