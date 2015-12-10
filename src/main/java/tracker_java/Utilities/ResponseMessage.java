package tracker_java.Utilities;

import java.util.HashMap;

/**
 * HashMap to ease and standardize client info messages
 * @param newMessage info message to be seen by the client
 */
public class ResponseMessage extends HashMap {

    public ResponseMessage(String newMessage) {
        this.put("info", newMessage);
    }

}
