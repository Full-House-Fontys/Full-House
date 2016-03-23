package GUI;

import CentralPoint.Mission;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Qunfo on 23-3-2016.
 */
public class fireController {
    @FXML
    private Label
            lbReceived;

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
            ArrayList<Mission> missions = (ArrayList<Mission>) ois.readObject();
            if (missions != null) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lbReceived.setText("Received Missions from server");
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

}
