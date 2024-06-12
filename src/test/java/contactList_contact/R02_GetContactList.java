package contactList_contact;

import base_urls.ContactListBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.ContactResponsePojo;

import java.util.List;

import static contactList_contact.R01_CreateContact.actualUpdateData;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R02_GetContactList extends ContactListBaseUrl {
    /*
    Given
        https://thinking-tester-contact-list.herokuapp.com/contacts
        --header 'Authorization: Bearer {{token}}'
    When
        I send the get request
    Then
        status code is 200
    And
        Response body should be like:


     */

    @Test
    void getContactListTest() throws JsonProcessingException {
        // Set the url
        spec.pathParams("first","contacts");

        //Send the get request and get the response
        Response response = given(spec).get("{first}");
//        response.prettyPrint();

        List<ContactResponsePojo> toDoList =new ObjectMapper().readValue(response.asString(), new TypeReference<>() {});
        ContactResponsePojo actualData = new ContactResponsePojo();

        for (ContactResponsePojo w : toDoList) {
            if (w.getOwner().equalsIgnoreCase(actualUpdateData.getOwner())){
                actualData = w;
            }
        }

        //Do the assertion
        assertEquals(response.statusCode(),200);
        assertEquals(actualData.getFirstName(),actualUpdateData.getFirstName());
        assertEquals(actualData.getLastName(),actualUpdateData.getLastName());
        assertEquals(actualData.getBirthdate(),actualUpdateData.getBirthdate());
        assertEquals(actualData.getEmail(),actualUpdateData.getEmail());
        assertEquals(actualData.getPhone(),actualUpdateData.getPhone());
        assertEquals(actualData.getStreet1(),actualUpdateData.getStreet1());
        assertEquals(actualData.getStreet2(),actualUpdateData.getStreet2());
        assertEquals(actualData.getCity(),actualUpdateData.getCity());
        assertEquals(actualData.getStateProvince(),actualUpdateData.getStateProvince());
        assertEquals(actualData.getPostalCode(),actualUpdateData.getPostalCode());
        assertEquals(actualData.getCountry(),actualUpdateData.getCountry());


    }

}
