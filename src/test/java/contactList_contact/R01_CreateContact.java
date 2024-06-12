package contactList_contact;

import base_urls.ContactListBaseUrl;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.ContactPojo;
import pojos.ContactResponsePojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R01_CreateContact extends ContactListBaseUrl {
    /*
Given
    https://thinking-tester-contact-list.herokuapp.com/contacts
When
    curl --location 'https://thinking-tester-contact-list.herokuapp.com/contacts' \
--header 'Authorization: Bearer {{token}}' \
--data-raw '{
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
    "country": "USA"
}'
Then
    Status code is 201
And
    Response code should be like:
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
    public static ContactResponsePojo actualUpdateData;
    public static ContactPojo expectedData;

    @Test
    void createContactTest(){
        // Set the url
        spec.pathParams("first","contacts");

        //Set the expected data
        String json = """
                {
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
                    "country": "USA"
                }
                """;
        expectedData = ObjectMapperUtils.jsonToJava(json,ContactPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Sent the post request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        ContactResponsePojo actualData = response.as(ContactResponsePojo.class);
        System.out.println("actualData = " + actualData);

        //Do the assertion
        assertEquals(response.statusCode(),201);
        assertEquals(actualData.getFirstName(),expectedData.getFirstName());
        assertEquals(actualData.getLastName(),expectedData.getLastName());
        assertEquals(actualData.getBirthdate(),expectedData.getBirthdate());
        assertEquals(actualData.getEmail(),expectedData.getEmail());
        assertEquals(actualData.getPhone(),expectedData.getPhone());
        assertEquals(actualData.getStreet1(),expectedData.getStreet1());
        assertEquals(actualData.getStreet2(),expectedData.getStreet2());
        assertEquals(actualData.getCity(),expectedData.getCity());
        assertEquals(actualData.getStateProvince(),expectedData.getStateProvince());
        assertEquals(actualData.getPostalCode(),expectedData.getPostalCode());
        assertEquals(actualData.getCountry(),expectedData.getCountry());

        actualUpdateData=actualData;


    }
}
