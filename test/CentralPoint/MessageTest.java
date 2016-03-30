package CentralPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

/**
 * Created by Kaj Suiker on 22-3-2016.
 */
public class MessageTest {

    private Message message;

    @Before
    public void setUp() throws Exception {
        message = new Message("Backup", "We hebben een extra brandweerwagen nodig!");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetTitel() throws Exception {
        assertEquals("Title not correctly get", "Backup", message.getTitel());
    }

    @Test
    public void testGetMessage() throws Exception {
        assertEquals("Title not correctly get", "We hebben een extra brandweerwagen nodig!", message.getMessage());
    }
}