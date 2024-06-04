package requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


public class D02_AssertHeader {

    /*
   Given
       https://restful-booker.herokuapp.com/booking/11
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
   And
       Server should be "Cowboy"
   And
       Connection should be "keep-alive"

*/
    @Test
    public void assertHeaderTest(){

        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/11");

        response.prettyPrint();

        response
                .then() // ValidateableResponse döner. (TestNG assertion kullanmadan)
                .statusCode(200) //Statuscode should be 200
                .contentType("application/json") //Content Type should be JSON
                .statusLine("HTTP/1.1 200 OK") //Status Line should be HTTP/1.1 200
                .header("Server",equalTo("Cowboy")) //Server should be "Cowboy"
                .header("Connection",equalTo("keep-alive")) //Connection should be "keep-alive"
        ;


    }
}
