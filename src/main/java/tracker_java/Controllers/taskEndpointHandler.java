package tracker_java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.hibernate.Query;
import org.hibernate.Session;
import tracker_java.Models.HibernateUtil;
import tracker_java.Models.Item;
import tracker_java.Utilities.JsonResponseHandler;
import tracker_java.Utilities.errorHandler;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * first checks what the URI is;
 * if /task/<id>
 * just returns the json representation of the task of that id
 * if /task/<id>/comments
 * return json of comments
 * if /task/<id>/history
 * return json of history
 */
public class taskEndpointHandler implements HttpHandler {

    private void handlePostComments(HttpExchange httpExchange, int taskId) {
        String res = "post comment";
        JsonResponseHandler.INSTANCE.replyWithJsonFromObject(httpExchange, res);
    }

    private void handlePostHistory(HttpExchange httpExchange, int taskId) {
        String res = "post history";
        JsonResponseHandler.INSTANCE.replyWithJsonFromObject(httpExchange, res);
    }

    private void handleUpdateTask(HttpExchange httpExchange, int taskId) {
        String res = "update existing";
        JsonResponseHandler.INSTANCE.replyWithJsonFromObject(httpExchange, res);
    }

    private void handlePostNewTask(HttpExchange httpExchange) {
        String res = "post new";
        JsonResponseHandler.INSTANCE.replyWithJsonFromObject(httpExchange, res);
    }

    public void handleGetComments(HttpExchange httpExchange, int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("from ItemComment where itemId = :taskId").setParameter("taskId", taskId);
        List l = q.list();
        HashMap response = new HashMap();
        response.put("code", "200");
        response.put("data", l);
        JsonResponseHandler.INSTANCE.replyWithJsonFromObject(httpExchange, response);
    }

    public void handleGetHistory(HttpExchange httpExchange, int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("from ItemStatus where itemId = :taskId").setParameter("taskId", taskId);
        List l = q.list();
        HashMap response = new HashMap();
        response.put("code", "200");
        response.put("data", l);
        JsonResponseHandler.INSTANCE.replyWithJsonFromObject(httpExchange, response);
    }

    public void handleGetTask(HttpExchange httpExchange, int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query query = s.createQuery("from Item where itemId = :taskId").setParameter("taskId", taskId);
        List list = query.list();
        if (list.size() > 1) {
            System.out.format("ERROR: 500: MORE THAN ONE TASK WITH SAME ID: %d%n", taskId);
            errorHandler.INSTANCE.returnServerError(httpExchange);
        } else {
            Item theTask = (Item) list.get(0);
            HashMap response = new HashMap();
            response.put("code", "200");
            response.put("data", theTask);
            JsonResponseHandler.INSTANCE.replyWithJsonFromObject(httpExchange, response);
        }
    }

    public void handleGet(HttpExchange httpExchange) {
        String[] fullURISplit = httpExchange.getRequestURI().toString().split("/");
        String matchedCase = UrlMatcher.INSTANCE.match(httpExchange.getRequestURI().toString());
        if (matchedCase == null) {
            System.out.format("unknown request path: %s%n", httpExchange.getRequestURI().toString());
            errorHandler.INSTANCE.returnBadRequest(httpExchange);
        } else {
            int taskId = Integer.parseInt(fullURISplit[2]);
            System.out.format("taskId = %d%n", taskId);
            switch (matchedCase) {
                case "/task/id/comments":
                    // handle comments
                    System.out.println("// handle comments");
                    handleGetComments(httpExchange, taskId);
                    break;
                case "/task/id/history":
                    // handle history
                    System.out.println("// handle history");
                    handleGetHistory(httpExchange, taskId);
                    break;
                case "/task/id":
                    // handle task itself
                    System.out.println("// handle task itself");
                    handleGetTask(httpExchange, taskId);
                    break;
            }
        }
    }

    /*
    POST /task - new task with auto ID generation
    POST /task/<id> - update task with ID <id> with new info
    POST /task/<id>/comment - post comment to task with id
    POST /task/<id>/history - post status change to task with id
     */
    public void handlePost(HttpExchange httpExchange) {
        String[] fullURISplit = httpExchange.getRequestURI().toString().split("/");
        String matchedCase = UrlMatcher.INSTANCE.match(httpExchange.getRequestURI().toString());
        if (matchedCase == null) {
            System.out.format("unknown request path: %s%n", httpExchange.getRequestURI().toString());
            errorHandler.INSTANCE.returnBadRequest(httpExchange);
        } else {
            int taskId;
            switch (matchedCase) {
                case "/task/id/comments":
                    // handle comments
                    taskId = Integer.parseInt(fullURISplit[2]);
                    System.out.format("taskId = %d%n", taskId);
                    System.out.println("// handle comments");
                    handlePostComments(httpExchange, taskId);
                    break;
                case "/task/id/history":
                    // handle history
                    taskId = Integer.parseInt(fullURISplit[2]);
                    System.out.format("taskId = %d%n", taskId);
                    System.out.println("// handle history");
                    handlePostHistory(httpExchange, taskId);
                    break;
                case "/task/id":
                    // handle task itself
                    taskId = Integer.parseInt(fullURISplit[2]);
                    System.out.format("taskId = %d%n", taskId);
                    System.out.println("// handle update existing task");
                    handleUpdateTask(httpExchange, taskId);
                    break;
                case "/task":
                    System.out.format("// handle new task%n");
                    handlePostNewTask(httpExchange);
                    break;
            }
        }

    }

    public void handle(HttpExchange httpExchange) {
        System.out.format("%s request at /task%n", httpExchange.getRequestMethod());
        if (httpExchange.getRequestMethod().equals("GET")) {
            this.handleGet(httpExchange);
        } else if (httpExchange.getRequestMethod().equals("POST")) {
            this.handlePost(httpExchange);
        }
    }
}
