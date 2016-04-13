package GUI;

import CentralPoint.CentralPoint;
import CentralPoint.Material;
import CentralPoint.Mission;
import CentralPoint.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.geom.Point2D;
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
    @FXML
    private ListView lvActieveMissies;

    @FXML
    private TableView<Material> tvAvailableMat;
    @FXML
    private TableColumn<Material, String> tcAvaiName;
    @FXML
    private TableColumn<Material, String> tcAvaiSort;
    @FXML
    private TableColumn<Material, String> tcAvaiLocation;
    @FXML
    private TableColumn<Material, String> tcAvaiDistance;

    @FXML
    private TableView<Material> tvNotAvailableMat;
    @FXML
    private TableColumn<Material, String> tcNotAvaiName;
    @FXML
    private TableColumn<Material, String> tcNotAvaiSort;
    @FXML
    private TableColumn<Material, String> tcNotAvaiLocation;
    @FXML
    private TableColumn<Material, String> tcNotAvaiDistance;

    private Mission mission;
    private CentralPoint centralPoint;
    private ObservableList<Team> teamAvailable;
    private ObservableList<Team> teamToAdd;
    private ObservableList<Team> teamsInTheMission;
    private ObservableList<Material> materialAvailable;
    private ObservableList<Material> materialInMission;
    private Alert alert;

    public void setMissionController(Mission mission, CentralPoint centralPoint) {
        this.mission = mission;
        this.centralPoint = centralPoint;
        mission = centralPoint.addMaterialsToMission(mission.getID());
        this.teamAvailable = FXCollections.observableArrayList(centralPoint.getSpecificTeam());
        this.teamToAdd = FXCollections.observableArrayList();
        if(mission.getTeamsAssigned() != null) {
            this.teamsInTheMission = FXCollections.observableArrayList(mission.getTeamsAssigned());
        }
        this.materialAvailable = FXCollections.observableArrayList(centralPoint.getAvailableMaterials());
        if(mission.getMaterialsAssigned() != null) {
            this.materialInMission = FXCollections.observableArrayList(mission.getMaterialsAssigned());
        }
        setSettings();
    }

    private void setSettings() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Beschrijving : " + mission.getDescription() + "\n");
        stringBuilder.append("Start tijd : " + mission.getStartTime() + "\n");
        stringBuilder.append("Laatst geupdate : " + mission.getLastUpdate() + "\n");
        stringBuilder.append("Eind tijd : " + mission.getEndTime() + "\n");
        stringBuilder.append("Locatie : " + mission.getLocationX() + ";" + mission.getLocationY() + "\n");
        for (Material m : materialAvailable) {
            m.setDistance(new Point2D.Double(mission.getLocationX(), mission.getLocationY()));
        }
        TaBeschrijving.insertText(0, stringBuilder.toString());
        if(mission.getTeamsAssigned() != null) {
            LvTeams.setItems(FXCollections.observableArrayList(mission.getTeamsAssigned()));
        }
        lvTeamsInMission.setItems(teamsInTheMission);
        lvTeamsAvailable.setItems(teamAvailable);
        tvAvailableMat.setItems(materialAvailable);
        tvNotAvailableMat.setItems(materialInMission);
    }

    @FXML
    private void addMaterialToMission() {
        if (tvAvailableMat.getSelectionModel().getSelectedItem() != null) {
            Mission mis = centralPoint.addMaterialToMission(tvAvailableMat.getSelectionModel().getSelectedItem(), mission);
            setMissionController(mis, centralPoint);
            setSettings();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Materiaal koppelen aan missie");
            alert.setHeaderText("Koppelen");
            alert.setContentText("Selecteer een materiaal om te koppelen");
            alert.showAndWait();
        }
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

    private void showPopup(String bericht) {
        Stage primaryStage = new Stage();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(bericht));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
