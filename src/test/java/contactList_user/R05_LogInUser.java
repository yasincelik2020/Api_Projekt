package contactList_user;

import base_urls.ContactListBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.contactListPojo.UserResponsePojo;
import utilities.ObjectMapperUtils;

import java.util.Map;

import static contactList_user.R01_CreateUser.token;
import static contactList_user.R03_UpdateUser.*;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R05_LogInUser extends ContactListBaseUrl {
    /*
    curl --location 'https://thinking-tester-contact-list.herokuapp.com/users/login' \
--data-raw '{
    "email": "test2@fake.com",
    "password": "myNewPassword"
}'
     */

    @Test
    void logInUser(){
        //Set the url
        spec.pathParams("first","users","second","login");

        //Set the expected Data
        String json = "{\n" +
                "    \"email\": \""+actualDataUpdate.getEmail()+"\",\n" +
                "    \"password\": \""+password+"\"" +
                "}";

        Map expectedData = ObjectMapperUtils.jsonToJava(json,Map.class);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(response.statusCode(),200);
        UserResponsePojo actualData = response.as(UserResponsePojo.class);
        assertEquals(actualData.getUser().getFirstName(),actualDataUpdate.getFirstName());
        assertEquals(actualData.getUser().getLastName(),actualDataUpdate.getLastName());
        assertEquals(actualData.getUser().getEmail(),actualDataUpdate.getEmail());

        token=actualData.getToken();

    }
}
