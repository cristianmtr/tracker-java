package tracker_java.Controllers;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * /task -> /task
 * /task/21314 -> /task/id
 * /task/1212451/comments -> /task/id/comments
 */
public enum UrlMatcher {
    INSTANCE;

    public String match(String theUrl){
        Pattern taskPtn = Pattern.compile("/task(/|)$");
        Pattern taskIdPtn = Pattern.compile("/task/[0-9]{1,}(/|)$");
        Pattern taskIdCommentsPtn = Pattern.compile("/task/[0-9]{1,}/comments(/|)$");
        Pattern taskIdHistoryPtn = Pattern.compile("/task/[0-9]{1,}/history(/|)$");
        HashMap<String, Pattern> returnStringToPattern = new HashMap<>();
        returnStringToPattern.put("/task/id/history", taskIdHistoryPtn);
        returnStringToPattern.put("/task/id/comments", taskIdCommentsPtn);
        returnStringToPattern.put("/task/id", taskIdPtn);
        returnStringToPattern.put("/task", taskPtn);
        for (String returnString : returnStringToPattern.keySet()) {
            Matcher m = returnStringToPattern.get(returnString).matcher(theUrl);
            if (m.find() ) {
                return returnString;
            };
        }
        return null;
    };
}
