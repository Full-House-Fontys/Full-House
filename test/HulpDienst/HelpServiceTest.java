package HulpDienst;

import CentralPoint.Staff;
import CentralPoint.Team;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Mark on 1-6-2016.
 */
public class HelpServiceTest {

    private HelpService HS;

    /**
     * the set up that needs to be done before every test
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        HS = new HelpService();
    }

    /**
     * test to get the newest staff list
     *
     * @throws Exception
     */
    @Test
    public void testRenewStaffList() throws Exception {
        ObservableList<Staff> staffObservableList;
        staffObservableList = HS.renewStaffList();
        assertTrue("no database items found", staffObservableList.size() > 0);
    }

    /**
     * test to get all existing teams
     * @throws Exception
     */
    @Test
    public void testGetAllTeams() throws Exception {
        ObservableList<Team> teamObservableList;
        teamObservableList = HS.getAllTeams();
        assertTrue("no database items found", teamObservableList.size() > 0);
    }

    /**
     * test to get all teamrequests that have been send
     * @throws Exception
     */
    @Test
    public void testGetTeamRequests() throws Exception {
        ObservableList<TeamRequest> teamRQObservableList;
        teamRQObservableList = HS.getTeamRequests();
        assertTrue("items cannot exist", teamRQObservableList.size() == 0);
    }

    /**
     * Test to filter staff based on job
     * @throws Exception
     */
    @Test
    public void testFilterStaffList() throws Exception {
        ObservableList<Staff> staffObservableList;
        staffObservableList = HS.filterStaffList("Brandweer");
        for (Staff staff : staffObservableList) {
            assertTrue("Wrong returned value", staff.getSort().equals("Brandweer"));
        }
        staffObservableList = HS.filterStaffList("Politi");
        for (Staff staff : staffObservableList) {
            assertTrue("Wrong returned value", staff.getSort().equals("Politi"));
        }
        staffObservableList = HS.filterStaffList("EHBO");
        for (Staff staff : staffObservableList) {
            assertTrue("Wrong returned value", staff.getSort().equals("EHBO"));
        }
        staffObservableList = HS.filterStaffList("Alle");
        assertTrue("No value returned", staffObservableList.size() > 0);

    }

    /**
     * test to create a new team and check if it excists in the database
     * @throws Exception
     */
    @Test
    public void testCreateTeam() throws Exception {
        Staff staff = new Staff(1, "Kaj", "", "Adams", "p_adams", "jkl(USDj2", null, "Brandweer", true, 3, 5);
        List<Staff> staffList = new ArrayList<>();
        staffList.add(staff);
        Team team = new Team(HS.getAllTeams().size() + 1, "testteam1", (ArrayList<Staff>) staffList, null);
        HS.createTeam(team);
        ObservableList<Team> teamObservableList;
        teamObservableList = HS.getAllTeams();
        boolean gevonden = false;
        for (Team t : teamObservableList) {
            if (t.getName().equals("testteam1")) {
                gevonden = true;
            }
        }
        assertTrue("testteam not found in database", gevonden);
    }

    /**
     * test to add a mission to a team
     * @throws Exception
     */
    @Test
    public void testAddMissionToTeam() throws Exception {
        Staff staff = new Staff(0, "Mark", "", "test", "test", "jkl(USDj2", null, "Brandweer", true, 3, 5);
        List<Staff> staffList = new ArrayList<>();
        staffList.add(staff);
        Team team = new Team(HS.getAllTeams().size() + 1, "testteam1", (ArrayList<Staff>) staffList, null);
        HS.createTeam(team);
        HS.addMissionToTeam(team, 1);
        ObservableList<Team> teamObservableList;
        teamObservableList = HS.getAllTeams();
        for (Team t : teamObservableList)
            if (t.getName().equals("testteam1")) {
                assertTrue("mission not added to team", t.getMissionID().size() > 0);
            }
    }

    /**
     * test to renew the teams list.
     * @throws Exception
     */
    @Test
    public void testRenewteams() throws Exception {
        ObservableList<Team> teamObservableList;
        teamObservableList = HS.renewteams();
        assertTrue("no database items found", teamObservableList.size() > 0);
    }

}