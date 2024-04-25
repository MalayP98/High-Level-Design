package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    private Constants(){}

    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String LOG_STARS = "****************";

    public static Map<String, Object> DEFAULT_SUCCESS_RESPONSE = new HashMap<String, Object>(){{put("response", "success");}};

    public static Map<String, Object> DEFAULT_FAILURE_RESPONSE = new HashMap<String, Object>(){{put("response", "fail");}};

}
