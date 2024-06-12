package contactList_contact;

import base_urls.ContactListBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.ContactPojo;
import utilities.ObjectMapperUtils;

import static contactList_contact.R01_CreateContact.expectedData;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R03_UpdateContact extends ContactListBaseUrl {
    /*
Given
    https://thinking-tester-contact-list.herokuapp.com/contacts/
When
    I send the put request
    Body should be like:
                        {
                            "firstName": "Amy",
                            "lastName": "Miller",
                            "birthdate": "1992-02-02",
                            "email": "amiller@fake.com",
                            "phone": "8005554242",
                            "street1": "13 School St.",
                            "street2": "Apt. 5",
                            "city": "Washington",
                            "stateProvince": "QC",
                            "postalCode": "A1B2D4",
                            "country": "Canada"
                        }
Then
    I get the response body like:
                                {
                                    "_id": "6085a221fcfc72405667c3d4",
                                    "firstName": "Amy",
                                    "lastName": "Miller",
                                    "birthdate": "1992-02-02",
                                    "email": "amiller@fake.com",
                                    "phone": "8005554242",
                                    "street1": "13 School St.",
                                    "street2": "Apt. 5",
                                    "city": "Washington",
                                    "stateProvince": "QC",
                                    "postalCode": "A1A1A1",
                                    "country": "Canada"
                                    "owner": "6085a21efcfc72405667c3d4",
                                    "__v": 0
                                }
And
    Status code is 200
     */

    @Test
    void updateContactTest(){
        // Set the url
        spec.pathParams("first","contacts");

        //Set the expected data
        String json = """
                    {
                        "firstName": "Amy",
                        "lastName": "Miller",
                        "birthdate": "1992-02-02",
                        "email": "amiller@fake.com",
                        "phone": "8005554242",
                        "street1": "13 School St.",
                        "street2": "Apt. 5",
                        "city": "Washington",
                        "stateProvince": "QC",
                        "postalCode": "A1B2D4",
                        "country": "Canada"
                    }
                """;
        expectedData = ObjectMapperUtils.jsonToJava(json,ContactPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the put request and get the response
        Response response = given(spec).body(expectedData).put("{first}");
        response.prettyPrint();

        //Do assertion
        assertEquals(response.statusCode(),200);
    }
}
