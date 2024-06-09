package requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class D29_GetRequestGroovy extends GoRestBaseUrl {
    @Test
    void groovyGetTest(){
/*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Abhaidev Kaur", "Fr. Deenabandhu Adiga", "Akshita Singh DC" are among the users -> Bu data değişken
        And
            The female users are less than or equals to male users -> Bu data değişken
*/
        //Set the url
        spec.pathParams("first","users");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do the assertion

//        The value of "pagination limit" is 10
        response
                .then()
                .statusCode(200)
                .body("meta.pagination.limit",equalTo(10))
                .body("meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1")) //The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
                .body("data",hasSize(10)) //        The number of users should  be 10
                .body("data.status",hasItem("active")) //        We have at least one "active" status
                .body("data.name",hasItems("Archan Shah I", "Adwitiya Chattopadhyay", "Kannan Mehrotra Jr." )) //"Archan Shah I", "Adwitiya Chattopadhyay", "Kannan Mehrotra Jr."  are among the users -> Bu data değişken

        ;

        int numOfFemales = response.jsonPath().getList("data.findAll{it.gender == 'female'}").size();
        int numOfMales = response.jsonPath().getList("data.findAll{it.gender == 'male'}").size();

        assert (numOfFemales>numOfMales); //The female users are greater than or equals to male users -> Bu data değişken

        System.out.println("numOfMales = " + numOfMales);
        System.out.println("numOfFemales = " + numOfFemales);





    }
}
