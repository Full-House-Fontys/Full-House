package CentralPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Mark on 22-3-2016.
 */
public class MissionTest {

    Mission mission0;
    Mission mission1;
    Mission mission2;
    Mission mission3;
    Date date1;
    Team team1;
    Team team2;
    Team team3;
    ArrayList<Team> teamList1;
    ArrayList<Team> teamList2;
    ArrayList<Team> teamList3;
    ArrayList<Team> teamList4;

    /**
     * set up unittest
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        date1 = new Date();
        mission0 = new Mission();
        mission1 = new Mission(1, "FIRE", "fire", date1, date1, null, 10.0, 10.0, 3);
        mission2 = new Mission(2, "BURGLER", "burgler", date1, date1, null, 12.5, 16.3, 4);
        mission3 = new Mission(3, "INJURED", "injured", date1, date1, null, 80.1, 60.9, 5);
        team1 = new Team(1, "brandweer", null, null);
        team2 = new Team(2, "EHBO", null, null);
        team3 = new Team(3, "politie", null, null);
        teamList1 = new ArrayList<>();
        teamList2 = new ArrayList<>();
        teamList3 = new ArrayList<>();
        teamList4 = new ArrayList<>();
        teamList1.add(team1);
        teamList1.add(team2);
        teamList2.add(team2);
        teamList2.add(team3);
        teamList3.add(team1);
        teamList3.add(team3);
        teamList4.add(team1);
        teamList4.add(team2);
        teamList4.add(team3);
    }

    /**
     * tear down unittest
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {

    }

    /**
     * test get mission start time
     * @throws Exception
     */
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

    /**
     * test mission last updaten
     * @throws Exception
     */
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

    /**
     * test get mission ending time
     * @throws Exception
     */
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

    /**
     * test to get mission x location
     * @throws Exception
     */
    @Test
    public void testGetLocationX() throws Exception {
        assertEquals("wrong locationx", 10.0, mission1.getLocationX(), 0.0);
        assertEquals("wrong locationx", 12.5, mission2.getLocationX(), 0.0);
        assertEquals("wrong locationx", 80.1, mission3.getLocationX(), 0.0);
        assertNotEquals("error in locationx", 12.5, mission1.getLocationX(), 0.0);
        assertNotEquals("error in locationx", 16.1, mission2.getLocationX(), 0.0);
        assertNotEquals("error in locationx", 10.0, mission3.getLocationX(), 0.0);
    }

    /**
     * test to get mission y location
     * @throws Exception
     */
    @Test
    public void testGetLocationY() throws Exception {
        assertEquals("wrong locationy", 10.0, mission1.getLocationY(), 0.0);
        assertEquals("wrong locationy", 16.3, mission2.getLocationY(), 0.0);
        assertEquals("wrong locationy", 60.9, mission3.getLocationY(), 0.0);
        assertNotEquals("error in locationy", 12.5, mission1.getLocationY(), 0.0);
        assertNotEquals("error in locationy", 16.1, mission2.getLocationY(), 0.0);
        assertNotEquals("error in locationy", 10.0, mission3.getLocationY(), 0.0);
    }

    /**
     * test to set last update time
     * @throws Exception
     */
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

    /**
     * test to set mission end time
     * @throws Exception
     */
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

    /**
     * test to get the mission toString
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        assertEquals("wrong tekst", mission1.getID() + ": " + mission1.getName(), mission1.toString());
        assertEquals("wrong tekst", mission2.getID() + ": " + mission2.getName(), mission2.toString());
        assertEquals("wrong tekst", mission3.getID() + ": " + mission3.getName(), mission3.toString());
        assertNotEquals("wrong tekst", mission3.getName(), mission1.toString());
        assertNotEquals("wrong tekst", mission1.getName(), mission2.toString());
        assertNotEquals("wrong tekst", mission2.getName(), mission3.toString());
    }

    /**
     * test to get mission id
     * @throws Exception
     */
    @Test
    public void testGetID() throws Exception {
        assertEquals("wrong missionID", 1, mission1.getID());
        assertEquals("wrong missionID", 2, mission2.getID());
        assertEquals("wrong missionID", 3, mission3.getID());
        assertNotEquals("error in missionID", 2, mission1.getID());
        assertNotEquals("error in missionID", 3, mission2.getID());
        assertNotEquals("error in missionID", 1, mission3.getID());
    }

    /**
     * test to get mission name
     * @throws Exception
     */
    @Test
    public void testGetName() throws Exception {
        assertEquals("wrong missionID", "FIRE", mission1.getName());
        assertEquals("wrong missionID", "BURGLER", mission2.getName());
        assertEquals("wrong missionID", "INJURED", mission3.getName());
        assertNotEquals("error in missionID", "BURGLER", mission1.getName());
        assertNotEquals("error in missionID", "INJURED", mission2.getName());
        assertNotEquals("error in missionID", "FIRE", mission3.getName());
    }

