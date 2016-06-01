package AppCommunication;

import org.junit.Assert;
import org.junit.Test;


/**
 * Kees on 26/04/2016.
 */
public class CommunicationServerSingletonTest {

    /**
     * test to get the network server
     *
     * @throws Exception
     */
    @Test
    public void getNetworkServer() throws Exception {
        CommunicationServerSingleton communicationServerSingleton = new CommunicationServerSingleton();
        Assert.assertNotNull(CommunicationServerSingleton.getNetworkServer());
    }

}