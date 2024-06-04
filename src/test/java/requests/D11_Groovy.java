package requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class D11_Groovy extends JsonPlaceHolderBaseUrl {

    //Groovy: Java tabanli bir programlama dili
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
    @Test
    void groovyTest() {
        //Set the url
        spec.pathParams("first", "todos");

        //Set the expected Dqata

        //Send the request and get the response
        Response response = given(spec).get("{first}");
//        response.prettyPrint();


        // Do assertion
//        1)Status code is 200
        assertEquals(response.statusCode(), 200);

//        2)Print all ids greater than 190 on the console
        JsonPath jsonPath = response.jsonPath();
        List<Integer> ids = jsonPath.getList("id");
        int count = 0;
        for (int w : ids) {
            if (w > 190) {
                count++;
                System.out.println(w);
            }
        }

        //2.Yol

        //2. Yol:
        List<Integer> idsGreaterThan190 = jsonPath.getList("findAll{it.id>190}.id"); //findAll metoduna List dönen yerden baslanilir.
        // Burada body list alindigi icin en bastan baslanir.
        System.out.println("idsGreaterThan190 = " + idsGreaterThan190);

        //        Assert that there are 10 ids greater than 190
        assertEquals(count, 10);

//        2)Print all titles which ids greater than 190 on the console
        List<String> titleList = response.jsonPath().getList("title");
        for (int i = titleList.size() - count; i < titleList.size(); i++) {
            System.out.println("titleList.get(" + i + ") = " + titleList.get(i));
        }
        List<String> titlesWhoseIdsGreaterThan190 = jsonPath.getList("findAll{it.id>190}.title");
        System.err.println("titlesWhoseIdsGreaterThan190 = " + titlesWhoseIdsGreaterThan190);

//        3)Print all userIds whose userIds are less than 5 on the console
        List<String> userIdsWhoseIdsLessThan5 = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("idsWhoseIdsLessThan5 = " + userIdsWhoseIdsLessThan5);

//        Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(userIdsWhoseIdsLessThan5.size(),4);

//        4)Print all titles whose ids are greater than 195
        List<String> titlesWhoseIdsMoreThan195 = jsonPath.getList("findAll{it.id>195}.title");
        System.out.println("titlesWhoseIdsMoreThan195 = " + titlesWhoseIdsMoreThan195);

//        Assert that "numquam repellendus a magnam" is one of the titles whose ids are greater than 195
        assertTrue(titlesWhoseIdsMoreThan195.contains("numquam repellendus a magnam"));
        assert titlesWhoseIdsMoreThan195.contains("numquam repellendus a magnam"); //Java Assertion

//        5)Print id whose title is "quo adipisci enim quam ut ab"
        List<Integer> titleSuched = jsonPath.getList("findAll{it.title=='quo adipisci enim quam ut ab'}.id");
        System.out.println("titleSuched = " + titleSuched);
//        Assert that id is 8
        assertEquals(titleSuched.getFirst(),8);

    }
}
