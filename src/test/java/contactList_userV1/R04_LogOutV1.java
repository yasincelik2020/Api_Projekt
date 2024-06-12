package contactList_userV1;


import base_urls.ContactListBaseUrlV1;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class R04_LogOutV1 extends ContactListBaseUrlV1 {
    /*
    'https://thinking-tester-contact-list.herokuapp.com/users/logout'
     */
    @Test
    void logOutTest(){
        spec.pathParams("first","users","second","logout");

        Response response = given(spec).post("{first}/{second}");
        response.prettyPrint();
    }
}
