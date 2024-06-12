package contactList_user;

import base_urls.ContactListBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R06_DeleteUser extends ContactListBaseUrl {
/*
Given
    https://thinking-tester-contact-list.herokuapp.com/users/me
When
    User sends delete request
Then
    Status code is 200
And
    This request should not return any response body
 */

    @Test
    void testDelete(){
        //Set the url
        spec.pathParams("first","users","second","me");

        //Send the expected data

        //Send the delete request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(response.statusCode(), 200);
        assert response.asString().isEmpty();






    }
}
