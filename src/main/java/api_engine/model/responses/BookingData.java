package api_engine.model.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingData {

    @JsonProperty("firstname")
    public String firstName;
    @JsonProperty("lastname")
    public String lastName;
    @JsonProperty("totalprice")
    public double totalPrice;
    @JsonProperty("depositpaid")
    public boolean depositPaid;
    @JsonProperty("bookingdates")
    public BookingDates bookingDates;
    @JsonProperty("additionalneeds")
    public String additionalNeeds;

    public BookingData(String firstName, String lastName, double totalPrice, boolean depositPaid, BookingDates bookingDates, String additionalNeeds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.bookingDates = bookingDates;
        this.additionalNeeds = additionalNeeds;
    }

    @Override public String toString() {
        return "BookingData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", totalPrice=" + totalPrice +
            ", depositPaid=" + depositPaid +
            ", bookingDates=" + bookingDates +
            ", additionalNeeds='" + additionalNeeds + '\'' +
            '}';
    }
}
