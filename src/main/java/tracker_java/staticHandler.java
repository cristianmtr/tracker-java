package tracker_java;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by cristian on 10/4/15.
 */
public class staticHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("request at /static");

        String filePath = httpExchange.getRequestURI().toString();
        filePath = filePath.replaceFirst("/", "");
        String mimeTypeExtension = filePath.substring(filePath.lastIndexOf("."), filePath.length());

        System.out.format("requesting %s %n", filePath);

        Headers headers = httpExchange.getResponseHeaders();
        if (mimeTypeExtension.equals(".css")) {
            System.out.println("css!");
            headers.add("Content-type", "text/css");
        }
        else if (mimeTypeExtension.equals(".js")) {
            headers.add("Content-type", "application/javascript");
        }
        else if (mimeTypeExtension.equals(".ico")) {
            headers.add("Content-type", "image/png");
        }

        String data = new StringFromFilePathReader(filePath).getData();

        httpExchange.sendResponseHeaders(200, data.getBytes().length);
        OutputStream res = httpExchange.getResponseBody();
        res.write(data.getBytes());
        res.close();
        httpExchange.close();

    }
}
