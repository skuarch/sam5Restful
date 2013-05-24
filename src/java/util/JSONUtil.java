package util;

import java.text.ParseException;
import org.json.JSONObject;

/**
 *
 * @author skuarch
 */
public class JSONUtil {

    //==========================================================================
    private JSONUtil() {
    }

    //==========================================================================
    public static String getJSONError() {
        return "{\"error\":\"remote server has a problems\"}";
    }

    //==========================================================================
    public static String getJSONErrorAppend(String text) {
        return "{\"error\":\"" + text + "\"}";
    }

    //==========================================================================
    public static JSONObject stringToJSONObject(String string) throws ParseException {

        if (string == null) {
            throw new NullPointerException("string is null");
        }

        return new JSONObject(string);

    } // end stringToJSONObject
} // end class