package test_data;

import java.util.HashMap;
import java.util.Map;

public class BookerTestData {
    public static Map<String, String> bookingdatesMap(String checkin,String checkout){
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin",checkin);
        bookingdates.put("checkout",checkout);
        return bookingdates;
    }
    public static Map<String, Object> expectedDataMap (String firstname,String lastname,Integer totalprice, Boolean depositpaid,Map<String, String> bookingdates,String additionalneeds  ){
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname",firstname);
        expectedData.put("lastname",lastname);
        expectedData.put("totalprice",totalprice);
        expectedData.put("depositpaid",depositpaid);
        expectedData.put("bookingdates",bookingdates);
        expectedData.put("additionalneeds",additionalneeds);
        return expectedData;
    }


    /*
    Map<String,String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2023-03-07");
        bookingdates.put("checkout", "2024-09-25");
        System.out.println("bookingdates = " + bookingdates);

    Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname","John");
        expectedData.put("lastname","Doe");
        expectedData.put("totalprice",471);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdates);
        expectedData.put("additionalneeds","Lunch");
        */



}
