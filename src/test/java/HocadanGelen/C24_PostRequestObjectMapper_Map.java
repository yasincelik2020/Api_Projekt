package HocadanGelen;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class C24_PostRequestObjectMapper_Map extends JsonPlaceHolderBaseUrl {
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
        //Object mapper ile json datayı map'e çavirelim.
        //1. Adım: ObjectMapper objesi oluştur:
        ObjectMapper objectMapper = new ObjectMapper();

        //2. Adım: readValue() methodu ile çeviri yap: Önce json datayı string bir containera al.
        String strJson = """
                {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
                }
                """;
        //readValue() methodunda ilk parametrede çevrilmek istenen json datanın String hali, 2. parametrede ise json'ın çevrileceği class belirtilmelidir.
        Map expectedData = objectMapper.readValue(strJson, Map.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        Map actualData = objectMapper.readValue(response.asString(), Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.get("userId"), expectedData.get("userId"));
        assertEquals(actualData.get("title"), expectedData.get("title"));
        assertEquals(actualData.get("completed"), expectedData.get("completed"));

    }

}
