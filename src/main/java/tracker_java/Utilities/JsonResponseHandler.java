package tracker_java.Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * returns JSON replies from the specified Object
 */
public enum JsonResponseHandler {
    INSTANCE;

    public void replyWithJsonFromObject(HttpExchange httpExchange, Object theObject) {
        com.fasterxml.jackson.databind.ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String resultAsJson = null;
        try {
            resultAsJson = ow.writeValueAsString(theObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (resultAsJson != null) {
            Headers headers = httpExchange.getResponseHeaders();
            headers.add("Content-type", "text/json");

            try {
                httpExchange.sendResponseHeaders(200, resultAsJson.getBytes().length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            OutputStream res = httpExchange.getResponseBody();
            try {
                System.out.format("Replying with %s", resultAsJson);
                res.write(resultAsJson.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpExchange.close();
        }
        else {
            System.out.format("ERROR 500: resultAsJson was null when requesting %s%n", httpExchange.getRequestURI().toString());
            errorHandler.INSTANCE.returnServerError(httpExchange);
        }
    }
}