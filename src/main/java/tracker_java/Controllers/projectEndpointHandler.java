package tracker_java.Controllers;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import tracker_java.Models.HibernateUtil;
import tracker_java.Models.ProjectEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by CristianMitroi on 10-12-2015.
 */
@Path("projects")
public class projectEndpointHandler {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjects() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        List<ProjectEntity> theList = (List<ProjectEntity>) s.createCriteria(ProjectEntity.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("projectid"), "projectid")
                        .add(Projections.property("name"), "name")
                        .add(Projections.property("description"), "description"))
                .setResultTransformer(Transformers.aliasToBean(ProjectEntity.class))
                .list();

        s.close();
        return Response.status(200).entity(theList).build();
    }
}
