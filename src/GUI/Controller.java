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
    private ComboBox cbTeams= new ComboBox();

    private Stage stage;
    private HashSet<Team> teams;
    private Mission mission;
    private ObservableList<Team> teamObservableList;


    public Controller() {
        DBRead DBR = new DBRead();
        teams = new HashSet<>();
        mission = new Mission(0, "null", null);
        teamObservableList = FXCollections.observableArrayList(teams);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lvTeams.setItems(teamObservableList);
            }
        });
    }

    public void makeTeam() {
        Team toMakeTeam = new Team(tfTeamName.getText(), null);
        if (!teamObservableList.contains(toMakeTeam)) {
            teamObservableList.add(toMakeTeam);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lvTeams.setItems(teamObservableList);
                    cbTeams.setItems(teamObservableList);
                }
            });

        }
        saveToDatabase();
    }

    private void assignTeamToJob() {
        // Team toAssignTeam = (Team)cbTeams.getSelectionModel().getSelectedItem();
        // Job toAssignJob = lvJob.getSelectionModel().getSelectedItem();
        // toAssignJob.addTeamToJob(toAssignTeam);
    }

    private void saveToDatabase() {
    }

}
