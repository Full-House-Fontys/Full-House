package HulpDienst;

import CentralPoint.Mission;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Mark on 26-4-2016.
 */
public class requestsTest {

    TeamRequest TR;
    Mission mission;
    requests RQ;

    /**
     * the set up that needs to be done before every test
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        mission = new Mission(1, null, null, null, null, null, 0.0, 0.0, -1);
        TR = new TeamRequest("BRAND", "veel brand", 2, 2, 6, 1, mission);
        RQ = new requests();
    }

    /**
     * test to get all requests from testrequest
     *
     * @throws Exception
     */
    @Test
    public void testGetRequests() throws Exception {
        assertEquals("wrong list", 0, RQ.GetRequests().size());
    }

    /**
     * test to add request to list
     * @throws Exception
     */
    @Test
    public void testAddRequests() throws Exception {
        RQ.addRequests(TR);
        assertEquals("wrong list", 1, RQ.GetRequests().size());
        assertEquals("wrong list", true, RQ.GetRequests().contains(TR));

    }
}