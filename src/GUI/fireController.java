package GUI;

import CentralPoint.Mission;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;


import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by Qunfo on 23-3-2016.
 */
public class fireController {
    @FXML
    private Label lbReceived;
    @FXML
    private ListView lvNewMissions;

    public fireController() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               receiveSocket();
            }
        },0,1000);
    }

    private void receiveSocket() {
        int port = 2002;
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket s = ss.accept();
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            HashMap missions = (HashMap<Mission, String>) ois.readObject();
            if (missions != null) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lbReceived.setText("Received Missions from server");
                        showReceivedMissions(missions);
                    }
                });
            }
            is.close();
            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void showReceivedMissions(HashMap<Mission,String> newMissions) {
        ObservableList<String> newMission = FXCollections.observableArrayList();
        for(Map.Entry<Mission,String> mission : newMissions.entrySet()){
            newMission.add(mission.getKey().getName());
        }
        lvNewMissions.setItems(newMission);
    }

}
