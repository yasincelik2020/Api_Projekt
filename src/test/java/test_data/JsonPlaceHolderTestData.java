package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    // Bu method ile test classinda Map data tipinde bir payload olusturuyoruz.
    public static Map<String, Object> expectedDataMap(Integer userId,String title, Boolean completed) {
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);
        return expectedData;
    }
}
