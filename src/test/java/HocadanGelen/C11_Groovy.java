package HocadanGelen;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C11_Groovy extends JsonPlaceHolderBaseUrl {
/*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
         I send GET Request to the URL
    Then
         1)Status code is 200
         2)Print all ids greater than 190 on the console
           Assert that there are 10 ids greater than 190
         3)Print all userIds whose ids are less than 5 on the console
           Assert that the number of userIds whose ids are less than 5 is 4
         4)Print all titles whose ids are greater than 195
           Assert that "numquam repellendus a magnam" is one of the titles whose ids are greater than 195
         5)Print id whose title is "quo adipisci enim quam ut ab"
           Assert that id is 8
*/

    //Groovy: Java tabanlı bir proglamlama dili.


    @Test
    void groovyTest() {

        //Set the url
        spec.pathParams("first", "todos");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();


        //Do assertion
        assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();

//        Print all ids greater than 190 on the console
        //1. Yol:
        List<Integer> ids = jsonPath.getList("id");
        //System.out.println("ids = " + ids);
        int counter = 0;
        for (int w : ids) {
            if (w < 191) {
                continue;
            }
            System.out.println(w);
            counter++;
        }

        //2. Yol:
        List<Integer> idsGreaterThan190 = jsonPath.getList("findAll{it.id>190}.id");//findAll methoduna List dönen yerden başlanılır. Burada body list döndüğü için en başta başladık.
        System.out.println("idsGreaterThan190 = " + idsGreaterThan190);


//        Assert that there are 10 ids greater than 190
        assertEquals(counter, 10);
        assertEquals(idsGreaterThan190.size(), 10);

        //Print all titles which ids' are greater than 190
        List<String> titleList = jsonPath.getList("title");
        for (int i = titleList.size() - counter; i < titleList.size(); i++) {
            System.out.println(titleList.get(i));
        }

        List<String> titlesWhoseIdsGreaterThan190 = jsonPath.getList("findAll{it.id>190}.title");
        System.out.println("titlesWhoseIdsGreaterThan190 = " + titlesWhoseIdsGreaterThan190);


        //         3)Print all userIds whose ids are less than 5 on the console

        List<Integer> userIdList = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("userIdList = " + userIdList);

        //           Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(userIdList.size(), 4);

//        4)Print all titles whose ids are greater than 195
        List<String> titleListGreaterThan195 = jsonPath.getList("findAll{it.id>195}.title");
        System.out.println("titleListGreaterThan195 = " + titleListGreaterThan195);

//        Assert that "numquam repellendus a magnam" is one of the titles whose ids are greater than 195
        assert titleListGreaterThan195.contains("numquam repellendus a magnam");//Java assertion

//        5)Print id whose title is "quo adipisci enim quam ut ab"
        List<Integer> idList = jsonPath.getList("findAll{it.title=='quo adipisci enim quam ut ab'}.id");
        System.out.println("idList = " + idList);

//        Assert that id is 8
        assertEquals(idList.getFirst(), 8);

    }

}
