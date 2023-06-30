package BDD;

import api_engine.EndPoints;
import api_engine.model.responses.Bookings;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingStepsDef {
    private Response bookingResponse;
    private final JSONObject jsonParams = new JSONObject();

    @When ("^all booking IDs are requested")
    public void requestBookingIds(){
        bookingResponse = EndPoints.getAllBookingIds(jsonParams);
    }
    @Then ("^the response status code should be (.*)")
    public void verifyResponseStatusCode(int expectedStatusCode){
        assertEquals(expectedStatusCode, bookingResponse.statusCode() );
    }
    @And ("^a list of booking IDs should be returned (.*)")
    public void getListOfBookingIds(boolean expectedResult){
        boolean actualResult;
        List<Integer> bookings = new ArrayList<>();
        var body = bookingResponse.getBody().asString();

        if(!body.equals("Internal Server Error")){
            Bookings []bookingData = bookingResponse.getBody().as(Bookings[].class);

            for (Bookings bookingId: bookingData) {
                bookings.add(bookingId.bookingId);
            }
        }

        actualResult = bookings.size() > 0;

        assertEquals(expectedResult, actualResult);
    }

    @And ("^add (.*) parameter and value (.*)")
    public void addParameter(String parameterName, String parameterValue) throws Exception {
        jsonParams.put(parameterName,parameterValue);
//        switch (parameterName){
//            case "checkin":
//                Date checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(parameterValue);
//                jsonParams.put(parameterName,checkInDate);
//            case"checkout":
//                Date checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(parameterValue);
//                jsonParams.put(parameterName,checkOutDate);
//                break;
//            case "firstname":
//                jsonParams.put(parameterName,parameterValue);
//                break;
//            case "lastname":
//                jsonParams.put(parameterName,parameterValue);
//                break;
//            default:
//                throw new Exception("Incorrect parameter name: " + parameterName);
//        }

    }






//    Given the base URL is set
//    When a specific booking with ID <bookingId> is requested
//    Then the response status code should be 200
//    And the booking details should be returned
//
//    Given the base URL is set
//    And a booking with the following details:
//        | firstName    | lastName   | totalPrice | depositPaid | checkIn    | checkOut   | additionalNeeds |
//        | <firstName>  | <lastName> | <totalPrice>| <depositPaid>| <checkIn>  | <checkOut> | <additionalNeeds> |
//    When a booking is created
//    Then the response status code should be 200
//    And the booking details should be returned
//
//    Scenario: Updating a booking
//    Given the base URL is set
//    And a booking with ID <bookingId> and the following updated details:
//        | firstName    | lastName   | totalPrice | depositPaid | checkIn    | checkOut   | additionalNeeds |
//        | <firstName>  | <lastName> | <totalPrice>| <depositPaid>| <checkIn>  | <checkOut> | <additionalNeeds> |
//    When a booking is updated
//    Then the response status code should be 200
//    And the updated booking details should be returned
//
//    Scenario: Partially updating a booking
//    Given the base URL is set
//    And a booking with ID <bookingId> and the following partial details:
//        | firstName    | lastName   | totalPrice | depositPaid | checkIn    | checkOut   | additionalNeeds |
//        | <firstName>  | <lastName> | <totalPrice>| <depositPaid>| <checkIn>  | <checkOut> | <additionalNeeds> |
//    When a booking is partially updated
//    Then the response status code should be 200
//    And the partially updated booking details should be returned
//
//    Scenario: Delete a booking
//    Given the base URL is configured
//    And a valid authentication token is obtained
//    When the booking with ID 123 is deleted
//    Then the booking with ID 123 is successfully deleted

}
