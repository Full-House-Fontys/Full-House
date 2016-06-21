package CentralPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Kaj Suiker on 22-3-2016.
 */
public class StaffTest {
    private Staff staff;
    private Point2D point2D = new Point2D.Double(12.333,22.557);

    /**
     * set up for unittest
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        staff = new Staff();
        assertNotNull("object created", staff);
        staff = new Staff(0, "Kaj", "", "Adams", "p_adams", "jkl(USDj2", point2D, "Brandweer", true, 3, 5);
    }

    /**
     * tear down procedure of unittest
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {

    }

    /**
     * test to get staff name
     * @throws Exception
     */
    @Test
    public void testGetName() throws Exception {
        assertEquals("Incorrect name get","Kaj",staff.getName());
    }

    /**
     * test to get staff location
     * @throws Exception
     */
    @Test
    public void testGetLocation() throws Exception {
        assertEquals("Incorrect get location point2d",point2D,staff.getLocation());
    }

    /**
     * test to get the staff locationstring
     * @throws Exception
     */
    @Test
    public void testGetLocationString() throws Exception {
        assertEquals("Incorrect Location","12.33; 22.56",staff.getLocationString());
    }

    /**
     * test to check if staff is already on a location
     * @throws Exception
     */
    @Test
    public void testIsOnLocation() throws Exception {
        assertEquals("Incorrect location boolean get",true,staff.isOnLocation());
    }

    /**
     * test to set the onlocation boolean
     * @throws Exception
     */
    @Test
    public void testSetOnLocation() throws Exception {
        staff.setOnLocation(false);
        assertEquals("Incorrect location boolean set",false,staff.isOnLocation());
    }


    /**
     * test to get staff job
     */
    @Test
    public void testGetSort() {
        assertEquals("Incorrect sort","Brandweer",staff.getSort());
    }

    /**
     * test to get staff lastname
     */
    @Test
    public void testGetLastName() {
        assertEquals("Incorrect lastname","Adams",staff.getLastName());
    }

    /**
     * test to get the team id
     */
    @Test
    public void testGetTeamID() {
        assertEquals("Incorrect TeamId",3,staff.getTeamID());
    }

    /**
     * test to get latest mission id of the staff member
     */
    @Test
    public void testGetMissionID() {
        assertEquals("Incorrect MissionId",5,staff.getMissionID());
    }

    /**
     * test to get the toString method of the staff
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception{
        assertNotNull("string not returned", staff.toString());
    }

    /**
     * test to get username of staff member
     *
     * @throws Exception
     */
    @Test
    public void testGetUserName() throws Exception {
        assertEquals("username wrong", "p_adams", staff.getUserName());
    }

    /**
     * test to get password of staff member
     *
     * @throws Exception
     */
    @Test
    public void testGetPassword() throws Exception {
        assertEquals("password wrong", "jkl(USDj2", staff.getPassword());
    }

    /**
     * test to set the username of the staff member
     *
     * @throws Exception
     */
    @Test
    public void testSetUserName() throws Exception {
        staff.setUserName("testname");
        assertEquals("username wrong", "testname", staff.getUserName());
    }

    /**
     * test to set the password of the staff member
     *
     * @throws Exception
     */
    @Test
    public void testSetPassword() throws Exception {
        staff.setPassword("testpassword");
        assertEquals("password wrong", "testpassword", staff.getPassword());
    }

    /**
     * test to get staff id
     *
     * @throws Exception
     */
    @Test
    public void testGetId() throws Exception {
        assertEquals("password wrong", 0, staff.getId());
    }

    /**
     * test to set staff id
     *
     * @throws Exception
     */
    @Test
    public void testSetId() throws Exception {
        staff.setId(1);
        assertEquals("password wrong", 1, staff.getId());
    }
}