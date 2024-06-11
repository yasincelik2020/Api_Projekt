package contactList_user;

import base_urls.ContactListBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class R04_LogOut extends ContactListBaseUrl {
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
