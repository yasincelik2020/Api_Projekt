package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class D13_PostRequestMap extends JsonPlaceHolderBaseUrl {
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
        // Assertion isleminde String body den spesifik degerler alinamayacagindan bu yontem tavsiye edilmez.
        // Map' e alarak assertion yapilmasi tavsiye edilir.
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);

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
