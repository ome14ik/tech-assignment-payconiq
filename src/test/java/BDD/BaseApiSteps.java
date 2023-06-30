package BDD;

import api_engine.EndPoints;
import configs.ConfigReader;
import configs.Configuration;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseApiSteps {

@Before
    public void setup() throws Exception {
    ConfigReader.readConfiguration();

    /**
     * Verify API is up snd running
     */
    Response pingResponse = EndPoints.pingHealthCheck();
    assertEquals(pingResponse.statusCode(), 201);

    EndPoints.getToken();

}

}
