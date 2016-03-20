package GUI;

import DBElements.Material;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

/**
 * Created by jvdwi on 20-3-2016.
 */
public class MaterialManagerTest {
    private MaterialManager mm;
    ObservableList<Material> initialList;
    Material m1;

    @Before
    public void setUp() throws Exception {
        mm = new MaterialManager();
        initialList = mm.getMaterials();
        m1 = new Material("Test", "MeerSoorten", new Point2D.Double(12.2, 12.4), true);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRenewMaterials() throws Exception {
        mm.renewMaterials();
    }

    @Test
    public void testGetMaterialById() throws Exception {
        mm.renewMaterials();
        Material m2 = mm.getMaterialById(1);
        assertEquals("ID's zijn niet gelijk", 1, m2.getId());
        assertEquals("Namen zijn niet gelijk", "Brandweerwagen Boxtel", m2.getName());
        assertEquals("Soorten zijn niet gelijk", "Brandweerwagen", m2.getSort());
        assertEquals("LocatieX is niet correct", 192.4599210000, m2.getLocation().getX(),0);
        assertEquals("LocatieY is niet correct", 5.5542801000, m2.getLocation().getY(), 0);
        assertEquals("Op locatie is niet correct", false, m2.isOnLocation());
        assertNull("Er is wel een materiaal met ID 0", mm.getMaterialById(0));
    }

    @Test
    public void testGetMaterials() throws Exception {
        mm.renewMaterials();
        assertTrue("Er zijn geen materialen", mm.getMaterials().size() > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertMaterialNameNullFail(){
        mm.insertMaterial(null, "Ambulance", 12.43, 12.66, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertMaterialNameEmptyFail(){
        mm.insertMaterial(" ", "Ambulance", 12.43, 12.66, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertMaterialSortNullFail(){
        mm.insertMaterial("AmbuZiekenwagen", null, 12.43, 12.66, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertMaterialSortEmptyFail(){
        mm.insertMaterial("AmbuZiekenwagen", "   ", 12.43, 12.66, true);
    }

    @Test
    public void testInsertMaterial() throws Exception {
        mm.insertMaterial("AmbuZiekenwagen", "Ambulance", 12.3, 12.2, false);
    }

    @Test
    public void testUpdateMaterial() throws Exception {

    }

    @Test
    public void testDeleteMaterial() throws Exception {

    }
}