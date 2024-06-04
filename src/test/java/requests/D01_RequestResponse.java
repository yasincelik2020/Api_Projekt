package requests;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class D01_RequestResponse {
    public static void main(String[] args) {
        //Ilgili URL'e get request gönder:
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/11");

        //Dönen response 'u güzelce yazdir:
        response.prettyPrint();

        //Status code'u yazdirir:
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

        //ContentType'i yazdir:
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);

        //Statusline'i yazdir.
        String statusLine = response.statusLine();
        System.out.println("statusLine = " + statusLine);

        //Header degerlerini bireysel olarak yazdir.
        String server = response.getHeader("Server");
        System.out.println("server = " + server);

        String date = response.getHeader("Date");
        System.out.println("date = " + date);

        //Tüm headerlari yazdir.
        Headers headers = response.getHeaders();
        System.out.println("\nheaders = \n" + headers);
        System.out.println("headers.size() = " + headers.size());

        //Bu sekilde de headers'dan verileri cekebiliriz.
        System.out.println("headers.get(\"Date\") = " + headers.get("Date"));

    }
    }
