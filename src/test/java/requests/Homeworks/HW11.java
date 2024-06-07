package requests.Homeworks;

import base_urls.AutomationBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HW11 extends AutomationBaseUrl {
    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12

    Note: Print using JsonPath: response.jsonPath().prettyPrint();

*/
    @Test
    void HW11Test(){
        // Set the url
        spec.pathParams("first","api","second","productsList");

        //Set the expected Data

        //Send the data and get the response
        Response response = given(spec).get("{first}/{second}");
        response.jsonPath().prettyPrint();

        //Do assertion

        assertEquals(response.statusCode(),200);

        // Assert that the number of "Women" user type is 12
        List<String> wowenList = response.jsonPath().getList("products.category.usertype.usertype");
        System.out.println("wowenList = " + wowenList);
        int count =0;
        for (String wowen : wowenList) {
            if (wowen.startsWith("W")){
                count++;
            }
        }
        System.out.println("count = " + count);
        assertEquals(count,12);



    }
}
