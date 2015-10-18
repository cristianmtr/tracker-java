package tracker_java.Controllers;

import junit.framework.TestCase;

/**
 * Created by cristian on 10/18/15.
 */
public class UrlMatcherTest extends TestCase {

    public void testMatchTaskId() throws Exception {
        assertEquals(UrlMatcher.INSTANCE.match("/task/123"), "/task/id");
    }

    public void testMatchTaskIdComments() throws Exception {
        assertEquals(UrlMatcher.INSTANCE.match("/task/123/comments"), "/task/id/comments");
    }

    public void testMatchTaskIdHistory() throws Exception {
        assertEquals(UrlMatcher.INSTANCE.match("/task/123/history"), "/task/id/history");
    }

    public void testMatchTaskNoId() throws Exception {
        assertEquals(UrlMatcher.INSTANCE.match("/task"), "/task");
    }

    public void testMatchTaskIdTrailing() throws Exception {
        assertEquals(UrlMatcher.INSTANCE.match("/task/123/"), "/task/id");
    }

    public void testMatchTaskIdCommentsTrailing() throws Exception {
        assertEquals(UrlMatcher.INSTANCE.match("/task/123/comments/"), "/task/id/comments");
    }

    public void testMatchTaskIdHistoryTrailing() throws Exception {
        assertEquals(UrlMatcher.INSTANCE.match("/task/123/history/"), "/task/id/history");
    }

    public void testMatchTaskNoIdTrailing() throws Exception {
        assertEquals(UrlMatcher.INSTANCE.match("/task/"), "/task");
    }
}