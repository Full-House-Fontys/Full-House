package AppCommunication;

import CentralPoint.CentralPoint;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Kaj Suiker on 15-6-2016.
 */
public class CommunicationMediatorTest {

    CommunicationMediator communicationMediator;
    CentralPoint centralPoint;

    @Before
    public void setUp() throws Exception {
        centralPoint = new CentralPoint();
        communicationMediator = new CommunicationMediator(centralPoint);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testHandle() throws Exception {
        String receiver = "0", sender = "0";
        CommunicationMessage com;
        com = new CommunicationMessage("login/h:h", sender, receiver);
        communicationMediator.handle(new CommunicationRequest(com));
        com = new CommunicationMessage("getMessages/2", sender, receiver);
        communicationMediator.handle(new CommunicationRequest(com));
        com = new CommunicationMessage("sendMessage/testmessage2", sender, receiver);
        communicationMediator.handle(new CommunicationRequest(com));
        com = new CommunicationMessage("requestBackup/:name:0:0:0:0:0:2", sender, receiver);
        communicationMediator.handle(new CommunicationRequest(com));
        com = new CommunicationMessage("getSteps/2", sender, receiver);
        communicationMediator.handle(new CommunicationRequest(com));
    }

    @Test
    public void testSend() throws Exception {
        communicationMediator.send("getMessage/hoi", "0.0.0.0");
    }
}