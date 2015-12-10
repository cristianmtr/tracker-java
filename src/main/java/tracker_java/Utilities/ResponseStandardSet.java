package tracker_java.Utilities;


import javax.ws.rs.core.Response;

public class ResponseStandardSet {

    public static Response noPermissionOnProject() {
        return Response.status(403).entity(new ResponseMessage("No adequate permission on project resource")).build();
    }

    public static Response emptyDataOnPost() {
        return Response.status(400).entity(new ResponseMessage("Empty data")).build();
    }

    public static Response itemNotBelongsToProject() {
        return Response.status(404).entity(new ResponseMessage("Task item does not belong to a project")).build();
    }

    public static Response noValidToken() {
        return Response.status(401).entity(new ResponseMessage("No valid token provided")).build();
    }

    public static Response serverErrorWithException(Throwable e) {
        return Response.serverError().entity(new ResponseMessage(e.getMessage())).build();
    }
}
