package AppCommunication;

import CentralPoint.CentralPoint;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Kaj Suiker on 20-4-2016.
 */
public class CommunicationMediator {
    AppCommunication appCommunication;
    CentralPoint centralPoint;

    public CommunicationMediator(CentralPoint centralPoint) {
        this.centralPoint = centralPoint;
        try {
            appCommunication = new AppCommunication();// CommunicationServerSingleton.getNetworkServer();
            listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        while (true) {
            CommunicationRequest networkRequest = appCommunication.consumeRequest();

            if (networkRequest != null)
                handle(networkRequest);
        }
    }

    private void handle(CommunicationRequest communicationRequestRequest) {
        switch (communicationRequestRequest.getUrl()) {
            case "login":
                comLogin(communicationRequestRequest);
                break;
            case "getMessages":
                comMessage(communicationRequestRequest);
                break;
            default:
                System.out.println("error");
        }
    }

    private void comMessage(CommunicationRequest communicationRequestRequest) {
        send(centralPoint.getLastMessages(), communicationRequestRequest.getNetworkMessage().getSender());
    }

    private void comLogin(CommunicationRequest communicationRequestRequest){
        if(centralPoint.checkExistingUser(communicationRequestRequest.getPayload().substring(0,communicationRequestRequest.getPayload().indexOf(":")), communicationRequestRequest.getPayload().substring(communicationRequestRequest.getPayload().indexOf(":")+1))){
            send("true",communicationRequestRequest.getNetworkMessage().getSender());
        }else {
            send("false",communicationRequestRequest.getNetworkMessage().getSender());
        }
    }

    public boolean send(String message, String receiver) {
        try {
            Socket socket = new Socket(receiver, 8080);//this.getPort());

            // Get input streams
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            outputStream.writeBytes(message);

            outputStream.close();
            socket.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
