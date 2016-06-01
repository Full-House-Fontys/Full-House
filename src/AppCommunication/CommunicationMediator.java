package AppCommunication;

import CentralPoint.CentralPoint;
import CentralPoint.Mission;
import HulpDienst.TeamRequest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Kaj Suiker on 20-4-2016.
 */
public class CommunicationMediator {
    AppCommunication appCommunication;
    CentralPoint centralPoint;

    //TODO
    public CommunicationMediator(CentralPoint centralPoint) {
        this.centralPoint = centralPoint;
        try {
            appCommunication = new AppCommunication();// CommunicationServerSingleton.getNetworkServer();
            listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO
    public void listen() {
        while (true) {
            CommunicationRequest networkRequest = appCommunication.consumeRequest();

            if (networkRequest != null) {
                //System.out.println(networkRequest.getPayload());
                handle(networkRequest);
            }
        }
    }

    //TODO
    private void handle(CommunicationRequest communicationRequestRequest) {
        System.out.println(communicationRequestRequest.getUrl() + communicationRequestRequest.getPayload());
        switch (communicationRequestRequest.getUrl()) {
            case "login":
                comLogin(communicationRequestRequest);
                break;
            case "getMessages":
                comMessage(communicationRequestRequest);
                break;
            case "sendMessage":
                comSend(communicationRequestRequest);
                break;
            case "requestBackup":
                comRequest(communicationRequestRequest);
                break;
            default:
                System.out.println("error");
        }
    }

    //TODO
    private void comRequest(CommunicationRequest communicationRequestRequest) {
        TeamRequest teamRequest = parseRequest(communicationRequestRequest.getPayload());
        if(teamRequest != null){
            centralPoint.sendSupportService(teamRequest);
        }
    }

    /**
     * parse request, recieved from mobile
     * @param request the request in one long string
     * @return  TeamRequest object for sending to hulpdienst
     */
    private TeamRequest parseRequest(String request){
        TeamRequest teamRequest;
        String name, discription;
        int medic, police, fireman, militaryPolice;
        Mission mission;
        String[] parts = request.split(":");
        name = parts[0];
        discription = parts[1];
        medic = Integer.parseInt(parts[2]);
        police = Integer.parseInt(parts[3]);
        fireman = Integer.parseInt(parts[4]);
        militaryPolice = Integer.parseInt(parts[5]);
        mission = centralPoint.getMissionFromId(Integer.parseInt(parts[6]));
        if(mission != null) {
            return teamRequest = new TeamRequest(name, discription, medic, police, fireman, militaryPolice, mission);
        }
        return null;
    }

    //TODO
    private void comSend(CommunicationRequest communicationRequestRequest) {
        if (centralPoint.insertMessage(communicationRequestRequest.getPayload())) {

        }
    }

    //TODO
    private void comMessage(CommunicationRequest communicationRequestRequest) {
        send("getMessages/" + centralPoint.getLastMessages(), communicationRequestRequest.getNetworkMessage().getSender());
        System.out.println("getMessages/" + centralPoint.getLastMessages());
    }

    /**
     * send mission from login info
     *
     * @param communicationRequestRequest send request
     */
    private void comLogin(CommunicationRequest communicationRequestRequest) {
        int missionId = centralPoint.checkExistingUser(communicationRequestRequest.getPayload().substring(0, communicationRequestRequest.getPayload().indexOf(":")), communicationRequestRequest.getPayload().substring(communicationRequestRequest.getPayload().indexOf(":") + 1));
        if (missionId != -1) {
            send("login/" + missionId, communicationRequestRequest.getNetworkMessage().getSender());
            System.out.println("true");
        } else {
            send("login/" + missionId, communicationRequestRequest.getNetworkMessage().getSender());
            System.out.println("false");
        }
    }

    //TODO
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
