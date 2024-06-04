package requests;

import base_urls.BookerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class D08_JsonPath extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/56
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
        {
            "firstname": "Josh",
            "lastname": "Allen",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "super bowls"
        }
*/

    @Test
    void  jsonPathTest(){

        // Set the url
        spec.pathParams("first","booking","second","39");

        //Send the request and get the response
        Response response =given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //         HTTP Status Code should be 200
        int statusCode = response.statusCode();
        assertEquals(statusCode, 200);

        //        Response content type is "application/json"
        String contentType = response.contentType();
//        assertEquals(contentType, "application/json; charset=utf-8");
        assertEquals(contentType, ContentType.JSON.withCharset("utf-8"));

        // Body'den specific bir field'i almak icin JsonPath kullanilabilir:
        // 1.Adim: Response ' i JsonPath'e cevir
        JsonPath jsonPath = response.jsonPath();

        // 2.Adim: JsonPath objesinden specific bir datayi cagir
        String firstname = jsonPath.getString("firstname");
        System.out.println("firstname = " + firstname);

        String lastname = jsonPath.getString("lastname");
        int totalprice = jsonPath.getInt("totalprice");
        boolean depositpaid = jsonPath.getBoolean("depositpaid");
        String checkin = jsonPath.getString("bookingdates.checkin");
        String checkout = jsonPath.getString("bookingdates.checkout");
        String additionalneeds = jsonPath.getString("additionalneeds");

        assertEquals("Josh", firstname);
        assertEquals("Allen", lastname);
        assertEquals(111, totalprice);
        assertTrue(depositpaid);
        assertEquals("2018-01-01", checkin);
        assertEquals("2019-01-01", checkout);
        assertEquals("super bowls", additionalneeds);


    }
}
