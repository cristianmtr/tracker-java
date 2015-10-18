package tracker_java.Utilities;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * returns error codes to client
 * see: https://developer.mozilla.org/en-US/docs/Web/HTTP/Response_codes
 */
public enum errorHandler {
    INSTANCE;

    public void returnBadRequest(HttpExchange httpExchange) {
        Headers headers = httpExchange.getResponseHeaders();
        headers.add("Content-type","text/json");
        String response = "{\"code\":\"400\"}";
        try {
            httpExchange.sendResponseHeaders(400, response.getBytes().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream res = httpExchange.getResponseBody();
        try {
            res.write(response.getBytes());
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

    public void returnServerError(HttpExchange httpExchange) {
        Headers headers = httpExchange.getResponseHeaders();
        headers.add("Content-type","text/json");
        String response = "{\"code\":\"500\"}";
        try {
            httpExchange.sendResponseHeaders(500, response.getBytes().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream res = httpExchange.getResponseBody();
        try {
            res.write(response.getBytes());
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
}

