package tracker_java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.hibernate.Query;
import org.hibernate.Session;
import tracker_java.Models.HibernateUtil;
import tracker_java.Models.Item;
import tracker_java.Models.ItemComment;
import tracker_java.Models.ItemStatus;
import tracker_java.Utilities.JsonResponseHandler;
import tracker_java.Utilities.errorHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
@Path("task")
public class taskEndpointHandler{

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

    @Path("{id}/comments")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemComment> handleGetComments(@PathParam("id") int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("from ItemComment where itemId = :taskId").setParameter("taskId", taskId);
        List<ItemComment> l = q.list();
        return l;
    }

    @Path("{id}/history")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemStatus> handleGetHistory(@PathParam("id") int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("from ItemStatus where itemId = :taskId").setParameter("taskId", taskId);
        List l = q.list();
        return l;
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Item handleGetTask(@PathParam("id") int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query query = s.createQuery("from Item where itemId = :taskId").setParameter("taskId", taskId);
        List list = query.list();
        if (list.size() > 1)
        {
            System.out.format("WARNING: MORE THAN ONE TASK WITH ID %d FOUND", taskId);
        }
        Item theOne = (Item) list.get(0);
        return theOne;
    }
}
