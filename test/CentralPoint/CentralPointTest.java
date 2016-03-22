package CentralPoint;

import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

/**
 * Created by jvdwi on 22-3-2016.
 */
public class CentralPointTest {
    
    private CentralPoint cp;
    ObservableList<Material> initialList;
    Material m1;

    /**
     * set up method for unittests before running each test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        cp = new CentralPoint();
        initialList = cp.getMaterials();
        m1 = new Material("Test", "MeerSoorten", new Point2D.Double(12.2, 12.4), true);
        insTestMat();
    }

    /**
     * tear down method for unittests after running each test
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        delTestMat();
    }

    /**
     * inserts a test material, for the unittests
     */
    private void insTestMat(){
        cp.insertMaterial("AmbuZiekenwagen", "Ambulance", 12.3, 12.2, false);
    }

    /**
     * deletes the material, for the unittests
     */
    private void delTestMat(){
        cp.deleteMaterial(cp.getMaterialById(getHighestMatId()));
    }

    /**
     * get highest material id, necessary for some tests
     * @return
     */
    private int getHighestMatId() {
        int id = 0;
        for (Material m : cp.getMaterials()) {
            if (m.getId() > id) {
                id = m.getId();
            }
        }
        return id;
    }


    @Test
    public void testStaffOnLocation() throws Exception {

    }

    @Test
    public void testSetStaffOnLocation() throws Exception {

    }

    /**
     * tests the get material function by id
     * @throws Exception
     */
    @Test
    public void testGetMaterialById() throws Exception {
        Material m2 = cp.getMaterialById(1);
        assertEquals("ID's zijn niet gelijk", 1, m2.getId());
        assertEquals("Namen zijn niet gelijk", "Brandweerwagen Boxtel", m2.getName());
        assertEquals("Soorten zijn niet gelijk", "Brandweerwagen", m2.getSort());
        assertEquals("LocatieX is niet correct", 192.4599210000, m2.getLocation().getX(), 0);
        assertEquals("LocatieY is niet correct", 5.5542801000, m2.getLocation().getY(), 0);
        assertEquals("Op locatie is niet correct", false, m2.isOnLocation());
        assertNull("Er is wel een materiaal met ID 0", cp.getMaterialById(0));
        assertNotNull("Er komt geen string uit", m2.getLocationString());
    }

    /**
     * tests the get material function
     * @throws Exception
     */
    @Test
    public void testGetMaterials() throws Exception {
        assertTrue("Er zijn geen materialen", cp.getMaterials().size() > 0);
    }

    /**
     * test insert material fail without name
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInsertMaterialNameNullFail() {
        cp.insertMaterial(null, "Ambulance", 12.43, 12.66, true);
    }

    /**
     * test insert material fail with empty name
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInsertMaterialNameEmptyFail() {
        cp.insertMaterial(" ", "Ambulance", 12.43, 12.66, true);
    }

    /**
     * test insert material fail without sort
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInsertMaterialSortNullFail() {
        cp.insertMaterial("AmbuZiekenwagen", null, 12.43, 12.66, true);
    }

    /**
     * test insert material fail with empty sort
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInsertMaterialSortEmptyFail() {
        cp.insertMaterial("AmbuZiekenwagen", "   ", 12.43, 12.66, true);
    }

    /**
     * test insert material
     * @throws Exception
     */
    @Test
    public void testInsertMaterial() throws Exception {
        cp.insertMaterial("AmbuZiekenwagen", "Ambulance", 12.3, 12.2, false);
    }

    /**
     * test update material with same name
     */
    @Test(expected = IllegalStateException.class)
    public void testUpdateMaterialNameNotChanged() {
        cp.updateMaterial(getHighestMatId(), cp.getMaterialById(getHighestMatId()).getName(), "soort", new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test update material without name
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMaterialNameNull() {
        cp.updateMaterial(getHighestMatId(), null, "soort", new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test update material with empty name
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMaterialNameEmpty() {
        cp.updateMaterial(getHighestMatId(), "    ", "soort", new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test update material with same sort
     */
    @Test(expected = IllegalStateException.class)
    public void testUpdateMaterialSortNotChanged() {
        cp.updateMaterial(getHighestMatId(), "blabla", cp.getMaterialById(getHighestMatId()).getSort(), new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test update material without sort
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMaterialSortNull() {
        cp.updateMaterial(getHighestMatId(), "blabla", null, new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test update material with empty sort
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMaterialSortEmpty() {
        cp.updateMaterial(getHighestMatId(), "blabla", "  ", new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test update material with same location
     */
    @Test(expected = IllegalStateException.class)
    public void testUpdateMaterialLocationNotChanged() {
        cp.updateMaterial(getHighestMatId(), "blabla", "soort", cp.getMaterialById(getHighestMatId()).getLocation(), true);
    }

    /**
     * test update material without location
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMaterialLocationNull() {
        cp.updateMaterial(getHighestMatId(), "blabla", "soort", null, true);
    }

    /**
     * test update material with same onLocation
     */
    @Test(expected = IllegalStateException.class)
    public void testUpdateMaterialOnLocNotChanged() {
        cp.updateMaterial(getHighestMatId(), "blabla", "soort", new Point2D.Double(12.2, 12.0), cp.getMaterialById(getHighestMatId()).isOnLocation());
    }

    /**
     * test update material
     * @throws Exception
     */
    @Test
    public void testUpdateMaterial() throws Exception {
        cp.updateMaterial(getHighestMatId(), "blabla", "soort", new Point2D.Double(12.2, 12.0), true);
    }

    /**
     * test delete material
     * @throws Exception
     */
    @Test
    public void testDeleteMaterial() throws Exception {
        cp.deleteMaterial(cp.getMaterialById(getHighestMatId()));
    }
}