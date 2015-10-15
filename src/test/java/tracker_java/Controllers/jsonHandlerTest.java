package tracker_java.Controllers;

import junit.framework.TestCase;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by cristian on 10/15/15.
 */
public class jsonHandlerTest extends TestCase {
    public void testJsonObject() {
        JSONObject jObject = new JSONObject("{\n" +
                "   \"http://http://url.com/\": {\n" +
                "      \"id\": \"http://http://url.com//\"\n" +
                "   },\n" +
                "   \"http://url2.co/\": {\n" +
                "      \"id\": \"http://url2.com//\",\n" +
                "      \"shares\": 16\n" +
                "   }\n" +
                "   ,\n" +
                "   \"http://url3.com/\": {\n" +
                "      \"id\": \"http://url3.com//\",\n" +
                "      \"shares\": 16\n" +
                "   }\n" +
                "}".trim());
        Iterator<?> keys = jObject.keys();

        while (keys.hasNext()) {
            String key = (String) keys.next();

            System.out.println(jObject.get(key));
        }

    }
}