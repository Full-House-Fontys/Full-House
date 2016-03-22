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
public class MissionMarkTest {

    MissionMark mission1;
    MissionMark mission2;
    MissionMark mission3;
    Date date1;
    @Before
    public void setUp() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date1 = new Date();
        mission1 = new MissionMark(1, date1, 10.0, 10.0);
        mission2 = new MissionMark(2, date1, 12.5, 16.3);
        mission3 = new MissionMark(3, date1, 80.1, 60.9);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetMissionId() throws Exception {
        assertEquals("foute missieID", 1, mission1.getMissionId());
        assertEquals("foute missieID", 2, mission2.getMissionId());
        assertEquals("foute missieID", 3, mission3.getMissionId());
        assertNotEquals("fout in missieID", 2, mission1.getMissionId());
        assertNotEquals("fout in missieID", 3, mission2.getMissionId());
        assertNotEquals("fout in missieID", 1, mission3.getMissionId());
    }

    @Test
    public void testGetStartTime() throws Exception {
        assertEquals("foute starttijd", date1, mission1.getStartTime());
        assertEquals("foute starttijd", date1, mission2.getStartTime());
        assertEquals("foute starttijd", date1, mission3.getStartTime());
        Date date2 = new Date(1996);
        assertNotEquals("fout in starttijd", date2, mission1.getStartTime());
        assertNotEquals("fout in starttijd", date2, mission2.getStartTime());
        assertNotEquals("fout in starttijd", date2, mission3.getStartTime());
    }

    @Test
    public void testGetLastUpdate() throws Exception {
        assertEquals("foute LastUpdate", date1, mission1.getLastUpdate());
        assertEquals("foute LastUpdate", date1, mission2.getLastUpdate());
        assertEquals("foute LastUpdate", date1, mission3.getLastUpdate());
        Date date2 = new Date(1996);
        mission1.setLastUpdate(date2);
        assertEquals("foute LastUpdate", date2, mission1.getLastUpdate());
        assertNotEquals("fout in LastUpdate", date1, mission1.getLastUpdate());
        assertNotEquals("fout in LastUpdate", date2, mission2.getLastUpdate());
        assertNotEquals("fout in LastUpdate", date2, mission3.getLastUpdate());
    }

    @Test
    public void testGetEndTime() throws Exception {
        assertEquals("foute starttijd", null, mission1.getEndTime());
        assertEquals("foute starttijd", null, mission2.getEndTime());
        assertEquals("foute starttijd", null, mission3.getEndTime());
        Date date2 = new Date(1996);
        mission1.setEndTime(date2);
        mission2.setEndTime(date2);
        assertEquals("fout in starttijd", date2, mission1.getEndTime());
        assertNotEquals("fout in starttijd", date1, mission1.getEndTime());
        assertNotEquals("fout in starttijd", date1, mission2.getEndTime());
        assertNotEquals("fout in starttijd", date2, mission3.getEndTime());
    }

    @Test
    public void testGetLocationX() throws Exception {
        assertEquals("foute locatiex", 10.0, mission1.getLocationX(), 0.0);
        assertEquals("foute locatiex", 12.5, mission2.getLocationX(), 0.0);
        assertEquals("foute locatiex", 80.1, mission3.getLocationX(), 0.0);
        assertNotEquals("fout in locatiex", 12.5, mission1.getLocationX(), 0.0);
        assertNotEquals("fout in locatiex", 16.1, mission2.getLocationX(), 0.0);
        assertNotEquals("fout in locatiex", 10.0, mission3.getLocationX(), 0.0);
    }

    @Test
    public void testGetLocationY() throws Exception {
        assertEquals("foute locatiey", 10.0, mission1.getLocationY(), 0.0);
        assertEquals("foute locatiey", 16.3, mission2.getLocationY(), 0.0);
        assertEquals("foute locatiey", 60.9, mission3.getLocationY(), 0.0);
        assertNotEquals("fout in locatiey", 12.5, mission1.getLocationY(), 0.0);
        assertNotEquals("fout in locatiey", 16.1, mission2.getLocationY(), 0.0);
        assertNotEquals("fout in locatiey", 10.0, mission3.getLocationY(), 0.0);
    }

    @Test
    public void testSetLastUpdate() throws Exception {
        assertEquals("foute LastUpdate", date1, mission1.getLastUpdate());
        Date date2 = new Date(1996);
        mission1.setLastUpdate(date2);
        assertEquals("foute LastUpdate", date2, mission1.getLastUpdate());
        assertEquals("foute LastUpdate", date1, mission2.getLastUpdate());
        mission2.setLastUpdate(date2);
        assertEquals("foute LastUpdate", date2, mission2.getLastUpdate());
        assertEquals("foute LastUpdate", date1, mission3.getLastUpdate());
        mission3.setLastUpdate(date2);
        assertEquals("foute LastUpdate", date2, mission3.getLastUpdate());
        assertNotEquals("foute LastUpdate", date1, mission1.getLastUpdate());
        assertNotEquals("foute LastUpdate", date1, mission2.getLastUpdate());
        assertNotEquals("foute LastUpdate", date1, mission3.getLastUpdate());
    }

    @Test
    public void testSetEndTime() throws Exception {
        assertEquals("foute EndTime", null, mission1.getEndTime());
        Date date2 = new Date(1996);
        mission1.setEndTime(date2);
        assertEquals("foute EndTime", date2, mission1.getEndTime());
        assertEquals("foute EndTime", null, mission2.getEndTime());
        mission2.setEndTime(date2);
        assertEquals("foute EndTime", date2, mission2.getEndTime());
        assertEquals("foute EndTime", null, mission3.getEndTime());
        mission3.setEndTime(date2);
        assertEquals("foute EndTime", date2, mission3.getEndTime());
        assertNotEquals("foute EndTime", null, mission1.getEndTime());
        assertNotEquals("foute EndTime", null, mission2.getEndTime());
        assertNotEquals("foute EndTime", null, mission3.getEndTime());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("foute tekst", "id: 1 start time: " + date1, mission1.toString());
        assertEquals("foute tekst", "id: 2 start time: " + date1, mission2.toString());
        assertEquals("foute tekst", "id: 3 start time: " + date1, mission3.toString());
        assertNotEquals("foute tekst", "id: 2 start time: " + date1, mission1.toString());
        assertNotEquals("foute tekst", "id: 3 start time: " + date1, mission2.toString());
        assertNotEquals("foute tekst", "id: 1 start time: " + date1, mission3.toString());
    }
}