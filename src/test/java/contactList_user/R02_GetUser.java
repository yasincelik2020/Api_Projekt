package contactList_user;

import base_urls.ContactListBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.contactListPojo.User;

import static contactList_user.R01_CreateUser.expectedDataUpdate;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R02_GetUser extends ContactListBaseUrl {
    /*
    Given
        'https://thinking-tester-contact-list.herokuapp.com/users/me'
         'Authorization: Bearer {{token}}
    When
        User sends post request
    Then
        Status code should be 200
    And
         Response body shoul be like:
                                    {
                                      "_id": "6085a221fcfc72405667c3d4",
                                      "firstName": "John",
                                      "lastName": "Doe",
                                      "birthdate": "1970-01-01",
                                      "email": "jdoe@fake.com",
                                      "phone": "8005555555",
                                      "street1": "1 Main St.",
                                      "street2": "Apartment A",
                                      "city": "Anytown",
                                      "stateProvince": "KS",
                                      "postalCode": "12345",
                                      "country": "USA",
                                      "owner": "6085a21efcfc72405667c3d4",
                                      "__v": 0
                                    }

     */

    @Test
    void getUserTest(){
        //Set the url
        spec.pathParams("first","users","second","me");

        //Set the expecteed data


        //send the request and get the response
    Response response = given(spec).get("{first}/{second}");
    response.prettyPrint();

        User actualData = response.as(User.class);
        assertEquals(response.statusCode(),200);
        assertEquals(actualData.getFirstName(),expectedDataUpdate.getFirstName());
        assertEquals(actualData.getLastName(),expectedDataUpdate.getLastName());


    }
}
