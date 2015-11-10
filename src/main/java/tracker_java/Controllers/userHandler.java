package tracker_java.Controllers;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import tracker_java.Models.FormGenerator;
import tracker_java.Models.HibernateUtil;
import tracker_java.Models.MemberEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

/**
 * Created by CristianMitroi on 10-11-2015.
 */
@Path("/users")
public class userHandler {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postNewUser(MemberEntity newUser){
        String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        System.out.println(hashedPassword);
        newUser.setPassword(hashedPassword);
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Integer newCommentId = (Integer)s.save(newUser);
        s.flush();
        tx.commit();
        s.close();
        HashMap res = new HashMap();
        res.put("id", newCommentId);
        return Response.status(201).entity(res).build();
    }

    @GET
    @Path("spec")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserForm() {
        HashMap userForm = FormGenerator.INSTANCE.generateForm(MemberEntity.class);
        return Response.status(200).entity(userForm).build();
    }
}
