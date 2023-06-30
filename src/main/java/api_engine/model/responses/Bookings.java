package api_engine.model.responses;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bookings extends Booking{

    @Override public String toString() {
        return "Bookings{" +
            "bookingId=" + bookingId +
            '}';
    }
}