    /**
     * test to get mission description
     * @throws Exception
     */
    @Test
    public void testGetDescription() throws Exception {
        assertEquals("wrong missionID", "fire", mission1.getDescription());
        assertEquals("wrong missionID", "burgler", mission2.getDescription());
        assertEquals("wrong missionID", "injured", mission3.getDescription());
        assertNotEquals("error in missionID", "wrong description", mission1.getDescription());
        assertNotEquals("error in missionID", "wrong description", mission2.getDescription());
        assertNotEquals("error in missionID", "wrong description", mission3.getDescription());
    }

    /**
     * test to set and get all the assigned teams to this mission
     * @throws Exception
     */
    @Test
    public void testSetandGetTeamsAssigned() throws Exception {
        assertTrue("No NULL", mission1.getTeamsAssigned().isEmpty());
        assertTrue("No NULL", mission2.getTeamsAssigned().isEmpty());
        assertTrue("No NULL", mission3.getTeamsAssigned().isEmpty());
        assertNotEquals("No NULL", teamList1, mission1.getTeamsAssigned());
        assertNotEquals("No NULL", teamList2, mission1.getTeamsAssigned());
        assertNotEquals("No NULL", teamList3, mission1.getTeamsAssigned());
        mission1.setTeamsAssigned(teamList1);
        mission2.setTeamsAssigned(teamList2);
        mission3.setTeamsAssigned(teamList3);
        assertEquals("NULL", teamList1, mission1.getTeamsAssigned());
        assertEquals("NULL", teamList2, mission2.getTeamsAssigned());
        assertEquals("NULL", teamList3, mission3.getTeamsAssigned());
        assertNotEquals("Wrong list", teamList2, mission1.getTeamsAssigned());
        assertNotEquals("Wrong list", teamList3, mission1.getTeamsAssigned());
        assertNotEquals("Wrong list", teamList1, mission2.getTeamsAssigned());
        assertNotEquals("Wrong list", teamList3, mission2.getTeamsAssigned());
        assertNotEquals("Wrong list", teamList1, mission3.getTeamsAssigned());
        assertNotEquals("Wrong list", teamList2, mission3.getTeamsAssigned());
    }

    /**
     * test to add a team to a mission
     * @throws Exception
     */
    @Test
    public void testAddTeamToJob() throws Exception {
        mission1.addTeamToJob(team1);
        mission1.addTeamToJob(team2);
        mission2.addTeamToJob(team2);
        mission2.addTeamToJob(team3);
        mission3.addTeamToJob(team1);
        mission3.addTeamToJob(team3);
        assertEquals("Shouldn't add team", teamList1, mission1.getTeamsAssigned());
        assertEquals("Shouldn't add team", teamList2, mission2.getTeamsAssigned());
        assertEquals("Shouldn't add team", teamList3, mission3.getTeamsAssigned());
        mission1.addTeamToJob(team3);
        mission1.addTeamToJob(team3);
        mission2.addTeamToJob(team1);
        mission3.addTeamToJob(team2);
        assertTrue("Shouldn't add team", teamList4.containsAll(mission1.getTeamsAssigned()));
        assertTrue("Shouldn't add team", teamList4.containsAll(mission2.getTeamsAssigned()));
        assertTrue("Shouldn't add team", teamList4.containsAll(mission1.getTeamsAssigned()));
        assertNotEquals("Shouldn't add team", teamList1, mission1.getTeamsAssigned());
        assertNotEquals("Shouldn't add team", teamList2, mission2.getTeamsAssigned());
        assertNotEquals("Shouldn't add team", teamList3, mission3.getTeamsAssigned());
    }

    /**
     * test to get a missions estimated time
     *
     * @throws Exception
     */
    @Test
    public void testGetEstimatedTime() throws Exception {
        assertEquals("should be the same", 3, mission1.getEstimatedTime());
        assertEquals("should be the same", 4, mission2.getEstimatedTime());
        assertEquals("should be the same", 5, mission3.getEstimatedTime());
        assertNotEquals("shouldn't be the same", 3, mission3.getEstimatedTime());
        assertNotEquals("shouldn't be the same", 5, mission2.getEstimatedTime());
        assertNotEquals("shouldn't be the same", 4, mission1.getEstimatedTime());
    }

    /**
     * @throws Exception
     */
    @Test
    public void testSetEstimatedTime() throws Exception {
        assertEquals("should be the same", 3, mission1.getEstimatedTime());
        assertEquals("should be the same", 4, mission2.getEstimatedTime());
        mission1.setEstimatedTime(6);
        mission2.setEstimatedTime(7);
        assertEquals("should be the same", 6, mission1.getEstimatedTime());
        assertEquals("should be the same", 7, mission2.getEstimatedTime());
        assertNotEquals("shouldn't be the same", 3, mission1.getEstimatedTime());
        assertNotEquals("shouldn't be the same", 4, mission2.getEstimatedTime());
    }


    @Test
    public void testGetMaterialsAssigned() throws Exception {
        Material materiaal = new Material(1, null, null, null, false);
        ArrayList<Material> materialen = new ArrayList<>();
        materialen.add(materiaal);
        mission1.setMaterialsAssigned(materialen);
        assertTrue("material not added", mission1.getMaterialsAssigned().contains(materiaal));
    }

    @Test
    public void testSetName() throws Exception {
        mission1.setName("test");
        assertEquals("wrong name", "test", mission1.getName());
    }
}