package CentralPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * jvdwi on 22-3-2016.
 */
public class MaterialTest {
    private Material material;

    /**
     * Set up the unittest
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        material = new Material(1, "bla", "bla", new Point2D.Double(12.2222, 12.3), false);
        new Material("bla", "bla2", new Point2D.Double(12.2, 12.3), true);
    }

    /**
     * Teardown the unittest
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test getting id
     *
     * @throws Exception
     */
    @Test
    public void testGetId() throws Exception {
        assertEquals("ids aren't equal", 1, material.getId());
    }

    /**
     * Test getting name
     *
     * @throws Exception
     */
    @Test
    public void testGetName() throws Exception {
        assertEquals("names aren't equal", "bla", material.getName());
    }

    /**
     * Test setting name
     *
     * @throws Exception
     */
    @Test
    public void testSetName() throws Exception {
        material.setName("bla2");
        assertEquals("name not changed", "bla2", material.getName());
    }

    /**
     * Test getting sort
     *
     * @throws Exception
     */
    @Test
    public void testGetSort() throws Exception {
        assertEquals("sorts aren't equal", "bla", material.getSort());
    }

    /**
     * Test setting sort
     *
     * @throws Exception
     */
    @Test
    public void testSetSort() throws Exception {
        material.setSort("bla2");
        assertEquals("sort not changed", "bla2", material.getSort());
    }

    /**
     * Test getting location
     *
     * @throws Exception
     */
    @Test
    public void testGetLocation() throws Exception {
        assertEquals("locations aren't equal", new Point2D.Double(12.2222, 12.3), material.getLocation());
    }

    /**
     * Test getting locationString
     *
     * @throws Exception
     */
    @Test
    public void testGetLocationString() throws Exception {
        assertEquals("String is incorrect", "12.22; 12.3", material.getLocationString());
    }

    /**
     * Test setting location
     *
     * @throws Exception
     */
    @Test
    public void testSetLocation() throws Exception {
        material.setLocation(new Point2D.Double(0.0, 0.0));
        assertEquals("location not changed", new Point2D.Double(0.0, 0.0), material.getLocation());
    }

    /**
     * Test getting distance
     *
     * @throws Exception
     */
    @Test
    public void testGetDistance() throws Exception {
        assertEquals("Distance is mistakenly already setup", "0.0 km", material.getDistance());
    }

    /**
     * Test setting distance
     *
     * @throws Exception
     */
    @Test
    public void testSetDistance() throws Exception {
        material.setDistance(new Point2D.Double(52.1, 5.4));
        assertEquals("Distance is incorrectly calculated", "4477.03 km", material.getDistance());
    }

    /**
     * Test setting missionIds
     *
     * @throws Exception
     */
    @Test
    public void testSetMissionIds() throws Exception {
        ArrayList<Integer> missionIds = new ArrayList<>();
        missionIds.add(1);
        missionIds.add(2);
        material.setMissionIds(missionIds);
    }

    /**
     * Test getting onLocation
     *
     * @throws Exception
     */
    @Test
    public void testIsOnLocation() throws Exception {
        assertEquals("onLocation isn't correct", false, material.isOnLocation());
    }

    /**
     * Test setting onLocation
     *
     * @throws Exception
     */
    @Test
    public void testSetOnLocation() throws Exception {
        material.setOnLocation(true);
        assertEquals("onLocation not changed", true, material.isOnLocation());
    }

    /**
     * test to get the to string method of material.
     *
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        assertEquals("tostring wrong", "bla", material.toString());
    }
}