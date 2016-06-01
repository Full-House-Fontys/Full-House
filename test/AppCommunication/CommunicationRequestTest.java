package AppCommunication;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kees on 26/04/2016.
 */
public class CommunicationRequestTest {

    CommunicationMessage communicationMessage = new CommunicationMessage("Login/Hallo", "Henk", "Lisa");
    CommunicationRequest communicationRequest = new CommunicationRequest(communicationMessage);

    //TODO JAVADOC
    @Test
    public void getPayload() throws Exception {
        Assert.assertEquals("Payload is not correct", "Hallo", communicationRequest.getPayload());
    }

    //TODO JAVADOC
    @Test
    public void setPayload() throws Exception {
        communicationRequest.setPayload("Hee");
        Assert.assertEquals("Payload is not set correctly", "Hee", communicationRequest.getPayload());
    }

    //TODO JAVADOC
    @Test
    public void getUrl() throws Exception {
        Assert.assertEquals("URL is not correct", "Login", communicationRequest.getUrl());
    }

    //TODO JAVADOC
    @Test
    public void getNetworkMessage() throws Exception {
        Assert.assertEquals("Network message is not equals", communicationMessage, communicationRequest.getNetworkMessage());
    }

    //TODO JAVADOC
    @Test
    public void setUrl() throws Exception {
        communicationRequest.setUrl("http://www.google.nl");
        Assert.assertEquals("URL is not set correctly", "http://www.google.nl", communicationRequest.getUrl());
    }

}