package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static contactList_userV1.R01_CreateUserV1.token;
import static contactList_userV1.R01_CreateUserV1.token;

public class ContactListBaseUrlV1 {

    protected RequestSpecification spec; //Her requestte yapilacak tekrarli islemler bir kez buraya alinarak tekrardan sakinilir.

    @BeforeMethod
    public void setSpec() {

        spec = new RequestSpecBuilder()
                .addHeader("Authorization ", "Bearer "+ token)
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .setContentType(ContentType.JSON)
                .build();
    }
}
