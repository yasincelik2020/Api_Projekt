package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static contactList_user.R01_CreateUser.actualData;
import static contactList_user.R01_CreateUser.expectedData;
import static io.restassured.RestAssured.given;

public class Authentication {
    public static String generateToken() {
        String json = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }
                """;
        Response response = given().body(json).contentType(ContentType.JSON).post("https://restful-booker.herokuapp.com/auth");
//     response.prettyPrint();

        return response.jsonPath().getString("token"); // response icerisinde gelen token'i jsonpath ile String formatinda aldik.
    }

    public static String contactListToken() {
        Map<String, String> payload = new HashMap<>();
        if (expectedData != null) { //Eger user create edilirse bu user ile token alinir.
            payload.put("email", expectedData.getEmail());
            payload.put("password", expectedData.getPassword());
        } else { //User olusturulmadiysa clarusway kullanicisi ile token alinacak.
            payload.put("email", "michealsurf@gmail.com");
            payload.put("password", "micheal.123");
        }

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("https://thinking-tester-contact-list.herokuapp.com/users/login");
        try { // Default olarak token alinamadigi taktirde exception'i handle etmek icin kulaniyoruz.
            return response.jsonPath().getString("token");
        } catch (Exception e) {
            System.err.println("Token alinamadi.");
            return "Token alinamadi.";
        }

    }

    public static String contactListToken2() {

        if (actualData != null) {//Eğer user create edilirse bu use'ın direkt token'ı alınabilir
            return actualData.getToken();//Bu user'ın token'ını dön

        } else {
            Map<String, String> payload = new HashMap<>();
            payload.put("email", "michealsurf@gmail.com");
            payload.put("password", "micheal.123");

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .post("https://thinking-tester-contact-list.herokuapp.com/users/login");
            //response.prettyPrint();
            try { // Default olarak token alinamadigi taktirde exception'i handle etmek icin kulaniyoruz.
                return response.jsonPath().getString("token");
            } catch (Exception e) {
                System.err.println("Token alinamadi.");
                return "Token alinamadi.";
            }
        }
    }
}



