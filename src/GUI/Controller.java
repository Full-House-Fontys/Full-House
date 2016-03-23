package GUI;

import CentralPoint.Mission;
import CentralPoint.Team;
import DA.DBRead;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Controller {

    @FXML
    private TextField tfTeamName;
    @FXML
    private ListView lvTeams;
    @FXML
    private ComboBox cbTeams = new ComboBox();
    @FXML
    private ListView lvAssignedTeams;
    @FXML
    private TextField tfFakeMission;
    @FXML
    private ListView lvTasks;
    @FXML
    private Label lbDescription;
    @FXML
    private TextArea taDescription;

    private Stage stage;
    private HashSet<Team> teams;
    private ArrayList<Mission> missionList;
    private ObservableList<Mission> missionListOberservable;
    private ObservableList<Team> teamObservableList;
    private HashSet<Team> teamsAssigned;
    private ObservableList<Team> teamsAssignedObservableList;
    private int fakeMissionCounter =0;


    public Controller() {
        DBRead DBR = new DBRead();
        teams = new HashSet<>();
        teamsAssigned = new HashSet<>();
        missionList = new ArrayList<>();

        teamObservableList = FXCollections.observableArrayList(teams);
        teamsAssignedObservableList = FXCollections.observableArrayList(teamsAssigned);
        missionListOberservable = FXCollections.observableArrayList(missionList);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lvTeams.setItems(teamObservableList);
            }
        });
    }

    public void showDescriptionOfSelectedMission(){
        Mission mission = (Mission)lvTasks.getSelectionModel().getSelectedItem();
        lbDescription.setText(mission.getDescription());
    }

    public void makeTeam() {
        Team toMakeTeam = new Team(tfTeamName.getText(), null);
        if (!teamObservableList.contains(toMakeTeam)) {
            teamObservableList.add(toMakeTeam);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lvTeams.setItems(teamObservableList);
                }
            });

        }
        saveToDatabase();
    }

    public void assignTeamToMission() {
        teamsAssignedObservableList.add((Team) lvTeams.getSelectionModel().getSelectedItem());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lvAssignedTeams.setItems(teamsAssignedObservableList);
            }
        });

    }

    public void createFakeMission(){
        Mission fakeMission = new Mission(tfFakeMission.getText(),taDescription.getText(),null);
        missionListOberservable.add(fakeMission);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lvTasks.setItems(missionListOberservable);
            }
        });
    }
    private void saveToDatabase() {

    }

}
