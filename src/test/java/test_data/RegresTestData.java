package test_data;

import java.util.HashMap;
import java.util.Map;

public class RegresTestData {
    public static Map<String, Object> expectedTestData(String name, String job) {
        Map<String, Object> expectedData = new HashMap<>();
        if (name !=null){
            expectedData.put("name", name);
        }
        if (job !=null){
            expectedData.put("job", job);
        }
        return expectedData;
    }
}
