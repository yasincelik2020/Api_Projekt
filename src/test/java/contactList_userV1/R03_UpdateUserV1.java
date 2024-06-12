package contactList_userV1;


import base_urls.ContactListBaseUrlV1;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.contactListPojo.User;
import utilities.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;




public class R03_UpdateUserV1 extends ContactListBaseUrlV1 {
    /*
    Given
        https://thinking-tester-contact-list.herokuapp.com/users/me
        'Authorization: Bearer {{token}}'
    When
        User sends patch request
                        '{
                            "firstName": "Updated",
                            "lastName": "Username",
                            "email": "test2@fake.com",
                            "password": "myNewPassword"
                        }'
    Then
        Status code should be 200
    And
      Response body shoul be like:
                                    {
                                      "_id": "608b2db1add2691791c04c89",
                                      "firstName": "Updated",
                                      "lastName": "Username",
                                      "email": "test2@fake.com",
                                      "__v": 1
                                    }
     */

    public static String password;
    public static User actualDataUpdate;
    @Test
    void getUserTest() {
        //Set the url
        spec.pathParams("first", "users", "second", "me");
        password=Faker.instance().internet().password();

        //Set the expected data
        String json = "{\n" +
                "    \"firstName\": \"Updated\",\n" +
                "    \"lastName\": \"Username\",\n" +
                "    \"email\": \""+ Faker.instance().internet().emailAddress() +"\",\n"+
                "    \"password\": \""+ password +"\""+
                "}";
        Map expectedData = ObjectMapperUtils.jsonToJava(json, Map.class);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

        actualDataUpdate = response.as(User.class);

        // Do Assertion
        assertEquals(response.statusCode(),200);
        assertEquals(actualDataUpdate.getFirstName(),expectedData.get("firstName"));
        assertEquals(actualDataUpdate.getLastName(),expectedData.get("lastName"));
        assertEquals(actualDataUpdate.getEmail(),expectedData.get("email"));



    }
}
