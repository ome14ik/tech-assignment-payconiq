package api_engine.model.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDates{

    @JsonProperty("checkin")
    public LocalDateTime checkin;
    @JsonProperty("checkout")
    public LocalDateTime checkout;

    public BookingDates(LocalDateTime checkin, LocalDateTime checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override public String toString() {
        return "BookingDates{" +
            "checkin=" + checkin +
            ", checkout=" + checkout +
            '}';
    }
}
