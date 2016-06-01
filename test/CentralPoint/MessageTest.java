package CentralPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Kaj Suiker on 22-3-2016.
 */
public class MessageTest {

    private Message message;

    /**
     * Set up the unittest
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        message = new Message("Backup", "We hebben een extra brandweerwagen nodig!", 0);
    }

    /**
     * tear down the unittest
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {

    }

    /**
     * test to get the message title
     * @throws Exception
     */
    @Test
    public void testGetTitel() throws Exception {
        assertEquals("Title not correctly get", "Backup", message.getTitel());
    }

    /**
     * test to get message
     * @throws Exception
     */
    @Test
    public void testGetMessage() throws Exception {
        assertEquals("Title not correctly get", "We hebben een extra brandweerwagen nodig!", message.getMessage());
    }
}