package api_engine;

import api_engine.enums.PathParameter;
import api_engine.helpers.JsonHelper;
import api_engine.model.responses.BookingData;
import api_engine.model.responses.BookingDates;
import api_engine.model.responses.token.Token;
import api_engine.model.responses.token.TokenAndTime;
import configs.Configuration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndPoints {
    private static String BASE_URL = Configuration.url;
    private static TokenAndTime tokenAndTime;
    /**
     * Get token and time when token created. Return TokenAndTime object
     */
    public static void getToken() throws Exception {
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
        tokenAndTime = new TokenAndTime(token.token, LocalDateTime.now());

        //return tokenAndTime;
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
     */
    public static Response getBooking(String id, JSONObject params)
    {
        String contentType = "application/json";
        String accept = "application/json";

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .header("Accept", accept)
            .header("Content-Type", contentType);

        if ((params == null || params.size() == 0) && id == null){
            return request.get(Route.booking());
        }else  if (!(id == null) && (params == null || params.size() == 0)){
            return request.get(Route.booking()+id);
        } else if (id == null && !(params == null || params.size() == 0)){
            return request.params(params).get(Route.booking());
        }
            return request.params(params).get(Route.booking()+id);





    }

    /**
     * Create new booking
     * @return
     */
    public static Response createBooking(BookingData bookingData)
    {
        String contentType = "application/json";
        String accept = "application/json";

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .header("Accept", accept)
            .header("Content-Type", contentType)
            .auth().preemptive().basic("admin", "password123" );

        // Convert BookingData to JSON string
        String bookingBody = getJsonParamsFromObject(bookingData).toJSONString();

        return request.body(bookingBody).post(Route.booking());
    }

    /**
     * Update booking by booking id.
     */
    public static Response updateBooking(BookingData bookingData, String id)
    {
        String contentType = "application/json";
        String accept = "application/json";

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .header("Accept", accept)
            .header("Content-Type", contentType)
            .auth().preemptive().basic("admin", "password123" );

        String bookingBody = getJsonParamsFromObject(bookingData).toJSONString();

        return request.body(bookingBody).put(Route.booking()+id);
    }

    /**
     * Partial update booking by id
     */
    public static Response partialUpdateBooking(BookingData bookingData, String id)
    {
        String contentType = "application/json";
        String accept = "application/json";

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .header("Accept", accept)
            .header("Content-Type", contentType)
            .auth().preemptive().basic("admin", "password123" );
            //.cookie("token="+tokenAndTime.token);

        String bookingBody = getJsonParamsFromObject(bookingData).toJSONString();

        return request.body(bookingBody).patch(Route.booking()+id);
    }

    /**
     * Delete booking by booking id
     * @param id
     * @return
     */
    public static Response deleteBooking(String id){
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given()
            .auth().preemptive().basic("admin", "password123" );
            //.cookie("token="+tokenAndTime.token);
        return request.delete(Route.booking()+id);
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



    private static JSONObject getJsonParamsFromObject(BookingData bookingData){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", bookingData.firstName);
        jsonObject.put("lastname", bookingData.lastName);
        jsonObject.put("totalprice", bookingData.totalPrice);
        jsonObject.put("depositpaid", bookingData.depositPaid);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", bookingData.bookingDates.checkin.toString());
        bookingDates.put("checkout", bookingData.bookingDates.checkout.toString());

        jsonObject.put("bookingdates", bookingDates);
        jsonObject.put("additionalneeds", bookingData.additionalNeeds);

        return jsonObject;

    }



}
