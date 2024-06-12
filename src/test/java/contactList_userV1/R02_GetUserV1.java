package contactList_userV1;


import base_urls.ContactListBaseUrlV1;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.contactListPojo.User;


import static contactList_userV1.R01_CreateUserV1.expectedDataUpdate;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R02_GetUserV1 extends ContactListBaseUrlV1 {
    /*
    Given
        'https://thinking-tester-contact-list.herokuapp.com/users/me'
         'Authorization: Bearer {{token}}
    When
        User sends get request
    Then
        Status code should be 200
    And
         Response body should be like:
                                    {
                                        "_id": "6667f9710842020013c0b45c",
                                        "firstName": "Michael",
                                        "lastName": "Schuhmacher",
                                        "email": "miles.crist@yahoo.com",
                                        "__v": 1
                                    }
     */

    @Test
    void getUserTest(){
        //Set the url
        spec.pathParams("first","users","second","me");

        //Set the expected data --> Bir onceki class'ta olusturulan


        //send the request and get the response
    Response response = given(spec).get("{first}/{second}");
    response.prettyPrint();

        User actualData = response.as(User.class);
        assertEquals(response.statusCode(),200);
        assertEquals(actualData.getFirstName(),expectedDataUpdate.getFirstName());
        assertEquals(actualData.getLastName(),expectedDataUpdate.getLastName());


    }
}
