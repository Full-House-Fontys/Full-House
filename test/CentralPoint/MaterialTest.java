package CentralPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

/**
 * Created by jvdwi on 22-3-2016.
 */
public class MaterialTest {
    Material m;

    /**
     * set up the unittest
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        m = new Material(1, "bla", "bla", new Point2D.Double(12.2, 12.3), false);
        Material m2 = new Material("bla", "bla2", new Point2D.Double(12.2, 12.3), true);
    }

    /**
     * teardown the unittest
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {

    }

    /**
     * test getting id
     * @throws Exception
     */
    @Test
    public void testGetId() throws Exception {
        assertEquals("id niet gelijk", 1, m.getId());
    }

    /**
     * test getting name
     * @throws Exception
     */
    @Test
    public void testGetName() throws Exception {
        assertEquals("namen niet gelijk", "bla", m.getName());
    }

    /**
     * test setting name
     * @throws Exception
     */
    @Test
    public void testSetName() throws Exception {
        m.setName("bla2");
        assertEquals("naam is niet aangepast", "bla2", m.getName());
    }

    /**
     * test getting sort
     * @throws Exception
     */
    @Test
    public void testGetSort() throws Exception {
        assertEquals("soorten niet gelijk", "bla", m.getSort());
    }

    /**
     * test setting sort
     * @throws Exception
     */
    @Test
    public void testSetSort() throws Exception {
        m.setSort("bla2");
        assertEquals("soort is niet aangepast", "bla2", m.getSort());
    }

    /**
     * test getting location
     * @throws Exception
     */
    @Test
    public void testGetLocation() throws Exception {
        assertEquals("locatie is niet gelijk", new Point2D.Double(12.2, 12.3), m.getLocation());
    }

    /**
     * test getting locationString
     * @throws Exception
     */
    @Test
    public void testGetLocationString() throws Exception {
        assertEquals("string klopt niet", "12.2; 12.3", m.getLocationString());
    }

    /**
     * test setting location
     * @throws Exception
     */
    @Test
    public void testSetLocation() throws Exception {
        m.setLocation(new Point2D.Double(0.0, 0.0));
        assertEquals("location is niet aangepast", new Point2D.Double(0.0, 0.0), m.getLocation());
    }

    /**
     * test getting onLocation
     * @throws Exception
     */
    @Test
    public void testIsOnLocation() throws Exception {
        assertEquals("onLocation klopt niet", false, m.isOnLocation());
    }

    /**
     * test setting onLocation
     * @throws Exception
     */
    @Test
    public void testSetOnLocation() throws Exception {
        m.setOnLocation(true);
        assertEquals("onLocation is niet aangepast", true, m.isOnLocation());
    }
}