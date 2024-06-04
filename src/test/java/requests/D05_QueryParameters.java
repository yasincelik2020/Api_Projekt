package requests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class D05_QueryParameters {


    @Test
    void queryParamTest(){

//    API testinde izlenecek adimlar:
//    1.Set the ur l
    String url = "https://restful-booker.herokuapp.com/booking?firstname=Jane&lastname=Doe";

//    2.Set the expected data

//    3.Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();
//    4.Do assertion
        // 1.yol
        response
                .then()
                .statusCode(200)
                .body("bookingid",hasSize(greaterThan(0))) // Tavsiye edilen yöntem
        ;
        // 2.Yol:
        assert response.asString().contains("bookingid"); //Java assertion


        JsonPath jsonPath = response.jsonPath();
        String date = jsonPath.getString("bookingdates.checkin");
        System.out.println("date = " + date);


    }
}
