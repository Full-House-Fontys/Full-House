package CentralPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Kaj Suiker on 22-3-2016.
 */
public class StaffTest {
    private Staff staff;
    private Point2D point2D = new Point2D.Double(12.333,22.557);

    //TODO JAVADOC
    @Before
    public void setUp() throws Exception {
        staff = new Staff();
        assertNotNull("object created", staff);
        staff = new Staff(0, "Kaj", "", "Adams", "p_adams", "jkl(USDj2", point2D, "Brandweer", true, 3, 5);
    }

    //TODO JAVADOC
    @After
    public void tearDown() throws Exception {

    }

    //TODO JAVADOC
    @Test
    public void testGetName() throws Exception {
        assertEquals("Incorrect name get","Kaj",staff.getName());
    }

    //TODO JAVADOC
    @Test
    public void testGetLocation() throws Exception {
        assertEquals("Incorrect get location point2d",point2D,staff.getLocation());
    }

    //TODO JAVADOC
    @Test
    public void testGetLocationString() throws Exception {
        assertEquals("Incorrect Location","12.33; 22.56",staff.getLocationString());
    }

    //TODO JAVADOC
    @Test
    public void testIsOnLocation() throws Exception {
        assertEquals("Incorrect location boolean get",true,staff.isOnLocation());
    }

    //TODO JAVADOC
    @Test
    public void testSetOnLocation() throws Exception {
        staff.setOnLocation(false);
        assertEquals("Incorrect location boolean set",false,staff.isOnLocation());
    }

    //TODO JAVADOC
    @Test
    public void testGetSort() {
        assertEquals("Incorrect sort","Brandweer",staff.getSort());
    }

    //TODO JAVADOC
    @Test
    public void testGetLastName() {
        assertEquals("Incorrect lastname","Adams",staff.getLastName());
    }

    //TODO JAVADOC
    @Test
    public void testGetTeamID() {
        assertEquals("Incorrect TeamId",3,staff.getTeamID());
    }

    //TODO JAVADOC
    @Test
    public void testGetMissionID() {
        assertEquals("Incorrect MissionId",5,staff.getMissionID());
    }

    //TODO JAVADOC
    @Test
    public void testToString() throws Exception{
        assertNotNull("string not returned", staff.toString());
    }
}