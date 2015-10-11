package tracker_java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * first checks what the URI is;
 * if /task/<id>
 *     just returns the json representation of the task of that id
 *  if /task/<id>/comments
 *      return json of comments
 *  if /task/<id>/history
 *      return json of history
 */
public class taskEndpointHandler implements HttpHandler {

    public void handleComments(HttpExchange httpExchange) {

    }

    public void handleHistory(HttpExchange httpExchange) {

    }

    public void handleTask(HttpExchange httpExchange) {

    }

    public void handle(HttpExchange httpExchange){
        System.out.println("request at /task");
        String fullURI = httpExchange.getRequestURI().toString();
        String[] fullURISplit = fullURI.split("/");
        if (fullURISplit[(fullURISplit).length-1].equals("comments") ) {
            // handle comments
            System.out.println("// handle comments");
            handleComments(httpExchange);
        }
        else if (fullURISplit[(fullURISplit).length-1].equals("history") ) {
            // handle history
            System.out.println("// handle history");
        }
        else {
            // handle task itself
            System.out.println("// handle task itself");
        }


    }
}
