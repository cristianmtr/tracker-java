package tracker_java;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.hibernate.Session;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cristian on 10/4/15.
 */
public class jsonHandler implements HttpHandler {

    private List getAllTasks() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List tasks = session.createQuery("from TaskObject ").list();
//        for (TaskObject task: (List<TaskObject>) tasks){
//            task.DT_RowId = task.ID;
//        }
        session.close();
        return tasks;
    }

    private HashMap getDataSources(){
        HashMap dataSources = new HashMap();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List membersList = session.createQuery("from MemberObject").list();
        session.flush();
        session.close();

        HashMap members = new HashMap();
        for (MemberObject member : (List<MemberObject>) membersList) {
            members.put(member.id, member.name);
        }

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List tasklistsList = session.createQuery("from TasklistObject ").list();
        session.close();

        HashMap tasklists = new HashMap();
        for (TasklistObject tasklist : (List<TasklistObject>)tasklistsList) {
            tasklists.put(tasklist.id, tasklist.name);
        }

        HashMap priorities = new HashMap();
        priorities.put(1, "Urgent");
        priorities.put(2, "High Priority");
        priorities.put(3, "Medium");
        priorities.put(4, "Normal");
        priorities.put(5, "Low priority");
        priorities.put(6, "Low priority");
        priorities.put(7, "Very low priority");
        priorities.put(8, "Very low priority");
        priorities.put(9, "Whatever");

        dataSources.put("priority", priorities);
        dataSources.put("tasklist", tasklists);
        dataSources.put("responsible", members);

        return dataSources;
    }

    @Override
    public void handle(HttpExchange httpExchange) {
        System.out.println("request at /json");

        List tasks = this.getAllTasks();
        HashMap dataSources = this.getDataSources();

        HashMap result = new HashMap();
        result.put("data", tasks);
        result.put("dataSources", dataSources);

        com.fasterxml.jackson.databind.ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String resultAsJson = null;
        try {
            resultAsJson = ow.writeValueAsString(result);
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