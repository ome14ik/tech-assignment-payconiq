package api_engine.model.responses.token;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {

    @JsonProperty("token")
    public String token;
    @JsonProperty("reason")
    public String reason;

    @Override public String toString() {
        return "Token{" +
            "token='" + token + '\'' +
            ", reason='" + reason + '\'' +
            '}';
    }
}
