package CentralPoint;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * fhict on 11-05-16.
 */
public class NotificationTest {
    Notification n;
    Calendar c;

    /**
     * Sets up the unit test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        c = Calendar.getInstance();
        Date dateRepresentation = c.getTime();
        n = new Notification(2, "EHLP", "In Castenray is iets verschrikkelijks gebeurd", dateRepresentation, 2, 2);
    }

    /**
     * Gets the id
     * @throws Exception
     */
    @Test
    public void getID() throws Exception {
        Assert.assertEquals("ID is not correct", 2, n.getID());
    }

    /**
     * Sets the id
     * @throws Exception
     */
    @Test
    public void setID() throws Exception {
        n.setID(3);
        Assert.assertEquals("ID hasn't been changed correctly", 3, n.getID());
    }

    /**
     * Gets the title
     * @throws Exception
     */
    @Test
    public void getTitle() throws Exception {
        Assert.assertEquals("Title is not correct", "EHLP", n.getTitle());
    }

    /**
     * Sets the title
     * @throws Exception
     */
    @Test
    public void setTitle() throws Exception {
        n.setTitle("HELP");
        Assert.assertEquals("Title hasn't been changed correctly", "HELP", n.getTitle());
    }

    /**
     * Gets the content
     * @throws Exception
     */
    @Test
    public void getContent() throws Exception {
        Assert.assertEquals("Content is not correct", "In Castenray is iets verschrikkelijks gebeurd", n.getContent());
    }

    /**
     * Sets the content
     * @throws Exception
     */
    @Test
    public void setContent() throws Exception {
        n.setContent("In Oploo is iets geweldigs gebeurd");
        Assert.assertEquals("Content hasn't been changed correctly", "In Oploo is iets geweldigs gebeurd", n.getContent());
    }

    /**
     * Gets the time
     * @throws Exception
     */
    @Test
    public void getTime() throws Exception {
        Assert.assertEquals("Time is not correct", c.getTime(), n.getTime());
    }

    /**
     * Sets the time
     * @throws Exception
     */
    @Test
    public void setTime() throws Exception {
        c.set(Calendar.HOUR, 10);
        n.setTime(c.getTime());
        Assert.assertEquals("Time has not been changed correctly", c.getTime(), n.getTime());
    }

    /**
     * Gets the missionID
     * @throws Exception
     */
    @Test
    public void getMissionID() throws Exception {
        Assert.assertEquals("MissionID is not correct", 2, n.getMissionID());
    }

    /**
     * Sets the missionID
     * @throws Exception
     */
    @Test
    public void setMissionID() throws Exception {
        n.setMissionID(3);
        Assert.assertEquals("MissionID hasn't been changed correctly", 3, n.getMissionID());
    }

    /**
     * Gets the userID
     * @throws Exception
     */
    @Test
    public void getUserID() throws Exception {
        Assert.assertEquals("UserID is not correct", 2, n.getUserID());
    }

    /**
     * Sets the userID
     * @throws Exception
     */
    @Test
    public void setUserID() throws Exception {
        n.setUserID(3);
        Assert.assertEquals("UserID hasn't been changed correctly", 3, n.getUserID());
    }

    /**
     * Tests the ToString function
     * @throws Exception
     */
    @Test
    public void toStringTest() throws Exception {
        Assert.assertEquals("ToString gave wrong text", n.getContent(), n.toString());
    }

}