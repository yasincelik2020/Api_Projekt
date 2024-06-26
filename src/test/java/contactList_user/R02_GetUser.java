package contactList_user;

import base_urls.ContactListBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.contactListPojo.User;

import static contactList_user.R01_CreateUser.createdUser;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R02_GetUser extends ContactListBaseUrl {
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

        //Set the expected data --> Bir onceki class'ta olusturulan User objesi kullanilabilir.
        User expectedData = createdUser;


        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        User actualData = response.as(User.class);
        assertEquals(response.statusCode(),200);
        System.out.println("actualData = " + actualData);
//        assertEquals(actualData.getFirstName(),expectedData.getFirstName());
//        assertEquals(actualData.getLastName(),expectedData.getLastName());


    }
}
