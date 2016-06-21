package GUI;


import AppCommunication.CommunicationMediator;
import CentralPoint.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Qunfo on 29-3-2016.
 */
public class mainController implements Initializable {

    @FXML
    private ListView lvMissions;
    @FXML
    private ListView lvTeams;
    @FXML
    private ListView lvNotifications;
    @FXML
    private TextArea taInfo;
    @FXML
    private ListView lvMessage;
    @FXML
    private TextField tfMessage;

    private CentralPoint centralPoint;
    private ObservableList<Mission> missionListObservable;
    private ObservableList<Team> teamListObservable;
    private ObservableList<Notification> notificationObservableList;
    private ObservableList<String> messages;

    private Timer keepNotificationsUpToDate;

    /**
     * constructor for the maincontroller
     *
     * @throws Exception
     */
    public mainController() throws Exception {
        centralPoint = new CentralPoint();
        missionListObservable = FXCollections.observableArrayList(centralPoint.getAllMissions());
        teamListObservable = FXCollections.observableArrayList(centralPoint.getAllTeams());
        messages = FXCollections.observableArrayList(centralPoint.getLastMessages().split("///"));

        new CommunicationMediator(centralPoint);

        keepNotificationsUpToDate = new Timer();
    }

    /**
     * publish mission on the webpage
     */
    @FXML
    private void publishOnWebpage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Resources/publishOnWebScreen.fxml"));
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
            pubOnWebController MC = loader.getController();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * send message to database
     */
    public void sendMessage() {
        String message = tfMessage.getText();
        if (!message.equals("")) {
            centralPoint.insertMessageCentral(message);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tfMessage.setText("");
            }
        });
    }

    /**
     * creates a new view with all the mission details in it.
     *
     * @param selectedMission
     */
    private void createMissionView(Mission selectedMission) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Resources/missionscreen.fxml"));
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
            missionsdetailcontroller MC = loader.getController();
            MC.setMissionController(selectedMission, centralPoint);
            stage.setTitle("Missie : " + selectedMission.getName());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * shows all the staff that is currently in the field
     */
    public void ShowStaffInField() {

        ObservableList<Staff> staffOnLocation = centralPoint.getStaffOnLocation();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Resources/Task.fxml"));
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
            taskController taskControllers = loader.getController();
            taskControllers.setList(staffOnLocation);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Platform.runLater(() -> tvStaffOnLocation.setItems(staffOnLocation));
    }

    /**
     * initialize of the maincontroller
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllElements();
        lvMissions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mission>() {
            @Override
            public void changed(ObservableValue observable, Mission oldValue, Mission newValue) {
                createMissionView((Mission) lvMissions.getSelectionModel().getSelectedItem());
            }
        });

        lvNotifications.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    taInfo.setText(((Notification) lvNotifications.getSelectionModel().getSelectedItem()).getContent());
                } catch (NullPointerException exp) {

                }
            }
        });

        keepNotificationsUpToDate.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        notificationObservableList = FXCollections.observableList(centralPoint.getAllNotifications());
                        loadNotifications();
                        loadMessages();
                        loadMissions();
                    }
                }, 0, 1000
        );
    }

    /**
     * load missions on timer
     */
    private void loadMissions() {
        ObservableList<Mission> tempMission = FXCollections.observableArrayList(centralPoint.getAllMissions());
        if (!Arrays.equals(missionListObservable.toArray(), tempMission.toArray())) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    missionListObservable = tempMission;
                    lvMissions.setItems(missionListObservable);
                }
            });
        }
    }

    /**
     * loads messages
     */
    private void loadMessages() {
        String[] messagesArray = centralPoint.getLastMessages().split("///");
        ObservableList<String> messageList = FXCollections.observableArrayList(messagesArray);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (String message : messageList) {
                    if (!messages.contains(message)) {

                        messages.add(message);
                    }
                }
                lvMessage.setItems(messages);
            }
        });
    }

    /**
     * reload all elements used in the controller
     */
    private void loadAllElements() {
        loadTeams();
        loadMission();
        loadNotifications();
        lvMessage.setItems(messages);
    }

    /**
     * reload all the teams
     */
    private void loadTeams() {
        lvTeams.setItems(teamListObservable);
    }

    /**
     * reload all the missions
     */
    private void loadMission() {
        lvMissions.setItems(missionListObservable);
    }

    /**
     * reload all the notifications
     */
    private void loadNotifications() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lvNotifications.setItems(notificationObservableList);
            }
        });
    }

    /**
     * create a new view in which you can create a new mission
     */
    public void makeNewMission() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Resources/screenmission.fxml"));
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
            createMissionController createMissionController = loader.getController();
            createMissionController.setCentralPoint(centralPoint);
            stage.setTitle("nieuwe missie");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
