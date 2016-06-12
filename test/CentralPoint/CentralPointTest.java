package CentralPoint;

import javafx.collections.ObservableList;
import org.junit.After;
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
        insTestMat();
    }

    /**
     * Tear down method for unittests after running each test
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        delTestMat();
    }

    /**
     * Inserts a test material, for the unittests
     */
    private void insTestMat(){
        centralPoint.insertMaterial("AmbuZiekenwagen", "Ambulance", 12.3, 12.2, false);
    }

    /**
     * Deletes the material, for the unittests
     */
    private void delTestMat(){
        centralPoint.deleteMaterial(centralPoint.getMaterialById(getHighestMatId()));
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
        assertEquals("OnLocation isn't correct", false, m2.isOnLocation());
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
     * test insert material fail without name
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInsertMaterialNameNullFail() {
        centralPoint.insertMaterial(null, "Ambulance", 12.43, 12.66, true);
    }

    /**
     * test insert material fail with empty name
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInsertMaterialNameEmptyFail() {
        centralPoint.insertMaterial(" ", "Ambulance", 12.43, 12.66, true);
    }

    /**
     * test insert material fail without sort
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInsertMaterialSortNullFail() {
        centralPoint.insertMaterial("AmbuZiekenwagen", null, 12.43, 12.66, true);
    }

    /**
     * test insert material fail with empty sort
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInsertMaterialSortEmptyFail() {
        centralPoint.insertMaterial("AmbuZiekenwagen", "   ", 12.43, 12.66, true);
    }

    /**
     * test insert material
     * @throws Exception
     */
    @Test
    public void testInsertMaterial() throws Exception {
        centralPoint.insertMaterial("AmbuZiekenwagen", "Ambulance", 12.3, 12.2, false);
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
     * test update material with same name
     */
    @Test(expected = IllegalStateException.class)
    public void testUpdateMaterialNameNotChanged() {
        centralPoint.updateMaterial(getHighestMatId(), centralPoint.getMaterialById(getHighestMatId()).getName(), "soort", new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test update material without name
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMaterialNameNull() {
        centralPoint.updateMaterial(getHighestMatId(), null, "soort", new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test update material with empty name
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMaterialNameEmpty() {
        centralPoint.updateMaterial(getHighestMatId(), "    ", "soort", new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test update material with same sort
     */
    @Test(expected = IllegalStateException.class)
    public void testUpdateMaterialSortNotChanged() {
        centralPoint.updateMaterial(getHighestMatId(), "blabla", centralPoint.getMaterialById(getHighestMatId()).getSort(), new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test update material without sort
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMaterialSortNull() {
        centralPoint.updateMaterial(getHighestMatId(), "blabla", null, new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test update material with empty sort
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMaterialSortEmpty() {
        centralPoint.updateMaterial(getHighestMatId(), "blabla", "  ", new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test update material with same location
     */
    @Test(expected = IllegalStateException.class)
    public void testUpdateMaterialLocationNotChanged() {
        centralPoint.updateMaterial(getHighestMatId(), "blabla", "soort", centralPoint.getMaterialById(getHighestMatId()).getLocation(), true);
    }

    /**
     * test update material without location
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMaterialLocationNull() {
        centralPoint.updateMaterial(getHighestMatId(), "blabla", "soort", null, true);
    }

    /**
     * test update material with same onLocation
     */
    @Test(expected = IllegalStateException.class)
    public void testUpdateMaterialOnLocNotChanged() {
        centralPoint.updateMaterial(getHighestMatId(), "blabla", "soort", new Point2D.Double(12.2, 12.0), centralPoint.getMaterialById(getHighestMatId()).isOnLocation());
    }

    /**
     * test update material
     * @throws Exception
     */
    @Test
    public void testUpdateMaterial() throws Exception {
        centralPoint.updateMaterial(getHighestMatId(), "blabla", "soort", new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test delete material
     * @throws Exception
     */
    @Test
    public void testDeleteMaterial() throws Exception {
        centralPoint.deleteMaterial(centralPoint.getMaterialById(getHighestMatId()));
    }

    /**
     * get all missions from database
     */
    @Test
    public void testGetAllMissions() {
        ObservableList<Mission> allMissions = centralPoint.getAllMissions();
        assertEquals("There are not enough missions", 11, allMissions.size());
    }

    /**
     * create a new mission
     */
    @Test
    public void testCreateMission() {
        Date date = new Date();
        centralPoint.createMission("Testing mission", "Try out", date, 12.1, 21.2, -1);
        assertEquals("There are not 12 missions", 12, centralPoint.getAllMissions().size());
    }

    /**
     * get all teams from database
     */
    @Test
    public void testGetAllTeams() {
        assertEquals("There are not 10 teams", 10, centralPoint.getAllTeams().size());
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
}