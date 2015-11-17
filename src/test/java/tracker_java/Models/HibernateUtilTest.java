package tracker_java.Models;

import junit.framework.TestCase;

/**
 * Created by cristian on 11/17/15.
 */
public class HibernateUtilTest extends TestCase {

    public void testGetOneItemFromQuery() throws Exception {
        Object memberId = HibernateUtil.getOneItemFromQuery("select memberid from MemberEntity where username = 'cristian'");
        assertEquals(memberId, 3);
    }
}