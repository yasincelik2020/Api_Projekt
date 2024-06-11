package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static booker_test.R01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class R05_DeleteBooking extends BookerBaseUrl {

    @Test
    void testDeleteBooking(){
        //Set the url
        spec.pathParams("first","booking","second",bookingId);

        //Send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();


        //Do the assertion
        assertEquals(response.statusCode(), 201);

        String actualEnd = response.getStatusLine();
        assertTrue(actualEnd.contains("Created") );

    }

}
