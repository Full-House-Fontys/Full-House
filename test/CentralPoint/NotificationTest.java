package CentralPoint;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by fhict on 11-05-16.
 */
public class NotificationTest {
    Notification n;
    Calendar c;

    @Before
    public void setUp() throws Exception {
        c = Calendar.getInstance();
        Date dateRepresentation = c.getTime();
        n = new Notification(2, "EHLP", "In Castenray is iets verschrikkelijks gebeurd", dateRepresentation, 2, 2);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getID() throws Exception {
        Assert.assertEquals("ID is not correct", 2, n.getID());
    }

    @Test
    public void setID() throws Exception {
        n.setID(3);
        Assert.assertEquals("ID hasn't been changed correctly", 3, n.getID());
    }

    @Test
    public void getTitle() throws Exception {
        Assert.assertEquals("Title is not correct", "EHLP", n.getTitle());
    }

    @Test
    public void setTitle() throws Exception {
        n.setTitle("HELP");
        Assert.assertEquals("Title hasn't been changed correctly", "HELP", n.getTitle());
    }

    @Test
    public void getContent() throws Exception {
        Assert.assertEquals("Content is not correct", "In Castenray is iets verschrikkelijks gebeurd", n.getContent());
    }

    @Test
    public void setContent() throws Exception {
        n.setContent("In Oploo is iets geweldigs gebeurd");
        Assert.assertEquals("Content hasn't been changed correctly", "In Oploo is iets geweldigs gebeurd", n.getContent());
    }

    @Test
    public void getTime() throws Exception {
        Assert.assertEquals("Time is not correct", c.getTime(), n.getTime());
    }

    @Test
    public void setTime() throws Exception {
        c.set(Calendar.HOUR, 10);
        n.setTime(c.getTime());
        Assert.assertEquals("Time has not been changed correctly", c.getTime(), n.getTime());
    }

    @Test
    public void getMissionID() throws Exception {
        Assert.assertEquals("MissionID is not correct", 2, n.getMissionID());
    }

    @Test
    public void setMissionID() throws Exception {
        n.setMissionID(3);
        Assert.assertEquals("MissionID hasn't been changed correctly", 3, n.getMissionID());
    }

    @Test
    public void getUserID() throws Exception {
        Assert.assertEquals("UserID is not correct", 2, n.getUserID());
    }

    @Test
    public void setUserID() throws Exception {
        n.setUserID(3);
        Assert.assertEquals("UserID hasn't been changed correctly", 3, n.getUserID());
    }

    @Test
    public void toStringTest() throws Exception {
        Assert.assertEquals("ToString gave wrong text", n.getContent(), n.toString());
    }

}