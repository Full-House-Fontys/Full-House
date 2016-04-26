package AppCommunication;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kees on 26/04/2016.
 */
public class AppCommunicationTest {

    AppCommunication appCommunication = new AppCommunication();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void startListeners() throws Exception {
        appCommunication.startListeners();
    }

    @Test
    public void stopListeners() throws Exception {
        appCommunication.stopListeners();
    }

    @Test
    public void consumeRequest() throws Exception {
        appCommunication.startListeners();
    }

    @Test
    public void consumeMessage() throws Exception {
        Assert.assertNotNull("Message queue is null", appCommunication.consumeMessage());
    }

}