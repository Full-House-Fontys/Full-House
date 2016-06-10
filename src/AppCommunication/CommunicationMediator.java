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
    private AppCommunication appCommunication;
    private CentralPoint centralPoint;

    /**
     * Constructor, also makes a new instance of AppCommunication.
     * @param centralPoint : The central point this communication is made for.
     */
    public CommunicationMediator(CentralPoint centralPoint) {
        this.centralPoint = centralPoint;
        try {
            appCommunication = new AppCommunication();
            listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Will check the CommunicationRequest constantly for new requests.
     * If a request is detected, the method 'handle' is executed.
     */
    public void listen() {
        while (true) {
            CommunicationRequest networkRequest = appCommunication.consumeRequest();

            if (networkRequest != null) {
                handle(networkRequest);
            }
        }
    }

    /**
     * Based on different type of communicationRequest, the right method will be called.
     * Possible outcomes are: login, getMessages, sendMessage and requestBackup.
     * @param communicationRequestRequest : The request that needs to be executed.
     */
    private void handle(CommunicationRequest communicationRequestRequest) {
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

    /**
     * Creates a TeamRequest and send it to Helpservice
     * @param communicationRequestRequest Used for payload, data to create TeamRequest.
     */
    private void comRequest(CommunicationRequest communicationRequestRequest) {
        TeamRequest teamRequest = parseRequest(communicationRequestRequest.getPayload());
        if(teamRequest != null){
            centralPoint.sendSupportService(teamRequest);
        }
    }

    /**
     * parse request, received from mobile
     * @param request : the request in one long String
     * @return  TeamRequest object for sending to HelpService
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

    //TODO finish

    /**
     * @param communicationRequestRequest Used to get data from mobile app
     */
    private void comSend(CommunicationRequest communicationRequestRequest) {
        if (centralPoint.insertMessage(communicationRequestRequest.getPayload())) {

        }
    }

    /**
     * sends last 5 messages from database to mobile app
     * @param communicationRequestRequest
     */
    private void comMessage(CommunicationRequest communicationRequestRequest) {
        send("getMessages/" + centralPoint.getLastMessages(), communicationRequestRequest.getNetworkMessage().getSender());
        System.out.println("getMessages/" + centralPoint.getLastMessages());//TODO delete sout
    }

    /**
     * send mission from login info
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

    /**
     * Socket connection to send answers back to the mobile apps
     * @param message message to send back
     * @param receiver client IP
     * @return true/false for success
     */
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
