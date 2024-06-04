package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    // Bu method ile test classinda Map data tipinde bir payload olusturuyoruz.
    public static Map<String, Object> expectedDataMap(Integer userId,String title, Boolean completed) {
        Map<String, Object> expectedData = new HashMap<>();
        if (userId!=null){ // Eger userId parametre olarak null girilirse map'te bu field bulunmaz.
            expectedData.put("userId", userId);
        }
        if (title !=null){
            expectedData.put("title", title);
        }
        if (completed !=null){
            expectedData.put("completed", completed);
        }

        return expectedData;
    }
}
