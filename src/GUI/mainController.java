package GUI;


import CentralPoint.CentralPoint;
import CentralPoint.Mission;
import CentralPoint.Team;
import CentralPoint.Staff;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Qunfo on 29-3-2016.
 */
public class mainController implements Initializable {

    @FXML
    private ListView lvMissions;
    @FXML
    private ListView lvTeams;

    private CentralPoint centralPoint;
    private ObservableList<Mission> missionListObservable;
    private ObservableList<Team> teamListObservable;

    public mainController() {
        centralPoint = new CentralPoint();
        missionListObservable = FXCollections.observableArrayList(centralPoint.getAllMissions());
        teamListObservable = FXCollections.observableArrayList(centralPoint.getAllTeams());

    }

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

    public void ShowStaffInField(){

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllElements();
        lvMissions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mission>() {
            @Override
            public void changed(ObservableValue observable, Mission oldValue, Mission newValue) {
                createMissionView((Mission) lvMissions.getSelectionModel().getSelectedItem());
            }

        });

    }

    private void loadAllElements() {
        loadTeams();
        loadMission();
    }

    private void loadTeams() {
        lvTeams.setItems(teamListObservable);
    }

    private void loadMission() {
        lvMissions.setItems(missionListObservable);
    }

    public void makeNewMission() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Resources/screenmission.fxml"));
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
            createMissionController createMissionController = loader.getController();
            createMissionController.setCentralPoint(centralPoint);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}