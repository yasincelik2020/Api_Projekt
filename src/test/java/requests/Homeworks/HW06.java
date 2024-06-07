package requests.Homeworks;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class HW06 {
    @Test
    void HW06() {
//       Given
//              https://reqres.in/api/unknown/
        String url = "https://reqres.in/api/unknown/";
//       When
//            I send GET Request to the URL
        Response response = given().get(url);
        response.prettyPrint();
//       Then
//
//            1)Status code is 200
        response.then().statusCode(200);
        System.out.println("====================Print all pantone_values================");
//            2)Print all pantone_values
//              (Tüm pantone_value değerlerini yazdırınız)
        JsonPath jsonPath = response.jsonPath();
        List<String> pantValues = jsonPath.getList("data.pantone_value");
        System.out.println("pantValues = " + pantValues);
        System.out.println("====================Print all ids greater than 3 on the console================");
//            3)Print all ids greater than 3 on the console
//              (3'ten büyük id'leri yazdırınız)
        List<Integer> ids = jsonPath.getList("data.id");
        int count=0;
        for (int w:ids) {
            if (w>3){count++;
                System.out.println(w);
            }
        }
        System.out.println("====================Assert that there are 3 ids greater than 3================");
//              Assert that there are 3 ids greater than 3
//              (3'ten büyük 3 adet id olduğunu doğrulayınız)
        assertEquals(count,3);
        System.out.println("====================Print all names whose ids are less than 3 on the console================");
//            4)Print all names whose ids are less than 3 on the console
//              (id'si 3'ten küçük isimleri yazdırınız)
        List<String> names = jsonPath.getList("data.name");
        for (int i = 0; i < names.size()-(count+1); i++) {
            System.out.println("names.get("+(i+1)+") = " + names.get(i));
        }
//              Assert that the number of names whose ids are less than 3 is 2
//              (id'si 3'ten küçük 2 isim olduğunu doğrulayınız)
        count=0;
        for (int w:ids) {
            if (w<3){count++;
                System.out.println(w);
            }
        }
        assertEquals(count, 2);

    }
}
