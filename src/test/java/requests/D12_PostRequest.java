package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class D12_PostRequest extends JsonPlaceHolderBaseUrl {
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
    void postrequest(){
//        Given
//        1) https://jsonplaceholder.typicode.com/todos
//        set the Url
        spec.pathParams("first","todos");

        // Set the expected data ----> Payload
        // Assertion isleminde String body den spesifik degerler alinamayacagindan bu yontem tavsiye edilmez.
        // Map' ealarak assertion yapilmasi tavsiye edilir.
        String expectedData = """        
                {
                "userId": "55",
                "title": "Tidy your room",
                "completed": false
                }
          """; // Text Block --> Test classinda daha duzenli görünüm icin kullaniyorum.

        //Send the request and get the response
        Response response = given(spec)
                .contentType(ContentType.JSON) // String olan payload'un cevrilecegi icerik tipi belirtilmeli.
                .body(expectedData) // Gonderilecek data burada belirtilmeli.
                .post("{first}");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body("userId",equalTo("55")
                ,"title",equalTo("Tidy your room")
                        ,"completed",equalTo(false));




    }
}
