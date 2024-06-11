package contactList_user;

import base_urls.ContactListBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class R06_DeleteUser extends ContactListBaseUrl {
/*
https://thinking-tester-contact-list.herokuapp.com/users/me
 */

    @Test
    void testDelete(){
        spec.pathParams("first","users","second","me");
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

    }
}
