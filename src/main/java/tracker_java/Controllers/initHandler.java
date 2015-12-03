package tracker_java.Controllers;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.IntegerType;
import tracker_java.Models.*;
import tracker_java.Utilities.PermissionsChecker;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static tracker_java.Models.HibernateUtil.getListFromQuery;

/**
 * Created by cristian on 10/4/15.
 */
@Path("init")
public class initHandler {

    private List getAllTasks(Integer userId) {
        List<ItemEntity> tasks = new ArrayList<ItemEntity>();
        List response = HibernateUtil.getListFromQuery(String.format("from MemberprojectEntity where memberid=%s", userId));
        for (MemberprojectEntity mem : (List<MemberprojectEntity>) response) {
            if (mem.getPosition() >= 1) {
                List<ItemEntity> newTasks = HibernateUtil.getListFromQuery(String.format("from ItemEntity where projectid=%s", mem.getProjectid()));
                tasks.addAll(newTasks);
            }
        }
        return tasks;

    }

    private HashMap getDataSources(){
        HashMap dataSources = new HashMap();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List membersList = session.createQuery("from MemberEntity ").list();
        session.flush();
        session.close();

        HashMap members = new HashMap();
        for (MemberEntity member : (List<MemberEntity>) membersList) {
            members.put(member.getMemberid(), member.getUsername());
        }

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List tasklistsList = session.createQuery("from ProjectEntity ").list();
        session.close();

        HashMap tasklists = new HashMap();
        for (ProjectEntity tasklist : (List<ProjectEntity>)tasklistsList) {
            tasklists.put(tasklist.getProjectid(), tasklist.getName());
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
        dataSources.put("projectlist", tasklists);
        dataSources.put("responsible", members);

        return dataSources;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response handle(@HeaderParam("Authorization") String authorization) {
        System.out.println("request at /init");

        Integer userId = PermissionsChecker.getUserIdFromAuthorization(authorization);

        List tasks = this.getAllTasks(userId);
        HashMap dataSources = this.getDataSources();

        HashMap result = new HashMap();
        result.put("data", tasks);
        result.put("dataSources", dataSources);

        return Response.status(200).entity(result).build();
    }
}
