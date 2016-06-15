package CentralPoint;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Mark on 15-6-2016.
 */
public class MissionPlanTest {

    MissionPlan MP;

    @Before
    public void setUp() throws Exception {
        MP = new MissionPlan(1, "test");
    }

    @Test
    public void testGetMissionID() throws Exception {
        assertEquals("wrong missionID", 1, MP.getMissionID());
    }

    @Test
    public void testSetMissionID() throws Exception {
        MP.setMissionID(2);
        assertEquals("wrong missionID", 2, MP.getMissionID());
    }

    @Test
    public void testGetAllSteps() throws Exception {
        assertEquals("wrong steps", "test", MP.getAllSteps());
    }

    @Test
    public void testSetAllSteps() throws Exception {
        MP.setAllSteps("another test");
        assertEquals("wrong steps", "another test", MP.getAllSteps());
    }
}