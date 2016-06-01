package AppCommunication;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kees on 26/04/2016.
 */
public class CommunicationServerSingletonTest {
    //TODO JAVADOC
    @Test
    public void getNetworkServer() throws Exception {
        CommunicationServerSingleton communicationServerSingleton = new CommunicationServerSingleton();
        Assert.assertNotNull(communicationServerSingleton.getNetworkServer());
    }

}