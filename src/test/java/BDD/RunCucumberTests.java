package BDD;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.platform.engine.Cucumber;

@CucumberOptions(
    features = "src/test/resources",
    glue = {"src.test.java.BDD"},
    tags = "@GetBooking" // Optional: Specify tags to include or exclude specific scenarios
)
@Cucumber
public class RunCucumberTests {
}
