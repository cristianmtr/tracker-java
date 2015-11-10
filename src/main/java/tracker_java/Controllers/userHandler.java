package tracker_java.Controllers;

import tracker_java.Models.FormGenerator;
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

        return Response.status(201).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserForm() {
        HashMap userForm = FormGenerator.INSTANCE.generateForm(MemberEntity.class);
        return Response.status(200).entity(userForm).build();
    }
}
