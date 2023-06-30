package BDD;

import configs.Configuration;
import io.cucumber.java.en.Given;

public class CommonStepDef {
    @Given("^the base URL - (.*) - is set")
    public void setBaseUri(String url){
        Configuration.url = url;
    }
}
