package tracker_java.Models;

import junit.framework.TestCase;

/**
 * Created by cristian on 11/17/15.
 */
public class HibernateUtilTest extends TestCase {

    public void testGetOneItemFromQuery() throws Exception {
        String username = "cristian";
        Object memberId = HibernateUtil.getOneItemFromQuery(String.format("select memberid from MemberEntity where username = '%s'",username));
        assertEquals(memberId, 3);
    }
}