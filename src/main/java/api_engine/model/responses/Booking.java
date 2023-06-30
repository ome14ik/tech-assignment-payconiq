package api_engine.model.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Booking {
    @JsonProperty("bookingid")
    public int bookingId;
}
