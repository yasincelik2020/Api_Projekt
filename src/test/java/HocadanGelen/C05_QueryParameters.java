package HocadanGelen;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C05_QueryParameters {


    @Test
    void queryParamTest(){

//        1. Set the url
        String url = "https://restful-booker.herokuapp.com/booking?firstname=Jane&lastname=Doe";

//        2. Set the expected data

//        3. Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

//        4. Do assertion
        //1. yol:
        response
                .then()
                .statusCode(200)
                .body("bookingid", hasSize(greaterThan(0)));//Tavsiye edilen yöntem

        //2. Yol:
        assert response.asString().contains("bookingid") : "Body does not contain bookingid";//Java assertion

    }

}
