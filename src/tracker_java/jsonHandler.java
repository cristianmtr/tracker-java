package tracker_java;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.hibernate.Session;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by cristian on 10/4/15.
 */
public class jsonHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) {
        System.out.println("request at /json");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List tasks = session.createQuery("from TaskObject ").list();
//        List data = null;
//        for (TaskObject task : (List<TaskObject>) tasks) {
//            data.add(task);
//        }
        com.fasterxml.jackson.databind.ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String resultAsJson = null;
        try {
            resultAsJson = ow.writeValueAsString(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Headers headers = httpExchange.getResponseHeaders();
        headers.add("Content-type","text/json");

        try {
            httpExchange.sendResponseHeaders(200, resultAsJson.getBytes().length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        OutputStream res = httpExchange.getResponseBody();
        try {
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
}
