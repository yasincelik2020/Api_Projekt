package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import utilities.ObjectMapperUtils;

import static booker_test.R01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R02_GetBooking extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        User sends request
    Then
        Status code should be 200
    And
        Response body should be like:
            {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 15,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-03-07",
                "checkout": "2024-09-25"
            },
            "additionalneeds": "Lunch"
           }

    */

    @Test
    void getBookingTest (){
        // Set the url
        spec.pathParams("first","booking","second",bookingId);
        System.out.println(bookingId);

        //Set the expected data
        String strJson = """
                            {
                            "firstname": "John",
                            "lastname": "Doe",
                            "totalprice": 15,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2023-03-07",
                                "checkout": "2024-09-25"
                            },
                            "additionalneeds": "Lunch"
                           }
                """;
        BookingPojo expectedData = ObjectMapperUtils.jsonToJava(strJson, BookingPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(response.statusCode(),200);
        assertEquals(actualData.getFirstname(),expectedData.getFirstname());


    }


}
