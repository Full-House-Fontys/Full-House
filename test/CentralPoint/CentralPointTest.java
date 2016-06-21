package CentralPoint;

import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * jvdwi on 22-3-2016.
 */
public class CentralPointTest {

    private CentralPoint centralPoint;
    ObservableList<Material> initialList;
    ObservableList<Staff> staffObservableList;
    Material material;

    /**
     * Set up method for unittests before running each test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        centralPoint = new CentralPoint();
        initialList = centralPoint.getMaterials();
        staffObservableList = centralPoint.getStaffOnLocation();
        material = new Material("Test", "MeerSoorten", new Point2D.Double(12.2, 12.4), true);
    }

    /**
     * Get highest material id, necessary for some tests
     * @return the highest material id
     */
    private int getHighestMatId() {
        int id = 0;
        for (Material m : centralPoint.getMaterials()) {
            if (m.getId() > id) {
                id = m.getId();
            }
        }
        return id;
    }


    /**
     * Returns list of all staff members that are on location
     * @throws Exception
     */
    @Test
    public void testStaffOnLocation() throws Exception {
        assertNotNull("list is not filled from database", centralPoint.getStaffOnLocation());
    }

    /**
     * tests the get material function by id
     * @throws Exception
     */
    @Test
    public void testGetMaterialById() throws Exception {
        Material m2 = centralPoint.getMaterialById(1);
        assertEquals("IDs aren't equal", 1, m2.getId());
        assertEquals("Names aren't equal", "Brandweerwagen Boxtel", m2.getName());
        assertEquals("Sorts aren't equal", "Brandweerwagen", m2.getSort());
        assertEquals("LocationX isn't correct", 51.598757, m2.getLocation().getX(), 0);
        assertEquals("LocationY isn't correct", 5.317385, m2.getLocation().getY(), 0);
        assertEquals("OnLocation isn't correct", true, m2.isOnLocation());
        assertNull("Somehow there is material with id = 0", centralPoint.getMaterialById(0));
        assertNotNull("There isn't a string", m2.getLocationString());
    }

    /**
     * tests the get material function
     * @throws Exception
     */
    @Test
    public void testGetMaterials() throws Exception {
        assertTrue("No materials found", centralPoint.getMaterials().size() > 0);
    }

    /**
     * tests the get availableMaterials function
     *
     * @throws Exception
     */
    @Test
    public void testGetAvailableMaterials() throws Exception {
        assertTrue("No available materials found", centralPoint.getAvailableMaterials().size() > 0);
    }

    /**
     * test add material to mission
     */
    @Test
    public void testAddMaterialToMission() throws Exception {
        Mission mission1 = centralPoint.getAllMissions().get(0);
        Material availableMat1 = centralPoint.getAvailableMaterials().get(0);
        assertEquals("Failed adding material to mission", mission1, centralPoint.addMaterialToMission(availableMat1, mission1));
    }

    /**
     * get all missions from database
     */
    @Test
    public void testGetAllMissions() {
        ObservableList<Mission> allMissions = centralPoint.getAllMissions();
        assertTrue("There are not enough missions", allMissions.size() > 0);
    }

    /**
     * create a new mission
     */
    @Test
    public void testCreateMission() {
        Date date = new Date();
        centralPoint.createMission("Testing mission", "Try out", date, 12.1, 21.2, -1);
        assertTrue("There are not 12 missions", centralPoint.getAllMissions().size() > 0);
    }

    /**
     * get all teams from database
     */
    @Test
    public void testGetAllTeams() {
        assertTrue("There are not 10 teams", centralPoint.getAllTeams().size() > 0);
    }

    /**
     * get all notifications from database
     *
     * @throws Exception
     */
    @Test
    public void getAllNotifications() throws Exception {
        assertTrue("There aren't notifications", centralPoint.getAllNotifications().size() > 0);
    }

    @Test
    public void testGetOutput() throws Exception {

    }

    @Test
    public void testAddTeamsToMission() throws Exception {

    }

    @Test
    public void testAddMaterialsToMission() throws Exception {

    }

    @Test
    public void testGetSpecificTeam() throws Exception {
        ObservableList<Team> teams = centralPoint.getSpecificTeam();
        assertTrue("geen teams gevonden", teams.size() > 0);
    }

    @Test
    public void testCheckExistingUser() throws Exception {

    }

    @Test
    public void testCreateRapport() throws Exception {

    }

    @Test
    public void testGetLastMessages() throws Exception {
    }

    @Test
    public void testCreateserver() throws Exception {

    }

    @Test
    public void testGetMissionFromId() throws Exception {

    }

    @Test
    public void testSendSupportService() throws Exception {

    }

    @Test
    public void testInsertMessage() throws Exception {

    }

    @Test
    public void testInsertMissionPlan() throws Exception {

    }

    @Test
    public void testMissionDone() throws Exception {
        Mission mission = centralPoint.getMissionFromId(1);
        assertNull("zou niet klaar moeten zijn", mission.getEndTime());
        centralPoint.missionDone(1);
        Mission mission1 = centralPoint.getMissionFromId(1);
        assertNotNull("niet klaar", mission1.getEndTime());
    }

    @Test
    public void testGetSteps() throws Exception {
        assertEquals("fout", "Brand Eindhoven Kelgrenstraat 22", centralPoint.getSteps(1));
    }
}