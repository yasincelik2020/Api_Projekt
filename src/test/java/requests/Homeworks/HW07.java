package requests.Homeworks;

import base_urls.RegresBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test_data.RegresTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HW07 extends RegresBaseUrl {
    /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */
    @Test
    void HW07(){
        //Set the Url
        spec.pathParams("first","api","second","users");

        //Send the data and get the response (I send POST Request to the Url)
        Map<String,Object> expectedData= RegresTestData.expectedTestData("John","Engineer");
        Response response = given(spec).body(expectedData).post("{first}/{second}");
        response.prettyPrint();

        Map<String,Object> actualData = response.as(Map.class);
//        Status code is 201
//        And response body should be like
        assertEquals(response.statusCode(),201);
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));



    }
}
