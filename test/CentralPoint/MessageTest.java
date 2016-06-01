package CentralPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kaj Suiker on 22-3-2016.
 */
public class MessageTest {

    private Message message;

    //TODO JAVADOC
    @Before
    public void setUp() throws Exception {
        message = new Message("Backup", "We hebben een extra brandweerwagen nodig!", 0);
    }

    //TODO JAVADOC
    @After
    public void tearDown() throws Exception {

    }

    //TODO JAVADOC
    @Test
    public void testGetTitel() throws Exception {
        assertEquals("Title not correctly get", "Backup", message.getTitel());
    }

    //TODO JAVADOC
    @Test
    public void testGetMessage() throws Exception {
        assertEquals("Title not correctly get", "We hebben een extra brandweerwagen nodig!", message.getMessage());
    }
}