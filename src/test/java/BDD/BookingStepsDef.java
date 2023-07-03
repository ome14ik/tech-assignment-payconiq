package BDD;

import BDD.enums.BodyErrors;
import api_engine.EndPoints;
import api_engine.model.responses.BookingCreating;
import api_engine.model.responses.BookingData;
import api_engine.model.responses.BookingDates;
import api_engine.model.responses.Bookings;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingStepsDef {
    List<Integer> bookingsIds;
    private Response bookingResponse;
    private final JSONObject jsonParams = new JSONObject();
    BookingCreating bookingCreated;
    BookingData bookingData;
    private String id;

    @When ("^all booking IDs are requested")
    public void requestBookingIds(){
        bookingResponse = EndPoints.getAllBookingIds(jsonParams);
    }
    @Then ("^the response status code should be (.*)")
    public void verifyResponseStatusCode(int expectedStatusCode){
        var statusCode = bookingResponse.statusCode();
        assertEquals(expectedStatusCode, statusCode);
    }
    @And ("^a list of booking IDs should be returned (.*)")
    public void returnedAllBookingIds(boolean expectedResult){
        boolean actualResult;
        bookingsIds = getListOfBookingIds();

        actualResult = bookingsIds.size() > 0;

        assertEquals(expectedResult, actualResult);
    }

    @And ("^add (.*) parameter and value (.*) of existing booking")
    public void addParameter(String parameterName, String parameterValue) throws Exception {
        jsonParams.put(parameterName,parameterValue);

    }


    @And ("^use any existing - (.*) - booking Id as parameter.")
     public void getAnyExistingId(boolean idExist){
        boolean actualResult;
        int bookingId;

        List<Integer> bookingsIdsList = getResponseWithAllBookingIds(null);

        if (!idExist){
            bookingId = Collections.max(bookingsIdsList) + 1000;
            actualResult =  bookingsIdsList.contains(bookingId);
        } else {
            bookingId = bookingsIdsList.get((int) (Math.random() * bookingsIdsList.size()));

            actualResult =  bookingsIdsList.contains(bookingId);

        }

        id = bookingId+"";
        assertEquals(idExist,actualResult);

    }

    @When ("^a specific booking with random id's is requested")
    public void specificIdRequired(){
        bookingResponse = EndPoints.getBooking(id, jsonParams);
    }

    @And("^the booking details should be returned details - (.*)")
    public void getBookingDetails(boolean expectedResult){
        boolean actualResult = false;


        if(!isBodyErrorExist()){
            BookingData bookingData = bookingResponse.getBody().as(BookingData.class);
            actualResult = true;
        }

        assertEquals(expectedResult, actualResult);

    }

    @And("^A new booking created. First name - (.*), Last Name -(.*), total price - (.*), checkin date - (.*), deposit paid - (.*), checkout date - (.*), additional needs - (.*)")
    public void newBookingCreated(String firstName, String lastName,Double totalPrice, String checkin, boolean depositPaid ,String checkout, String additionalNeeds){

        Response response = EndPoints.createBooking(createDefaultBooking(firstName,lastName,totalPrice,checkin, depositPaid, checkout, additionalNeeds));

        assertEquals(response.statusCode(), 200);

        bookingCreated = response.getBody().as(BookingCreating.class);
    }


    @And("^a booking with the following details: First name - (.*), Last Name -(.*), total price - (.*), checkin date - (.*), deposit paid - (.*), checkout date - (.*), additional needs - (.*)")
    public void detailsForCreating(String firstName, String lastName,Double totalPrice, String checkin, boolean depositPaid ,String checkout, String additionalNeeds){
        bookingData = createDefaultBooking(firstName,lastName,totalPrice,checkin, depositPaid, checkout, additionalNeeds);
    }

    @When("^a booking is created")
    public void createBooking() {
        bookingResponse = EndPoints.createBooking(bookingData);
    }

    @And("^the booking details should be returned - (.*)")
    public void verifyReturnedBookingDetails(boolean bookingReturn){

        boolean isExist = false;
        try {
            bookingCreated = bookingResponse.getBody().as(BookingCreating.class);
            isExist = true;
        }
        finally {
            assertEquals(bookingReturn, isExist);
        }

    }

    @When("^a booking is updated")
    public void updateBooking() throws Exception {
        EndPoints.getToken();
        bookingResponse = EndPoints.updateBooking(bookingData, id);
    }

    @When("^a booking is partial updated")
    public void partialUpdateBooking() throws Exception {
        EndPoints.getToken();
        bookingResponse = EndPoints.partialUpdateBooking(bookingData, id);
    }


    @And ("^the updated booking details should be returned - (.*)")
    public void verifyUpdatedReturnedBookingDetails (boolean bookingReturn){
        boolean isExist = false;
        try {
            bookingCreated = bookingResponse.getBody().as(BookingCreating.class);
            isExist = true;
        }
        finally {
            assertEquals(bookingReturn, isExist);
        }
    }

    @When ("^the booking id is deleted")
    public void deleteBookingId() throws Exception {
        EndPoints.getToken();
        EndPoints.deleteBooking(id);
    }

    @Then ("^the booking Id is successfully deleted - (.*)")
    public void verifyIdExistance(int statusCode){
        bookingResponse = EndPoints.getBooking(id, null);

        assertEquals(statusCode, bookingResponse.statusCode());
    }

    /**
     * Verify errors
     */
    private boolean isBodyErrorExist(){
        var body = bookingResponse.getBody().asString();
        boolean bodyError = true;
        if(!body.equals(BodyErrors.NotFound.getValue()) || !body.equals((BodyErrors.InternalServerError.getValue()))){
            bodyError = false;
        }
        return bodyError;
    }



    /**
     * Get list of bookings id's by entering parameters or without
     * @return
     */
    private List<Integer> getListOfBookingIds(){
    bookingsIds = new ArrayList<>();
    var body = bookingResponse.getBody().asString();

    if(!body.equals(BodyErrors.InternalServerError.getValue())){
        Bookings []bookingData = bookingResponse.getBody().as(Bookings[].class);

        for (Bookings bookingId: bookingData) {
                bookingsIds.add(bookingId.bookingId);
        }
    }

    return bookingsIds;
}

    /**
     * get Response With All Booking Ids related to different parameters or not related
     */
    private List <Integer> getResponseWithAllBookingIds(JSONObject params){
    bookingResponse = EndPoints.getBooking(null, params);
    bookingsIds = getListOfBookingIds();
    return bookingsIds;
}

    /**
     * Create default booking
     */
    private BookingData createDefaultBooking(String firstName, String lastName,Double totalPrice, String checkin, boolean depositPaid ,String checkout, String additionalNeeds){

        LocalDate checkoutDate = formatFromString(checkout);
        LocalDate checkinDate = formatFromString(checkin);
        BookingDates bookingDates = new BookingDates(checkinDate ,checkoutDate);
        BookingData bookingData = new BookingData (firstName,lastName, totalPrice, depositPaid, bookingDates,additionalNeeds );

    return  bookingData;
}

public LocalDate formatFromString( String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateTime = LocalDate.parse(dateString, formatter);
    return dateTime;
}

}
