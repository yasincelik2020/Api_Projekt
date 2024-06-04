package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class D16_PatchRequest extends JsonPlaceHolderBaseUrl {
    //Parcali Update
     /*
    Given
        1) https://jsonplaceholder.typicode.com/todos/198
        2) {
             "title": "Read Books"
           }
    When
        I send PATCH Request to the Url
    Then
       Status code is 200
       And response body is like  {
                                        "userId": 10,
                                        "id": 198,
                                        "title": "Read Books",
                                        "completed": true
                                    }
*/

    @Test
    void patchRequestTest(){
        //Set the url
        spec.pathParams("first","todos","second","198");
        // Set the expected Data
        Map<String,Object> expectedData = JsonPlaceHolderTestData.expectedDataMap(null,"Read Books",null);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}"); // patch ile sadece ilgili veriyi update ediyoruz.
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData = response.as(Map.class);

        assertEquals(response.statusCode(),200);
        assertEquals(actualData.get("title"),expectedData.get("title"));

        //Eger tüm body assert edilecekse böyle yapabiliriz.
        assertEquals(actualData.get("userId"),10);
        assertEquals(actualData.get("completed"),true);

    }


}
