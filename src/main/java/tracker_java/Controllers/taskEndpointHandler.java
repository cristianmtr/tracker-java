package tracker_java.Controllers;

import org.glassfish.jersey.server.ContainerRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tracker_java.Models.HibernateUtil;
import tracker_java.Models.ItemEntity;
import tracker_java.Models.ItemcommentEntity;
import tracker_java.Models.ItemstatusEntity;
import tracker_java.Utilities.PermissionRequirements;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
@Path("tasks")
public class taskEndpointHandler{

    @POST
    @Path("{id}/comments/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionRequirements
    public Response handlePostNewComment(final ItemcommentEntity newComment, @Context ContainerRequest request, @PathParam("id") int taskId) {
        newComment.setItemid(taskId);
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Integer newCommentId = (Integer)s.save(newComment);
        s.flush();
        tx.commit();
        s.close();
        HashMap res = new HashMap();
        res.put("id", newCommentId);
        return Response.status(201).entity(res).build();

    }

    @Path("{id}/history/new")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermissionRequirements
    public Response handlePostHistory(@PathParam("id") int taskId, ItemstatusEntity newStatus, @Context ContainerRequest request) {
        newStatus.setItemid(taskId);
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Long newId = (Long)s.save(newStatus);
        s.flush();
        tx.commit();
        s.close();
        HashMap res = new HashMap();
        res.put("id", newId);
        return  Response.status(201).entity(res).build();
    }

    @Path("{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionRequirements
    public Response handleUpdateTask(@PathParam("id") int taskId, ItemEntity newTask, @Context ContainerRequest request) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        newTask.setId(taskId);
        s.merge(newTask);
        s.flush();
        tx.commit();
        s.close();
        return Response.status(201).build();
    }

    @Path("new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionRequirements
    public Response handlePostNewTask(ItemEntity jsonObject, @Context ContainerRequest request) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Integer newId = (Integer)s.save(jsonObject);
        s.flush();
        tx.commit();
        s.close();
        HashMap res = new HashMap();
        res.put("id", newId);
        return Response.status(201).entity(res).build();
    }

    @Path("{id}/comments")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionRequirements
    public Response handleGetComments(@Context ContainerRequest request,@PathParam("id") int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("from ItemcommentEntity where itemid = :taskId").setParameter("taskId", taskId);
        List theList = q.list();
        tx.commit();
        s.close();
        return Response.status(200).entity(theList).build();
    }

    @Path("{id}/history")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionRequirements
    public Response handleGetHistory(@Context ContainerRequest request,@PathParam("id") int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("from ItemstatusEntity  where itemid = :taskId").setParameter("taskId", taskId);
        List theList = q.list();
        s.close();
        return Response.status(200).entity(theList).build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermissionRequirements
    public Response handleGetTask(@Context ContainerRequest request,@PathParam("id")int taskId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query theOneq = s.createQuery("from ItemEntity where id = :taskId").setParameter("taskId", taskId);
        ItemEntity theOne = (ItemEntity) theOneq.list().get(0);
        s.close();
        if (theOne != null ) {
            return Response.status(200).entity(theOne).build();
        }
        else {
            return Response.status(404).build();
        }
    }
}
