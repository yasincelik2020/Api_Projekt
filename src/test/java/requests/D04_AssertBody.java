package requests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class D04_AssertBody {
/*
    Given
        https://jsonplaceholder.typicode.com/todos/23
    When
        User send GET Request to the URL
    Then
        HTTP Status Code should be 200
    And
        Content Type should be "application/json"
    And
        "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
    And
        "completed" is false
    And
        "userId" is 2
*/
    /*
    API testinde izlenecek adimlar:
    1.Set the url
    2.Set the expected data
    3.Send the request and get the response
    4.Do assertion


     */
    @Test
    void bodyAssertionTest(){
//        1.Set the url
        String url = "https://jsonplaceholder.typicode.com/todos/23";
//        2.Set the expected data


//        3.Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

//        4.Do assertion
        //1. Yol // Hard assertion. Ilk hatada test durur . Body'nin disindaki datalar metadaat olarak geciyor.
        response
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")) //"title" is "et itaque necessitatibus maxime molestiae qui quas velit",
                .body("completed", equalTo(false))//"completed" is false
                .body("userId",equalTo( 2)) //"userId" is 2
        ; // Coklu body icerisinde coklu assert yapilirsa bu hard assertion seklinde calisir. (Ilk fail durumunda test durur)

        //2.Yol //Soft assertion. Tüm hatalari test eder
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")
                        ,"completed", equalTo(false)
                        ,"userId",equalTo( 2)); //"title" is "et itaque necessitatibus maxime molestiae qui quas velit",
        //Tek bir body icerisinde coklu assert yapilirsa bu soft assertion seklinde calisir. (Ilk fail durumunda test devam eder.)


    }
}
