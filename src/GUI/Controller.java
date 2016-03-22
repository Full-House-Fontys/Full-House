package GUI;

import CentralPoint.CentralPoint;
import CentralPoint.Mission;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import CentralPoint.Team;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Controller {
    @FXML
    private TextField tbNameTask;
    @FXML
    private TextField tbPlaceTask;
    @FXML
    private DatePicker dtTimeTask;
    @FXML
    private TextArea taAnnouncements;
    @FXML
    private Button btnSendTasks;
    @FXML
    private ListView lvTasks;
    @FXML
    private TextField tfTeamName;
    @FXML
    private ListView lvTeams;
    @FXML
    private ListView lvStaffOnLocation;
    @FXML
    private ComboBox cbTeams= new ComboBox();

    private CentralPoint centralPoint;

    private Stage stage;
    private HashSet<Team> teams;
    private ArrayList<String> staffOnLocation;
    private Mission mission;
    private ObservableList<Team> teamObservableList;
    private ObservableList<String> staffObservableList;


    public Controller() {
        //DBRead DBR = new DBRead();
        //teams = new HashSet<>();
        //mission = new Mission(0, "null", null);
        //teamObservableList = FXCollections.observableArrayList(teams);
        centralPoint = new CentralPoint();
        staffOnLocation = centralPoint.staffOnLocation();
        staffObservableList = FXCollections.observableArrayList(staffOnLocation);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //lvTeams.setItems(teamObservableList);
                lvStaffOnLocation.setItems(staffObservableList);
            }
        });
    }

    public void makeTeam() {
        ArrayList<String> teamnames = new ArrayList<>();
        teamnames.add("Lind");
        centralPoint.setStaffOnLocation(teamnames);
        staffOnLocation = centralPoint.staffOnLocation();
        staffObservableList = FXCollections.observableArrayList(staffOnLocation);

        //Team toMakeTeam = new Team(tfTeamName.getText(), null);
        //if (!teamObservableList.contains(toMakeTeam)) {
            //teamObservableList.add(toMakeTeam);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lvStaffOnLocation.setItems(staffObservableList);
                    //lvTeams.setItems(teamObservableList);
                    //cbTeams.setItems(teamObservableList);
                }
            });

        //}
        //saveToDatabase();

    }

    private void assignTeamToJob() {
        // Team toAssignTeam = (Team)cbTeams.getSelectionModel().getSelectedItem();
        // Job toAssignJob = lvJob.getSelectionModel().getSelectedItem();
        // toAssignJob.addTeamToJob(toAssignTeam);
    }

    private void saveToDatabase() {
    }

}
