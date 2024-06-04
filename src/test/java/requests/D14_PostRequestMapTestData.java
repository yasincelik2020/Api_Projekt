package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class D14_PostRequestMapTestData extends JsonPlaceHolderBaseUrl {
    /*
     Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
          }
    When
        I send POST Request to the Url

    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
*/
    @Test
    void postrequestMap(){
//        Given
//        1) https://jsonplaceholder.typicode.com/todos
//        set the Url
        spec.pathParams("first","todos");

        // Set the expected data ----> Payload
        // Kodumuzu dinamik hale getirdik.
        // Map' e alarak assertion yapilmasi tavsiye edilir.
        Map<String,Object> expectedData = JsonPlaceHolderTestData.expectedDataMap(55, "Tidy your room",false);
        System.out.println("expectedData = " + expectedData);


// Text Block --> Test classinda daha duzenli görünüm icin kullaniyorum.

        //Send the request and get the response
        Response response = given(spec)
                .body(expectedData) // Gonderilecek data burada belirtilmeli.
                .post("{first}");
        response.prettyPrint();

        //Do assertion
        //1.Yol:
        response
                .then()
                .statusCode(201)
                .body("userId",equalTo(expectedData.get("userId"))
                ,"title",equalTo(expectedData.get("title"))
                        ,"completed",equalTo(expectedData.get("completed")));

        //2.Yol: Response i map'e cevirerek assertion yapacagiz.
        Map<String,Object> actualData = response.as(Map.class); // De-serialization islemi yapildi.
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(),201);
        assertEquals(actualData.get("userId"),expectedData.get("userId"));
        assertEquals(actualData.get("title"),expectedData.get("title"));
        assertEquals(actualData.get("completed"),expectedData.get("completed"));






    }
}
