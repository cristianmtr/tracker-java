package tracker_java.Controllers;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import tracker_java.Models.FormGenerator;
import tracker_java.Models.HibernateUtil;
import tracker_java.Models.MemberEntity;
import tracker_java.Utilities.AuthenticationHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.UUID;

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

    @Path("token")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(@Context HttpHeaders headers) {
        // userNamePassword = [$username, $password]
        String[] userNamePassword = AuthenticationHandler.decode(headers.getRequestHeader("authorization").get(0));

        if (userNamePassword != null) {
            try {
                if (AuthenticationHandler.checkUserNamePassword(userNamePassword[0], userNamePassword[1])) {
                    // TODO generate token somehow
                    String key = UUID.randomUUID().toString().toUpperCase() +
                            "|" + userNamePassword[0];
                    String theToken = BCrypt.hashpw(key, BCrypt.gensalt());
                    // save token in redis
                    // token -> userId
                    Integer userId;
                    try {
                        userId = AuthenticationHandler.getUserIdFromUsername(userNamePassword[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new WebApplicationException(Response.status(400).build());
                    }
                    AuthenticationHandler.saveToken(theToken, userId);
                    HashMap res = new HashMap();
                    res.put("token", theToken);
                    return Response.ok(res).build();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new WebApplicationException(Response.status(400).build());
            }
        }

        return Response.status(401).build();
    }

    @GET
    @Path("specification")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserForm() {
        HashMap userForm = FormGenerator.INSTANCE.generateForm(MemberEntity.class);
        return Response.status(200).entity(userForm).build();
    }
}
