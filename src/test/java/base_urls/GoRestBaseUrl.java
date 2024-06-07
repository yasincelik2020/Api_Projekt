package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class GoRestBaseUrl {
    protected RequestSpecification spec;

    @BeforeMethod
    public void setPec(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v1")
                .setContentType(ContentType.JSON)
                .build();
    }
}
