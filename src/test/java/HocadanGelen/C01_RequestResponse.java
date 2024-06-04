package HocadanGelen;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class C01_RequestResponse {

    public static void main(String[] args) {

        //Ilgili Url'e get request gönder:
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/11");

        //Dönen response'ı güzelce yazdır:
        response.prettyPrint();

        //Status code'u yazdır:
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

        //ContentType'ı yazdır:
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);

        //Statusline'ı yazdır:
        String statusLine = response.statusLine();
        System.out.println("statusLine = " + statusLine);

        //Header değerlerini bireysel olarak yazdır:
        String server = response.getHeader("Server");
        System.out.println("server = " + server);

        String date = response.getHeader("Date");
        System.out.println("date = " + date);

        //Tüm headers yazdır:
        Headers headers = response.getHeaders();
        System.out.println("\nheaders = \n" + headers);

    }

}
