package requests.Homeworks;

import base_urls.BookerBaseUrl;
import org.testng.annotations.Test;

public class HW12 extends BookerBaseUrl {
    /*
Write an automation test to test all endpoints using the documentation
available at https://restful-booker.herokuapp.com/apidoc/index.html.
*/
    @Test
    void HW12(){
        //Set the url
        spec.pathParams("first","apidoc","second","index.html");

        //Set the expected data

}
}

