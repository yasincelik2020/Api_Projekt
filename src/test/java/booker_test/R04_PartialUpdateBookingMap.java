package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import java.util.Map;

import static booker_test.R01_CreateBooking.bookingId;
import static booker_test.R03_UpdateBooking.expectedDataUpdate;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R04_PartialUpdateBookingMap extends BookerBaseUrl {
    /*
    Given
        1) https://restful-booker.herokuapp.com/booking/:id
        2) {
            "firstname" : "Mary",
            "lastname" : "Star"
            }
    When
        User sends patch request
    Then
        Status code should be 200
    And
        Response body should be
                        {
                            "firstname" : "Mary",
                            "lastname" : "Star",
                            "totalprice" : 111,
                            "depositpaid" : true,
                            "bookingdates" : {
                                "checkin" : "2018-01-01",
                                "checkout" : "2019-01-01"
                            },
                            "additionalneeds" : "Breakfast"
                        }

     */

    @Test
    void partialUpdateBookingMapTest(){
        //Set the url
        spec.pathParams("first","booking","second",bookingId);

        //Set the expectedData// Patch request'te tüm body gönderilmeyecegii icin Map kullanmak önerilir.
        String strJson = """
                 {
                    "firstname" : "Mary",
                    "lastname" : "Star"
                 }
                """;
        Map expectedData = ObjectMapperUtils.jsonToJava(strJson,Map.class);
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map actualData= response.as(Map.class);
        System.out.println("actualData = " + actualData);

        //Ne gönderdiysek onu assert etmek ilk görevimizdir.
        assertEquals(response.statusCode(),200);
        assertEquals(actualData.get("firstname"),expectedData.get("firstname"));
        assertEquals(actualData.get("lastname"),expectedData.get("lastname"));

        //Hard coding ile assert edilebilir, ama tavsiye edilmez.
//        assertEquals(actualData.get("totalprice"),111);
//        assertEquals(actualData.get("depositpaid"),true);
//        assertEquals( ((Map)actualData.get("bookingdates")).get("checkin"),"2018-01-01");
//        assertEquals( ((Map)actualData.get("bookingdates")).get("checkout"),"2019-01-01");
//        assertEquals( actualData.get("additionalneeds"),"Breakfast");

        //Bir onceki class'ta olusturdugum expectedDatayi kullanabilirim.
        assertEquals(actualData.get("totalprice"),expectedDataUpdate.getTotalprice());
        assertEquals(actualData.get("depositpaid"),expectedDataUpdate.getDepositpaid());
        assertEquals( ((Map)actualData.get("bookingdates")).get("checkin"),expectedDataUpdate.getBookingdates().getCheckin());
        assertEquals( ((Map)actualData.get("bookingdates")).get("checkout"),expectedDataUpdate.getBookingdates().getCheckout());
        assertEquals( actualData.get("additionalneeds"),expectedDataUpdate.getAdditionalneeds());


    }

}
