package tracker_java.Controllers;

import java.io.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import tracker_java.Utilities.StringFromFilePathReader;

public class rootHandler implements HttpHandler {

    public void handle(HttpExchange arg0) throws IOException {
        System.out.println("request at / ");
        String data = null;
        try {
            data = new StringFromFilePathReader("templates/index.html").getData();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        arg0.sendResponseHeaders(200, data.getBytes().length);
        OutputStream res = arg0.getResponseBody();
        res.write(data.getBytes());
        res.close();
        arg0.close();

    }

}
