package api_engine.model.responses.token;

import java.time.LocalDateTime;

public class TokenAndTime extends Token{

    private static LocalDateTime tokenTimeStart;

    public static LocalDateTime getTokenTimeStart() {
        return tokenTimeStart;
    }

    public TokenAndTime(String token, LocalDateTime localDateTime ){
        this.token = token;
        this.tokenTimeStart = localDateTime;
    }



}
