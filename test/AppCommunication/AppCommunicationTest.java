package AppCommunication;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



/**
 *  Kees on 26/04/2016.
 */
public class AppCommunicationTest {

    private AppCommunication appCommunication = new AppCommunication();

    /**
     * set up of unittest
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * test to start the listener
     *
     * @throws Exception
     */
    @Test
    public void startListeners() throws Exception {
        appCommunication.startListeners();
    }

    /**
     * test to stop listener
     * @throws Exception
     */
    @Test
    public void stopListeners() throws Exception {
        appCommunication.stopListeners();
    }


    /**
     * test to consume a request
     * @throws Exception
     */
    @Test
    public void consumeRequest() throws Exception {
        appCommunication.startListeners();
    }

    /**
     * test to consume a message
     * @throws Exception
     */
    @Test
    public void consumeMessage() throws Exception {
        Assert.assertNotNull("Message queue is null", appCommunication.consumeMessage());
    }

}