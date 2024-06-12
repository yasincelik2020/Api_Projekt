package contactList_userV1;


import base_urls.ContactListBaseUrlV1;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class R06_DeleteUserV1 extends ContactListBaseUrlV1 {
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
