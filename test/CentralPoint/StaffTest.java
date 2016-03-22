package CentralPoint;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

/**
 * Created by Kaj Suiker on 22-3-2016.
 */
public class StaffTest {
    private Staff staff;
    private Point2D point2D = new Point2D.Double(12.333,22.557);

    @Before
    public void setUp() throws Exception {
        staff = new Staff();
        assertNotNull("object created", staff);
        staff = new Staff("Kaj", "", "Adams","p_adams","jkl(USDj2", point2D, "Brandweer", 4,true );
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Incorrect name get","Kaj",staff.getName());
    }

    @Test
    public void testGetLocation() throws Exception {
        assertEquals("Incorrect get location point2d",point2D,staff.getLocation());
    }

    @Test
    public void testGetTeamID() throws Exception {
        assertEquals("Incorrect teamID",4,staff.getTeamID());
    }

    @Test
    public void testGetLocationString() throws Exception {
        assertEquals("Incorrect Location","12.33; 22.56",staff.getLocationString());
    }

    @Test
    public void testIsOnLocation() throws Exception {
        assertEquals("Incorrect location boolean get",true,staff.isOnLocation());
    }

    @Test
    public void testSetOnLocation() throws Exception {
        staff.setOnLocation(false);
        assertEquals("Incorrect location boolean set",false,staff.isOnLocation());
    }

    @Test
    public void testToString() throws Exception{
        assertNotNull("string not returned", staff.toString());
    }
}