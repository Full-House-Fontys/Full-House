package CentralPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Mark on 22-3-2016.
 */
public class MissionTest {

    Mission mission1;
    Mission mission2;
    Mission mission3;
    Date date1;

    @Before
    public void setUp() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date1 = new Date();
        mission1 = new Mission(1, "FIRE", "fire", date1, date1, null, 10.0, 10.0);
        mission2 = new Mission(2, "BURGLER", "burgler", date1, date1, null, 12.5, 16.3);
        mission3 = new Mission(3, "INJURED", "injured", date1, date1, null, 80.1, 60.9);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetStartTime() throws Exception {
        assertEquals("wrong starttime", date1, mission1.getStartTime());
        assertEquals("wrong starttime", date1, mission2.getStartTime());
        assertEquals("wrong starttime", date1, mission3.getStartTime());
        Date date2 = new Date(1996);
        assertNotEquals("error in starttime", date2, mission1.getStartTime());
        assertNotEquals("error in starttime", date2, mission2.getStartTime());
        assertNotEquals("error in starttime", date2, mission3.getStartTime());
    }

    @Test
    public void testGetLastUpdate() throws Exception {
        assertEquals("wrong LastUpdate", date1, mission1.getLastUpdate());
        assertEquals("wrong LastUpdate", date1, mission2.getLastUpdate());
        assertEquals("wrong LastUpdate", date1, mission3.getLastUpdate());
        Date date2 = new Date(1996);
        mission1.setLastUpdate(date2);
        assertEquals("wrong LastUpdate", date2, mission1.getLastUpdate());
        assertNotEquals("error in LastUpdate", date1, mission1.getLastUpdate());
        assertNotEquals("error in LastUpdate", date2, mission2.getLastUpdate());
        assertNotEquals("error in LastUpdate", date2, mission3.getLastUpdate());
    }

    @Test
    public void testGetEndTime() throws Exception {
        assertEquals("wrong endtime", null, mission1.getEndTime());
        assertEquals("wrong endtime", null, mission2.getEndTime());
        assertEquals("wrong endtime", null, mission3.getEndTime());
        Date date2 = new Date(1996);
        mission1.setEndTime(date2);
        mission2.setEndTime(date2);
        assertEquals("wrong in endtime", date2, mission1.getEndTime());
        assertNotEquals("error in endtime", date1, mission1.getEndTime());
        assertNotEquals("error in endtime", date1, mission2.getEndTime());
        assertNotEquals("error in endtime", date2, mission3.getEndTime());
    }

    @Test
    public void testGetLocationX() throws Exception {
        assertEquals("wrong locationx", 10.0, mission1.getLocationX(), 0.0);
        assertEquals("wrong locationx", 12.5, mission2.getLocationX(), 0.0);
        assertEquals("wrong locationx", 80.1, mission3.getLocationX(), 0.0);
        assertNotEquals("error in locationx", 12.5, mission1.getLocationX(), 0.0);
        assertNotEquals("error in locationx", 16.1, mission2.getLocationX(), 0.0);
        assertNotEquals("error in locationx", 10.0, mission3.getLocationX(), 0.0);
    }

    @Test
    public void testGetLocationY() throws Exception {
        assertEquals("wrong locationy", 10.0, mission1.getLocationY(), 0.0);
        assertEquals("wrong locationy", 16.3, mission2.getLocationY(), 0.0);
        assertEquals("wrong locationy", 60.9, mission3.getLocationY(), 0.0);
        assertNotEquals("error in locationy", 12.5, mission1.getLocationY(), 0.0);
        assertNotEquals("error in locationy", 16.1, mission2.getLocationY(), 0.0);
        assertNotEquals("error in locationy", 10.0, mission3.getLocationY(), 0.0);
    }

    @Test
    public void testSetLastUpdate() throws Exception {
        assertEquals("wrong LastUpdate", date1, mission1.getLastUpdate());
        Date date2 = new Date(1996);
        mission1.setLastUpdate(date2);
        assertEquals("wrong LastUpdate", date2, mission1.getLastUpdate());
        assertEquals("wrong LastUpdate", date1, mission2.getLastUpdate());
        mission2.setLastUpdate(date2);
        assertEquals("wrong LastUpdate", date2, mission2.getLastUpdate());
        assertEquals("wrong LastUpdate", date1, mission3.getLastUpdate());
        mission3.setLastUpdate(date2);
        assertEquals("wrong LastUpdate", date2, mission3.getLastUpdate());
        assertNotEquals("wrong LastUpdate", date1, mission1.getLastUpdate());
        assertNotEquals("wrong LastUpdate", date1, mission2.getLastUpdate());
        assertNotEquals("wrong LastUpdate", date1, mission3.getLastUpdate());
    }

    @Test
    public void testSetEndTime() throws Exception {
        assertEquals("wrong EndTime", null, mission1.getEndTime());
        Date date2 = new Date(1996);
        mission1.setEndTime(date2);
        assertEquals("wrong EndTime", date2, mission1.getEndTime());
        assertEquals("wrong EndTime", null, mission2.getEndTime());
        mission2.setEndTime(date2);
        assertEquals("wrong EndTime", date2, mission2.getEndTime());
        assertEquals("wrong EndTime", null, mission3.getEndTime());
        mission3.setEndTime(date2);
        assertEquals("wrong EndTime", date2, mission3.getEndTime());
        assertNotEquals("wrong EndTime", null, mission1.getEndTime());
        assertNotEquals("wrong EndTime", null, mission2.getEndTime());
        assertNotEquals("wrong EndTime", null, mission3.getEndTime());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("wrong tekst", mission1.getName(), mission1.toString());
        assertEquals("wrong tekst", mission2.getName(), mission2.toString());
        assertEquals("wrong tekst", mission3.getName(), mission3.toString());
        assertNotEquals("wrong tekst", mission3.getName(), mission1.toString());
        assertNotEquals("wrong tekst", mission1.getName(), mission2.toString());
        assertNotEquals("wrong tekst", mission2.getName(), mission3.toString());
    }

    @Test
    public void testGetID() throws Exception {
        assertEquals("wrong missionID", 1, mission1.getID());
        assertEquals("wrong missionID", 2, mission2.getID());
        assertEquals("wrong missionID", 3, mission3.getID());
        assertNotEquals("error in missionID", 2, mission1.getID());
        assertNotEquals("error in missionID", 3, mission2.getID());
        assertNotEquals("error in missionID", 1, mission3.getID());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("wrong missionID", "FIRE", mission1.getName());
        assertEquals("wrong missionID", "BURGLER", mission2.getName());
        assertEquals("wrong missionID", "INJURED", mission3.getName());
        assertNotEquals("error in missionID", "BURGLER", mission1.getName());
        assertNotEquals("error in missionID", "INJURED", mission2.getName());
        assertNotEquals("error in missionID", "FIRE", mission3.getName());
    }

    @Test
    public void testGetDescription() throws Exception {
        assertEquals("wrong missionID", "fire", mission1.getDescription());
        assertEquals("wrong missionID", "burgler", mission2.getDescription());
        assertEquals("wrong missionID", "injured", mission3.getDescription());
        assertNotEquals("error in missionID", "wrong description", mission1.getDescription());
        assertNotEquals("error in missionID", "wrong description", mission2.getDescription());
        assertNotEquals("error in missionID", "wrong description", mission3.getDescription());
    }

    @Test
    public void testGetTeamsAssigned() throws Exception {

    }

    @Test
    public void testSetTeamsAssigned() throws Exception {

    }

    @Test
    public void testAddTeamToJob() throws Exception {

    }
}