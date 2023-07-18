package BDD;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "BDD")

@SelectClasspathResource("io/qameta/allure")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,
    value = "pretty, summary, json:target/reports/cucumber-reports/cucumber.json,"
        + " io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")


public class CucumberRunner {
}
