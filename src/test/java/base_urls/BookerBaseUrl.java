package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BookerBaseUrl {

    protected RequestSpecification spec; //Her requestte yapilacak tekrarli islemler bir kez buraya alinarak tekrardan sakinilir.

    @BeforeMethod
    public void setSpec() {

        spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();
    }
}
