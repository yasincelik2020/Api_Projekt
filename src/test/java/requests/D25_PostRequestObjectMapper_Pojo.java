package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class D25_PostRequestObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {
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

        ObjectMapper objectMapper = new ObjectMapper();

            //2.Adim: readvalue() metodu ile ceviri yap. Once json datayi string bir konteynere al.

        String strJson= """        
                {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
                }
          """; // Text Block --> Json formatinda yazmam gerekiyor.(.

        //readvalue() methodunda ilk parametrede cevrilmek istenen json datanin string hali, 2. parametrede cevrilmek istenen tipi(map,posoclass) yaziyoruz.
        JsonPlaceHolderPojo expectedData = objectMapper.readValue(strJson, JsonPlaceHolderPojo.class); // String text block'u burada map'e ceviriyoruz.
        System.out.println("expectedData = " + expectedData);

        //2.Yöntem
        Gson gson = new Gson();
        JsonPlaceHolderPojo expectedData2 = gson.fromJson(strJson, JsonPlaceHolderPojo.class);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do the assertion
        JsonPlaceHolderPojo actualData = objectMapper.readValue(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(),201);
        assertEquals(actualData.getUserId(),expectedData.getUserId());
        assertEquals(actualData.getTitle(),expectedData.getTitle());
        assertEquals(actualData.getCompleted(),expectedData.getCompleted());



    }
}
