package GUI;

import CentralPoint.CentralPoint;
import CentralPoint.Mission;
import CentralPoint.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Mark on 16-3-2016.
 */
public class missionsdetailcontroller {


    @FXML
    private TextArea TaBeschrijving;
    @FXML
    private ListView LvTeams;
    @FXML
    private ListView lvTeamsInMission;
    @FXML
    private ListView lvTeamsAvailable;
    @FXML
    private ListView lvToAddTeams;

    private Mission mission;
    private CentralPoint centralPoint;
    private ObservableList<Team> teamAvailable;
    private ObservableList<Team> teamToAdd;
    private ObservableList<Team> teamsInTheMission;
    private Alert alert;

    public void setMissionController(Mission mission, CentralPoint centralPoint)
    {
        this.mission = mission;
        this.centralPoint = centralPoint;
        this.teamAvailable = FXCollections.observableArrayList(centralPoint.getSpecificTeam());
        this.teamToAdd = FXCollections.observableArrayList();
        this.teamsInTheMission = FXCollections.observableArrayList(mission.getTeamsAssigned());
        setSettings();
    }

    private void setSettings()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Beschrijving : " + mission.getDescription() + "\n");
        stringBuilder.append("Start tijd : " + mission.getStartTime() + "\n");
        stringBuilder.append("Laatst geupdate : " + mission.getLastUpdate() + "\n");
        stringBuilder.append("Eind tijd : " + mission.getEndTime() + "\n");
        stringBuilder.append("Locatie : " + mission.getLocationX() + ";" + mission.getLocationY() + "\n");
        TaBeschrijving.insertText(0, stringBuilder.toString());
        LvTeams.setItems(FXCollections.observableArrayList(mission.getTeamsAssigned()));
        lvTeamsInMission.setItems(teamsInTheMission);
        lvTeamsAvailable.setItems(teamAvailable);
    }

    public void addAvailableTeamToCurrentTeams() {
        if (lvTeamsAvailable.getSelectionModel().getSelectedItem() != null) {
            teamToAdd.add((Team) lvTeamsAvailable.getSelectionModel().getSelectedItem());
            lvToAddTeams.setItems(teamToAdd);
            Team toRemove = (Team) lvTeamsAvailable.getSelectionModel().getSelectedItem();
            lvTeamsAvailable.getItems().remove(toRemove);
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Team toevoegen");
            alert.setHeaderText("Toevoegen");
            alert.setContentText("Selecteer een team om toe te voegen");

            alert.showAndWait();
        }
    }

    public void removeFromCurrentTeam() {
        if (lvToAddTeams.getSelectionModel().getSelectedItem() != null) {
            teamAvailable.add((Team) lvToAddTeams.getSelectionModel().getSelectedItem());
            lvTeamsAvailable.setItems(teamAvailable);
            Team toRemove = (Team) lvToAddTeams.getSelectionModel().getSelectedItem();
            lvToAddTeams.getItems().remove(toRemove);
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Team verwijderen");
            alert.setHeaderText("Verwijderen");
            alert.setContentText("Selecteer een team om te verwijderen");
            alert.showAndWait();
        }
    }

    public void sendMissionToTeam() throws IOException {
        String hostName = "";
        int portNumber = 0;
        Socket sendSocket = new Socket("localhost", 2002);
        try (OutputStream os = sendSocket.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(mission);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
