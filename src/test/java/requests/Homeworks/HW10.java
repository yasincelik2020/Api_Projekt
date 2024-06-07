package requests.Homeworks;

import base_urls.PetstoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.PetStorePojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HW10 extends PetstoreBaseUrl {
    /*
Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
Note: Use POJO payload
https://petstore.swagger.io/v2/user
{
  "id": 0,
  "username": "string",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string",
  "phone": "string",
  "userStatus": 0
}
*/
@Test
    void HW10(){
    //Set the url
    spec.pathParams("first","v2","second","user");

    //Set the payload
    PetStorePojo expectedData = new PetStorePojo(1,"Ali","Alis","Geldi","asd@gamil.com","123","345432122",0);

    // Send the expected data and get the response
    Response response = given(spec).body(expectedData).post("{first}/{second}");
    response.prettyPrint();

    PetStorePojo actualData = response.as(PetStorePojo.class);
    System.out.println("actualData = " + actualData);

    //Do assertion
    assertEquals(response.statusCode(), 200);



}

}
