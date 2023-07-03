package api_engine.model.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDates{

    @JsonProperty("checkin")
    public LocalDate checkin;
    @JsonProperty("checkout")
    public LocalDate checkout;

    public BookingDates(LocalDate checkin, LocalDate checkout) {
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
