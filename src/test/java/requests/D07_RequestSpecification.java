package requests;

import base_urls.BookerBaseUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class D07_RequestSpecification extends BookerBaseUrl {

    @Test
    void requestSpecification(){
//        1.Set the url
        // String url = "https://restful-booker.herokuapp.com/booking?firstname=Jane&lastname=Doe";
        spec.pathParams("first","booking")
                .queryParams("firstname","Jane","lastname","Doe")
        ;

//        2.Set the expected data

//        3.Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

//        4.Do assertion
        response
                .then()
                .statusCode(200)
                .body("bookingid",hasSize(greaterThan(0))); // Tavsiye edilen yöntem
    }
}
