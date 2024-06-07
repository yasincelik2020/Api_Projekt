package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class AutomationBaseUrl {

    protected RequestSpecification spec; //Her requestte yapilacak tekrarli islemler bir kez buraya alinarak tekrardan sakinilir.

    @BeforeMethod
    public void setSpec() {

        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://automationexercise.com")
                .build();
    }
}
