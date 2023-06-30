package api_engine;

import api_engine.model.responses.BookingData;
import api_engine.model.responses.BookingDates;
import api_engine.model.responses.Bookings;
import api_engine.model.responses.token.Token;
import api_engine.model.responses.token.TokenAndTime;
import configs.Configuration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndPoints {
    private static String BASE_URL = Configuration.url;

    /**
     * Get token and time when token created. Return TokenAndTime object
     */
    public static TokenAndTime getToken() throws Exception {
        JSONObject params =new JSONObject();
        params.put("username",Configuration.userName);
        params.put("password", Configuration.password);

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON);

        Response tokenResponse = request.body(params.toJSONString()).post(Route.authentification());

        assertEquals(tokenResponse.statusCode(), 200);

        Token token = tokenResponse.getBody().as(Token.class);

        if(token.reason != null){
            throw new Exception("Token not exist. Reason: "+token.reason);
        }
        TokenAndTime tokenAndTime = new TokenAndTime(token.token, LocalDateTime.now());

        return tokenAndTime;
    }

    /**
     * Get token response
     * @param params
     * @return
     */
    public static Response getTokenResponse(JSONObject params){
         RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON);

        return request.body(params.toJSONString()).post(Route.authentification());
    }

    /**
     * Get all Booking id's
     * @param params
     * @return
     */
    public static Response getAllBookingIds(JSONObject params)
    {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON);

        return request.params(params).get(Route.booking());
    }

    /**
     * get booking information by booking id
     * @param id
     * @param firstName
     * @param LastName
     * @param checkIn
     * @param checkOut
     * @return
     */
    public static Response getBooking(int id, String firstName, String LastName, LocalDateTime checkIn, LocalDateTime checkOut)
    {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON);

        return request.queryParam("id",id).get(Route.booking());
    }

    /**
     * Create new booking
     * @param firstName
     * @param lastName
     * @param totalPrice
     * @param depositPaid
     * @param checkIn
     * @param checkOut
     * @param additionalNeeds
     * @return
     */
    public static Response createBooking(String firstName, String lastName, double totalPrice, boolean depositPaid, LocalDateTime checkIn, LocalDateTime checkOut, String additionalNeeds )
    {
        BookingDates bookingDates = new BookingDates(checkIn,checkOut);
        BookingData bookingData = new BookingData(firstName,lastName,totalPrice,depositPaid,bookingDates,additionalNeeds);

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON);

        return request.body(bookingData).post(Route.booking());
    }


    /**
     * Update booking by booking id.
     * @param firstName
     * @param lastName
     * @param totalPrice
     * @param depositPaid
     * @param checkIn
     * @param checkOut
     * @param additionalNeeds
     * @param id
     * @return
     */
    public static Response updateBooking(String firstName, String lastName, double totalPrice, boolean depositPaid, LocalDateTime checkIn, LocalDateTime checkOut, String additionalNeeds, int id )
    {
        BookingDates bookingDates = new BookingDates(checkIn,checkOut);
        BookingData bookingData = new BookingData(firstName,lastName,totalPrice,depositPaid,bookingDates,additionalNeeds);


        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON);

        return request.queryParam("id",id).body(bookingData).put(Route.booking());
    }

    /**
     * Partial update booking by id
     * @param firstName
     * @param lastName
     * @param totalPrice
     * @param depositPaid
     * @param checkIn
     * @param checkOut
     * @param additionalNeeds
     * @param id
     * @return
     */
    public static Response partialUpdateBooking(String firstName, String lastName, double totalPrice, boolean depositPaid, LocalDateTime checkIn, LocalDateTime checkOut, String additionalNeeds, int id )
    {

        BookingDates bookingDates = new BookingDates(checkIn,checkOut);
        BookingData bookingData = new BookingData(firstName,lastName,totalPrice,depositPaid,bookingDates,additionalNeeds);

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON);


        return request.queryParam("id",id).body(bookingData).patch(Route.booking());
    }

    /**
     * Delete booking by booking id
     * @param id
     * @return
     */
    public static Response deleteBooking(int id){
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON);
        return request.pathParams("id", id).delete(Route.booking());
    }

    /**
     *  A simple health check endpoint to confirm whether the API is up and running.
     * @return
     */
    public static Response pingHealthCheck(){
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON);

        return request.get(Route.ping());
    }



    public void verifyTokenDuration() throws Exception {
        Duration duration = Duration.between(LocalDateTime.now(), TokenAndTime.getTokenTimeStart());
        if ( duration.getSeconds() > 600){
            getToken();
        }
    }


    public void response(){

        //Response bookingResponse = getAllBookingIds();
      // assertEquals(bookingResponse.statusCode(), 200);
//        String jsonResponse = bookingResponse.getBody().asString();
//        System.out.println(jsonResponse);
////
//       assertEquals(bookingResponse.statusCode(), 200);
//       Bookings[] bookings = bookingResponse.getBody().as(Bookings[].class);


    }



}
