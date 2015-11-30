package tracker_java.Controllers;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tracker_java.Models.HibernateUtil;
import tracker_java.Models.ItemEntity;
import tracker_java.Models.ItemcommentEntity;
import tracker_java.Models.ItemstatusEntity;
import tracker_java.Utilities.PermissionsChecker;

import javax.ws.rs.*;
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
    @Path("{id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handlePostNewComment(final ItemcommentEntity newComment, @HeaderParam("Authorization") String authorization, @PathParam("id") int taskId) {
        if (!PermissionsChecker.checkPermissionComment(taskId, authorization)) {
            return Response.status(401).build();
        }
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

    @Path("{id}/history")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handlePostHistory(@PathParam("id") int taskId, @HeaderParam("Authorization") String authorization,ItemstatusEntity newStatus) {
        if (!PermissionsChecker.checkPermissionWrite(taskId, authorization)) {
            return Response.status(401).build();
        }
        newStatus.setItemid(taskId);
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Integer userId = PermissionsChecker.getUserIdFromAuthorization(authorization);
        newStatus.setMemberid(userId);
        Long newId = (Long) s.save(newStatus);
        s.flush();
        tx.commit();
        s.close();
        HashMap res = new HashMap();
        res.put("id", newId);
        return Response.status(201).entity(res).build();
    }

    @Path("{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleUpdateTask(@PathParam("id") int taskId, ItemEntity newTask, @HeaderParam("Authorization") String authorization) {
        // check if user can MOVE task from existing project
        if (!PermissionsChecker.checkPermissionWrite(taskId, authorization)) {
            return Response.status(401).build();
        }
        // check if user has WRITE access to destination project
        if (!PermissionsChecker.checkWritePermissionToProject(newTask.getProjectid(), authorization)) {
            return Response.status(401).build();
        }
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        newTask.setId(taskId);
        s.merge(newTask);
        s.flush();
        tx.commit();
        s.close();
        return Response.status(201).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handlePostNewTask(ItemEntity jsonObject, @HeaderParam("Authorization") String authorization) {
        // when posting new task, check if the user has access to write the task
        // to the project
        if (!PermissionsChecker.checkWritePermissionToProject(jsonObject.getProjectid(), authorization)) {
            return Response.status(401).build();
        }
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
    public Response handleGetComments(@HeaderParam("Authorization") String authorization,@PathParam("id") int taskId) {
        if (!PermissionsChecker.checkPermissionRead(taskId, authorization)) {
            return Response.status(401).build();
        }
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
    public Response handleGetHistory(@HeaderParam("Authorization") String authorization,@PathParam("id") int taskId) {
        if (!PermissionsChecker.checkPermissionRead(taskId, authorization)) {
            return Response.status(401).build();
        }
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
    public Response handleGetTask(@HeaderParam("Authorization") String authorization,@PathParam("id")int taskId) {
        if (!PermissionsChecker.checkPermissionRead(taskId, authorization)) {
            return Response.status(401).build();
        }
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
