package tracker_java.Controllers;

import junit.framework.TestCase;

import javax.swing.text.html.parser.Entity;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by cristian on 10/26/15.
 */
public class taskEndpointHandlerTest extends TestCase {

    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target("http://localhost:8000");

    public void testDateFieldInNewTask() throws Exception {
        final String objectToPost = "{\"deadline\":\"2015-11-29T00:00:00+01:00\",\"description\":\"task with deadline from sqlalchemy\",\"itemId\":19,\"title\":\"task with deadline from sqlalchemy UPDATED FROM CURL\"}";
        Response res = webTarget.path("/task/testpost").request(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(objectToPost, MediaType.APPLICATION_JSON));

        System.out.println(res.readEntity(String.class));

    }
}