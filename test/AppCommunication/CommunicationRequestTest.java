package AppCommunication;

import org.junit.Assert;
import org.junit.Test;


/**
 * Kees on 26/04/2016.
 */
public class CommunicationRequestTest {

    CommunicationMessage communicationMessage = new CommunicationMessage("Login/Hallo", "Henk", "Lisa");
    CommunicationRequest communicationRequest = new CommunicationRequest(communicationMessage);

    /**
     * test to get payload of the request
     *
     * @throws Exception
     */
    @Test
    public void getPayload() throws Exception {
        Assert.assertEquals("Payload is not correct", "Hallo", communicationRequest.getPayload());
    }

    /**
     * test to set payload of the request
     *
     * @throws Exception
     */
    @Test
    public void setPayload() throws Exception {
        communicationRequest.setPayload("Hee");
        Assert.assertEquals("Payload is not set correctly", "Hee", communicationRequest.getPayload());
    }

    /**
     * test to get the URL of the request
     * @throws Exception
     */
    @Test
    public void getUrl() throws Exception {
        Assert.assertEquals("URL is not correct", "Login", communicationRequest.getUrl());
    }

    /**
     * test to get the network message of the request
     * @throws Exception
     */
    @Test
    public void getNetworkMessage() throws Exception {
        Assert.assertEquals("Network message is not equals", communicationMessage, communicationRequest.getNetworkMessage());
    }

    /**
     * test to set the URL of the request
     * @throws Exception
     */
    @Test
    public void setUrl() throws Exception {
        communicationRequest.setUrl("http://www.google.nl");
        Assert.assertEquals("URL is not set correctly", "http://www.google.nl", communicationRequest.getUrl());
    }

}