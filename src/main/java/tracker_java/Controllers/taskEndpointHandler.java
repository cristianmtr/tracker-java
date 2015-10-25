package tracker_java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.glassfish.jersey.internal.util.PropertiesClass;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;
import tracker_java.Models.HibernateUtil;
import tracker_java.Models.Item;
import tracker_java.Models.ItemComment;
import tracker_java.Models.ItemStatus;
import tracker_java.Utilities.JsonResponseHandler;
import tracker_java.Utilities.errorHandler;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handlePostNewTask(Item jsonObject) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Integer newId = (Integer)s.save(jsonObject);
        s.flush();
        tx.commit();
        s.close();
        HashMap res = new HashMap();
        res.put("id", newId);
        return Response.status(201).entity(JsonResponseHandler.INSTANCE.JsonFromObject(res)).build();
    }

    @Path("{id}/comments")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleGetComments(@PathParam("id") int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("from ItemComment where id = :taskId").setParameter("taskId", taskId);
        return Response.status(200).entity(JsonResponseHandler.INSTANCE.JsonFromObject(q.list())).build();
    }

    @Path("{id}/history")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleGetHistory(@PathParam("id") int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("from ItemStatus where id = :taskId").setParameter("taskId", taskId);
        return Response.status(200).entity(JsonResponseHandler.INSTANCE.JsonFromObject(q.list())).build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleGetTask(@PathParam("id") int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query query = s.createQuery("from Item where id = :taskId").setParameter("taskId", taskId);
        List list = query.list();
        if (list.size() > 1)
        {
            System.out.format("WARNING: MORE THAN ONE TASK WITH ID %d FOUND", taskId);
        }
        Item theOne = (Item) list.get(0);
        return Response.status(200).entity(JsonResponseHandler.INSTANCE.JsonFromObject(theOne)).build();
//        return theOne;
    }
}
