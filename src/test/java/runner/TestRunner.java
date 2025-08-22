package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",   // Path to feature files
        glue = {"org.autoenterprise.plugin.stepDef", "org.autoenterprise.core.hooks"},
        tags = "@Login",
        plugin = {"html:target/cucumber-reports.html",
                "json:target/CucumberTestReport.json"
        },
        monochrome = true
)
public class TestRunner {
}
