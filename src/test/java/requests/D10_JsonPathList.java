package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class D10_JsonPathList extends JsonPlaceHolderBaseUrl {

    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
         I send GET Request to the URL
    Then
         1)Status code is 200
         2)Print all ids greater than 190 on the console
           Assert that there are 10 ids greater than 190

*/
    @Test
    void jsonPathListTest(){
        //Set the url
        spec.pathParams("first","todos");

        //Set the expected Dqata

        //Send the request and get the response
        Response response = given(spec).get("{first}");
//        response.prettyPrint();


        // Do assertion
//        1)Status code is 200
        assertEquals(response.statusCode(), 200);

//        2)Print all ids greater than 190 on the console
        JsonPath jsonPath = response.jsonPath();
        List<Integer> ids= jsonPath.getList("id");


        int count = 0;
        for (int w : ids) {
            if (w>190){
                count++;
                System.out.println(w);
            }
        }
        //        Assert that there are 10 ids greater than 190
        assertEquals(count,10);

//        2)Print all titles which ids greater than 190 on the console
        List<String> titleList = response.jsonPath().getList("title");
        for (int i = titleList.size()-count; i < titleList.size() ; i++) {
            System.out.println("titleList.get("+i+") = " + titleList.get(i));
        }

    }



}
