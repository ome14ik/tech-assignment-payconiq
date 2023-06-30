package api_engine.model.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingCreating extends Booking {

        public BookingData bookingData;
    }
