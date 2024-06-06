package requests;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test_data.BookerTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class D18_PostRequestNestedMapTestData extends BookerBaseUrl {
    /*
    Given
        1) https://restful-booker.herokuapp.com/booking
        2) {
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
    When
        I send POST Request to the Url
    Then
        Status code is 200
        And response body should be like {
                                            "bookingid": 2243,
                                            "booking": {
                                                "firstname": "John",
                                                "lastname": "Doe",
                                                "totalprice": 471,
                                                "depositpaid": true,
                                                "bookingdates": {
                                                    "checkin": "2023-03-07",
                                                    "checkout": "2024-09-25"
                                                },
                                                "additionalneeds": "Lunch"
                                            }
                                        }
 */

    @Test
    void nestedMapTest() {
        //Set the Url
        spec.pathParams("first","booking");

        //Set the expected Data
        Map<String,String> bookingdates = BookerTestData.bookingdatesMap("2023-03-07","2024-09-25");

        System.out.println("bookingdates = " + bookingdates);

        Map<String,Object> expectedData = BookerTestData.expectedDataMap("John","Doe", 471,true, bookingdates,"Lunch");

        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);


//Tüm key'ler String, Value'lar Object container'i icinde bulundugundan, dönen value'lari kendi methodlari icinde kullanmak icin type casting yapilir.
        assertEquals(response.statusCode(), 200);
        assertEquals(((Map)actualData.get("booking")).get("firstname"), expectedData.get("firstname"));
        assertEquals(((Map)actualData.get("booking")).get("lastname"), expectedData.get("lastname"));
        assertEquals(((Map)actualData.get("booking")).get("totalprice"), expectedData.get("totalprice"));
        assertEquals(((Map)actualData.get("booking")).get("depositpaid"), expectedData.get("depositpaid"));

        assertEquals(((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"), bookingdates.get("checkin"));
        assertEquals(((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"), bookingdates.get("checkout"));
        
        assertEquals(((Map)actualData.get("booking")).get("additionalneeds"), expectedData.get("additionalneeds"));


    }
}
