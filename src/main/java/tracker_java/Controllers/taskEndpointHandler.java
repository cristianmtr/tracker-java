package tracker_java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.hibernate.Query;
import org.hibernate.Session;
import tracker_java.Models.HibernateUtil;
import tracker_java.Models.Item;
import tracker_java.Utilities.JsonResponseHandler;
import tracker_java.Utilities.errorHandler;

import java.util.HashMap;
import java.util.List;

/**
 * first checks what the URI is;
 * if /task/<id>
 *     just returns the json representation of the task of that id
 *  if /task/<id>/comments
 *      return json of comments
 *  if /task/<id>/history
 *      return json of history
 */
public class taskEndpointHandler implements HttpHandler {

    public void handleComments(HttpExchange httpExchange, int taskId) {

    }

    public void handleHistory(HttpExchange httpExchange, int taskId) {

    }

    public void handleTask(HttpExchange httpExchange, int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query query = s.createQuery("from Item where itemId = :taskId").setParameter("taskId", taskId);
        List list = query.list();
        if (list.size() > 1) {
            System.out.format("ERROR: 500: MORE THAN ONE TASK WITH SAME ID: %d%n", taskId);
            errorHandler.INSTANCE.returnServerError(httpExchange);
        }
        else {
            Item theTask = (Item)list.get(0);
            HashMap response = new HashMap();
            response.put("code", "200");
            response.put("data", theTask);
            JsonResponseHandler.INSTANCE.replyWithJsonFromObject(httpExchange, response);
        }
    }

    public void handle(HttpExchange httpExchange){
        System.out.println("request at /task");
        String[] fullURISplit = httpExchange.getRequestURI().toString().split("/");
        int taskId = 0;

        try {
            taskId = Integer.parseInt(fullURISplit[2]);
        }
        catch (NumberFormatException e) {
            System.out.format("task passed via URL was not appropriate int: %s", fullURISplit[2]);
            errorHandler.INSTANCE.returnBadRequest(httpExchange);
        }

        System.out.format("taskId = %d%n", taskId);
        switch (fullURISplit[(fullURISplit).length - 1]) {
            case "comments":
                // handle comments
                System.out.println("// handle comments");
                handleComments(httpExchange, taskId);
                break;
            case "history":
                // handle history
                System.out.println("// handle history");
                handleHistory(httpExchange, taskId);
                break;
            default:
                // handle task itself
                System.out.println("// handle task itself");
                handleTask(httpExchange, taskId);
                break;
        }


    }
}
