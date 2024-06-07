package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static utilities.ObjectMapperUtils.jsonToJava;

public class D28_PojoList extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        I send a GET request to the Url
    And
        Accept type is "application/json"
    Then
        HTTP Status Code should be 200
    And
        There must be a todo like:
            {
                "userId": 1,
                "id": 4,
                "title": "et porro tempora",
                "completed": true
            }
*/
    @Test
    void pojoListtest() throws JsonProcessingException {
        // Set the url
        spec.pathParams("first","todos");

        //Set the expected data
        String strJson = """
                            {
                                "userId": 1,
                                "id": 4,
                                "title": "et porro tempora",
                                "completed": true
                            }
                """;
        JsonPlaceHolderPojo expectedData = jsonToJava(strJson, JsonPlaceHolderPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("{first}");
//        response.prettyPrint();

        //Burada bir list icinde donen json datalari list icerisine pojo class objeleri seklinde atiyoruz.
        List<JsonPlaceHolderPojo> toDoList =new ObjectMapper().readValue(response.asString(), new TypeReference<>() {}); // normal class giremedigim icin abstract class (new TypeReference<>() {}) giriyoruz.
//        System.out.println("toDoList = " + toDoList);
        JsonPlaceHolderPojo actualData = new JsonPlaceHolderPojo();

        for (JsonPlaceHolderPojo w:toDoList) {
            if (w.getTitle().equalsIgnoreCase("et porro tempora")){
                actualData =w;
            };
        }

        assertEquals(response.statusCode(),200);
        assertEquals(actualData.getUserId(),expectedData.getUserId());
        assertEquals(actualData.getTitle(),expectedData.getTitle());
        assertEquals(actualData.getCompleted(),expectedData.getCompleted());

    }
}
