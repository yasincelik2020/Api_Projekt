package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {
    //Jenerik methodlarda T kulaniyoruz
    //Bu metod ile json datayi java objeisne ceviriyoruz.
    //ObjectMapper kullanirken atilan exception'i burada try-catch ile hallediyoruz.
    public static <T> T jsonToJava(String json,Class<T> tclass) { // Generic metod
        try {
           return new ObjectMapper().readValue(json,tclass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
