package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class D24_PostRequestObjectMapper_Map extends JsonPlaceHolderBaseUrl {
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
    void postRequestObjectMapper_MapTest() throws JsonProcessingException {
//        Given
//        1) https://jsonplaceholder.typicode.com/todos
//        Set the Url
        spec.pathParams("first","todos");

        // Set the expected data ----> Payload
            //Object mapper ile Json datayi map'e cevirelim.
            //1. Adim: ObjectMapper objesi olustur. (databind'den aliniyor.)
        ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper --> String'i map veya Pojo ya cevirme islemini yapiyor.

            //2.Adim: readvalue() metodu ile ceviri yap. Once json datayi string bir konteynere al.

        String strJson= """        
                {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
                }
          """; // Text Block --> Json formatinda yazmam gerekiyor.(.

        //readvalue() methodunda ilk parametrede cevrilmek istenen json datanin string hali, 2. parametrede cevrilmek istenen tipi(map,posoclass) yaziyoruz.
        Map expectedData = objectMapper.readValue(strJson, Map.class); // String text block'u burada map'e ceviriyoruz.
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do the assertion
        Map actualData = objectMapper.readValue(response.asString(), Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(),201);
        assertEquals(actualData.get("userId"),expectedData.get("userId"));
        assertEquals(actualData.get("title"),expectedData.get("title"));
        assertEquals(actualData.get("completed"),expectedData.get("completed"));



    }
}
