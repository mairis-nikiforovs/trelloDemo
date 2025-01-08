package apiOctober.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

// Option+Enter = import
@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty",
        "json:target/cucumber-reports/CucumberTestReport.json",
        "junit:target/cucumber-reports/report.xml"},
    features = {"src/test/resources/features"},
    glue = {"apiOctober/stepsdefinitions"}
)
public class TestRunner {

}
