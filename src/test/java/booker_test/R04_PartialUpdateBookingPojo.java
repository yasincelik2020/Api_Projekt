package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import pojos.PartialUpdatePojo;

import static booker_test.R01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static utilities.ObjectMapperUtils.jsonToJava;

public class R04_PartialUpdateBookingPojo extends BookerBaseUrl {
    //https://restful-booker.herokuapp.com/booking/:id
//      -H 'Content-Type: application/json' \
//            -H 'Accept: application/json' \
//            -H 'Cookie: token=abc123' \
//            -d '{
//            "firstname" : "James",
//            "lastname" : "Brown"
//}'
    @Test
    void testPartialUpdate(){
        //Set the url
        spec.pathParams("first","booking", "second", bookingId);
        //Set the expected data
        String strjson = """
                {
                    "firstname" : "James",
                    "lastname" : "Brown"
                }
                
                """;
        PartialUpdatePojo expectedData = jsonToJava(strjson,PartialUpdatePojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the expected data and get the response
        Response response = given(spec)
                .body(expectedData)
                .patch("{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData = response.as(BookingPojo.class);

        //Do the assertion

        assertEquals(response.statusCode(),200);
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());



    }

}
