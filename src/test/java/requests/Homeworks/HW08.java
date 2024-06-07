package requests.Homeworks;

import base_urls.PetstoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test_data.PetstoreTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HW08 extends PetstoreBaseUrl {
    /*
Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
https://petstore.swagger.io/v2/user
  {
    "id": 0,
    "username": "string",
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "password": "string",
    "phone": "string",
    "userStatus": 0
  }
*/
    @Test
    void HW08Test(){
        // Set the Url
        spec.pathParams("first","v2","second","user");

        //Create expected Data
        Map<String,Object> expectedData = PetstoreTestData.expectedTestData(0,"ali","alis","Kamaci","fret@gmail.com","1234","01235664554",0);

        //Send the Data and get hte Response

        Response response = given(spec).body(expectedData).post("{first}/{second}");
        response.prettyPrint();
        Map<String,Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        //Do the assertion
        assertEquals(response.statusCode(),200);
    }
}
