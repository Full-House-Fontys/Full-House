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
    private Material m;

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
        assertEquals("ids aren't equal", 1, m.getId());
    }

    /**
     * test getting name
     * @throws Exception
     */
    @Test
    public void testGetName() throws Exception {
        assertEquals("names aren't equal", "bla", m.getName());
    }

    /**
     * test setting name
     * @throws Exception
     */
    @Test
    public void testSetName() throws Exception {
        m.setName("bla2");
        assertEquals("name not changed", "bla2", m.getName());
    }

    /**
     * test getting sort
     * @throws Exception
     */
    @Test
    public void testGetSort() throws Exception {
        assertEquals("sorts aren't equal", "bla", m.getSort());
    }

    /**
     * test setting sort
     * @throws Exception
     */
    @Test
    public void testSetSort() throws Exception {
        m.setSort("bla2");
        assertEquals("sort not changed", "bla2", m.getSort());
    }

    /**
     * test getting location
     * @throws Exception
     */
    @Test
    public void testGetLocation() throws Exception {
        assertEquals("locations aren't equal", new Point2D.Double(12.2, 12.3), m.getLocation());
    }

    /**
     * test getting locationString
     * @throws Exception
     */
    @Test
    public void testGetLocationString() throws Exception {
        assertEquals("String is incorrect", "12.2; 12.3", m.getLocationString());
    }

    /**
     * test setting location
     * @throws Exception
     */
    @Test
    public void testSetLocation() throws Exception {
        m.setLocation(new Point2D.Double(0.0, 0.0));
        assertEquals("location not changed", new Point2D.Double(0.0, 0.0), m.getLocation());
    }

    /**
     * test getting onLocation
     * @throws Exception
     */
    @Test
    public void testIsOnLocation() throws Exception {
        assertEquals("onLocation isn't correct", false, m.isOnLocation());
    }

    /**
     * test setting onLocation
     * @throws Exception
     */
    @Test
    public void testSetOnLocation() throws Exception {
        m.setOnLocation(true);
        assertEquals("onLocation not changed", true, m.isOnLocation());
    }
}