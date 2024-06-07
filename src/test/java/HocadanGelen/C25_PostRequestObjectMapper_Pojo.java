package HocadanGelen;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class C25_PostRequestObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {
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
    void objectMapperMapTest() throws JsonProcessingException {
        //Set the url
        spec.pathParams("first", "todos");

        //Set the expected data
        ObjectMapper objectMapper = new ObjectMapper();

        String strJson = """
                {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
                }
                """;

        JsonPlaceHolderPojo expectedData = objectMapper.readValue(strJson, JsonPlaceHolderPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        JsonPlaceHolderPojo actualData = objectMapper.readValue(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.getUserId(), expectedData.getUserId());
        assertEquals(actualData.getTitle(), expectedData.getTitle());
        assertEquals(actualData.getCompleted(), expectedData.getCompleted());


    }

}
