package CentralPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Kaj Suiker on 22-3-2016.
 */
public class TeamTest {

    private Team team;
    private ArrayList<Staff> teamMembers = new ArrayList<>();
    private ArrayList<Integer> missionID = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        Staff steff = new Staff(1, "Steff", "", "Doorenmalen", "Stefferd", "ww", new Point2D.Double(1.123456, 1.654321), "Brandweer", false);
        teamMembers.add(steff);
        steff = new Staff(2, "Frits", "de", "Man", "FdM", "passwoord", new Point2D.Double(6.54321,8.888336), "Politie", false);
        teamMembers.add(steff);
        missionID.add(1); missionID.add(5); missionID.add(9);
        team = new Team(100, "Team 1", teamMembers, missionID);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetName() throws Exception {
        team.setName("Team 1");
        assertEquals("Teamname not equals", "Team 1", team.getName());
    }

    @Test
    public void testSetName() throws Exception {
        team.setName("Team 2");
        assertEquals("Teamname not equals", "Team 2", team.getName());
    }

    @Test
    public void testGetTeamMembers() throws Exception {
        assertEquals("Does not contain right staff", teamMembers, team.getTeamMembers());
    }

    @Test
    public void testSetTeamMembers() throws Exception {
        Staff steffert = new Staff(1, "Steffert", "van", "Huizen", "Sufferd", "ww2", new Point2D.Double(1.234561,1.652431), "EHBO", true);
        team.addMember(steffert);
        assertTrue("Does not contain right team", team.getTeamMembers().contains(steffert));
    }

    @Test
    public void testToString() throws Exception {
        team.setName("Team 2");
        assertEquals("Does not give the right string", "Team : Team 2", team.toString());
    }
}