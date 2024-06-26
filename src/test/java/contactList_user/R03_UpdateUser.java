package contactList_user;

import base_urls.ContactListBaseUrl;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.contactListPojo.User;
import pojos.contactListPojo.UserPojo;
import utilities.ObjectMapperUtils;

import java.util.Map;


import static contactList_user.R01_CreateUser.expectedData;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;




public class R03_UpdateUser extends ContactListBaseUrl {
    /*
    Given
        https://thinking-tester-contact-list.herokuapp.com/users/me
        'Authorization: Bearer {{token}}'
    When
        User sends patch request
                        '{
                            "firstName": "Mary",
                            "lastName": "Star",
                            "email": "test2@fake.com",
                            "password": "Mary.123"
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
        String json = """
                {
                    "firstName": "Mary",
                    "lastName": "Star",
                    "email": "test2@fake.com",
                    "password": "Mary.123"
                }
                """;
       expectedData= ObjectMapperUtils.jsonToJava(json,UserPojo.class);
       expectedData.setEmail(Faker.instance().internet().emailAddress());
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

        actualDataUpdate = response.as(User.class);

        // Do Assertion
        assertEquals(response.statusCode(),200);
        assertEquals(actualDataUpdate.getFirstName(),expectedData.getFirstName());
        assertEquals(actualDataUpdate.getLastName(),expectedData.getLastName());
        assertEquals(actualDataUpdate.getEmail(),expectedData.getEmail());



    }
}
