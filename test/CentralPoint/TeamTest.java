package CentralPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Kaj Suiker on 22-3-2016.
 */
public class TeamTest {

    private Team team;
    private ArrayList<Staff> teamMembers = new ArrayList<>();
    private ArrayList<Integer> missionID = new ArrayList<>();

    /**
     * set up of unittest
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        Staff steff = new Staff(1, "Steff", "", "Doorenmalen", "Stefferd", "ww", new Point2D.Double(1.123456, 1.654321), "Brandweer", false);
        teamMembers.add(steff);
        steff = new Staff(2, "Frits", "de", "Man", "FdM", "passwoord", new Point2D.Double(6.54321, 8.888336), "Politie", false);
        teamMembers.add(steff);
        missionID.add(1);
        missionID.add(5);
        missionID.add(9);
        team = new Team(100, "Team 1", teamMembers, missionID);
    }

    /**
     * tear down of unittest
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {

    }

    /**
     * test to get team name
     *
     * @throws Exception
     */
    @Test
    public void testGetName() throws Exception {
        team.setName("Team 1");
        assertEquals("Teamname not equals", "Team 1", team.getName());
    }

    /**
     * test to set team name
     *
     * @throws Exception
     */
    @Test
    public void testSetName() throws Exception {
        team.setName("Team 2");
        assertEquals("Teamname not equals", "Team 2", team.getName());
    }

    /**
     * test to get all staffmembers
     *
     * @throws Exception
     */
    @Test
    public void testGetTeamMembers() throws Exception {
        assertEquals("Does not contain right staff", teamMembers, team.getTeamMembers());
    }

    /**
     * test to set all staffmembers
     *
     * @throws Exception
     */
    @Test
    public void testSetTeamMembers() throws Exception {
        Staff steffert = new Staff(1, "Steffert", "van", "Huizen", "Sufferd", "ww2", new Point2D.Double(1.234561, 1.652431), "EHBO", true);
        Staff tom = new Staff(1, "tom", "van", "Huizen", "Sufferd", "ww2", new Point2D.Double(1.234561, 1.652431), "EHBO", true);
        team.addMember(steffert);
        assertTrue("Does not contain right team", team.getTeamMembers().contains(steffert));
        ArrayList<Staff> stafflist = new ArrayList<>();
        stafflist.add(tom);
        team.setTeamMembers(stafflist);
        assertTrue("Does not contain right team", team.getTeamMembers().contains(tom));
    }

    /**
     * test the tostring method of team
     *
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        team.setName("Team 2");
        assertEquals("Does not give the right string", "Team : Team 2", team.toString());
    }

    /**
     * test to set a teams mission id
     *
     * @throws Exception
     */
    @Test
    public void testSetMissionID() throws Exception {
        ArrayList<Integer> missions = new ArrayList<>();
        missions.add(2);
        team.setMissionID(missions);
        assertTrue("wrong id", team.getMissionID().contains(2));
    }

    /**
     * test to set a teams id
     *
     * @throws Exception
     */
    @Test
    public void testSetId() throws Exception {
        team.setId(2);
        assertEquals("wrong id", 2, team.getId());
    }
}