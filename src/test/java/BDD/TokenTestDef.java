package BDD;

import api_engine.EndPoints;
import api_engine.model.responses.token.Token;
import configs.Configuration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenTestDef {
    private JSONObject jsonParams = new JSONObject();
    private Response tokenResponce;

    @When ("^a token is requested, with username - (.*) and password - (.*)")
    public void a_token_is_requested(String userName, String password){
        jsonParams.put("username", userName);
        jsonParams.put("password", password);
        tokenResponce = EndPoints.getTokenResponse(jsonParams);


    }
    @Then ("^response status code should be (.*)")
    public void response_status_code_should_be(int statusCode){
        assertEquals(statusCode, tokenResponce.statusCode() );
    }

    @And ("^a token should be returned - (.*)")
    public void a_token_should_be_returned(boolean tokenValidation){
        boolean tokenExistence;
        Token token = tokenResponce.getBody().as(Token.class);
        tokenExistence = token.token != null;
        assertEquals(tokenValidation, tokenExistence);
    }
}
