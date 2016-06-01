package HulpDienst;

import CentralPoint.Mission;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mark on 26-4-2016.
 */
public class requestsTest {

    TeamRequest TR;
    Mission mission;
    requests RQ;

    //TODO JAVADOC
    @Before
    public void setUp() throws Exception {
        mission = new Mission(1, null, null, null, null, null, 0.0, 0.0);
        TR = new TeamRequest("BRAND", "veel brand", 2, 2, 6, 1, mission);
        RQ = new requests();
    }

    //TODO JAVADOC
    @Test
    public void testGetRequests() throws Exception {
        assertEquals("wrong list", 0, RQ.GetRequests().size());
    }

    //TODO JAVADOC
    @Test
    public void testAddRequests() throws Exception {
        RQ.addRequests(TR);
        assertEquals("wrong list", 1, RQ.GetRequests().size());
        assertEquals("wrong list", true, RQ.GetRequests().contains(TR));

    }
}