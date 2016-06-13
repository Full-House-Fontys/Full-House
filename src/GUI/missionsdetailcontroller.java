package GUI;

import CentralPoint.CentralPoint;
import CentralPoint.Material;
import CentralPoint.Mission;
import CentralPoint.Team;
import HulpDienst.ITeamRequest;
import HulpDienst.TeamRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.geom.Point2D;
import java.io.*;
import java.net.Socket;
import java.net.URL;
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
    private TextArea taWeatherInfo;

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

    @FXML
    private TextField TFrequestName;
    @FXML
    private TextField TFrequestDescription;
    @FXML
    private TextField TFrequestPolice;
    @FXML
    private TextField TFrequestMedic;
    @FXML
    private TextField TFrequestFireman;
    @FXML
    private TextField TFrequestMilitaryPolice;
    @FXML
    private Button BTrequest;

    private Mission mission;
    private CentralPoint centralPoint;
    private ObservableList<Team> teamAvailable;
    private ObservableList<Team> teamToAdd;
    private ObservableList<Team> teamsInTheMission;
    private ObservableList<Material> materialAvailable;
    private ObservableList<Material> materialInMission;
    private Alert alert;

    //TODO JAVADOC
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
        receiveAndShowWeatherInfo();
    }

    //TODO JAVADOC
    private void setSettings() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Beschrijving : " + mission.getDescription() + "\n");
        stringBuilder.append("Start tijd : " + mission.getStartTime() + "\n");
        stringBuilder.append("Laatst geupdate : " + mission.getLastUpdate() + "\n");
        stringBuilder.append("Eind tijd : " + mission.getEndTime() + "\n");
        stringBuilder.append("Locatie : " + mission.getLocationX() + ";" + mission.getLocationY() + "\n");
        stringBuilder.append("verwachte duur ongeval" + mission.getEstimatedTime() + "minuten");
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

    /**
     * Receives the weather information and shows it on the textarea taWeatherInfo
     */
    private void receiveAndShowWeatherInfo(){
        try {
            InputStream inputStream = new URL("http://api.openweathermap.org/data/2.5/forecast/weather?lat=" +
                    mission.getLocationX() +
                    "&lon=" +
                    mission.getLocationY() +
                    "&APPID=cbabe683e2349af1ea6e571bba89251f&units=metric&lang=nl").openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String jsonText = readAll(bufferedReader);
            JSONObject jsonObject = new JSONObject(jsonText);
            taWeatherInfo.setText("Het weer in " + jsonObject.getJSONObject("city").getString("name") + ":\n\n");
            taWeatherInfo.appendText("Huidig weer: \n" + getReadableWeather(jsonObject.getJSONArray("list").getJSONObject(0)) + "\n\n");
            taWeatherInfo.appendText("Weer over 3 uur: \n" + getReadableWeather(jsonObject.getJSONArray("list").getJSONObject(1)) + "\n\n");
            taWeatherInfo.appendText("Weer morgen: \n" + getReadableWeather(jsonObject.getJSONArray("list").getJSONObject(8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads out the json of a reader
     * @param rd the reader which contains the json
     * @return the json as a String
     * @throws IOException
     */
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * Generates the weather in readable text from JSON
     * @param jsonObject the JSON with the weather info
     * @return the weather info as readable String
     */
    private String getReadableWeather(JSONObject jsonObject){
        StringBuilder readableWeatherInfo = new StringBuilder();
        JSONObject mainJson = jsonObject.getJSONObject("main");
        readableWeatherInfo.append(mainJson.getDouble("temp") +
                "\u00b0C met " +
                jsonObject.getJSONArray("weather").getJSONObject(0).getString("description") + "\n");

        readableWeatherInfo.append("Windsnelheid: " +
                jsonObject.getJSONObject("wind").getDouble("speed") +
                "m/s; Richting: " +
                jsonObject.getJSONObject("wind").getDouble("deg") + "\u00b0\n");

        readableWeatherInfo.append("Bewolking en regen: " +
                jsonObject.getJSONObject("clouds").getInt("all") + " % bewolkt");

        try {
            readableWeatherInfo.append(" Regen: " + jsonObject.getJSONObject("rain").getDouble("3h") + "mm");
        }
        catch (JSONException exp) {
            readableWeatherInfo.append(" en geen regen.");
        }

        try {
            readableWeatherInfo.append(" Sneeuw: " + jsonObject.getJSONObject("snow").getDouble("3h") + "mm");
        }
        catch (JSONException exp) {
            exp.printStackTrace();
        }

        return readableWeatherInfo.toString();
    }

    //TODO JAVADOC
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

    //TODO JAVADOC
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

    //TODO JAVADOC
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

    //TODO JAVADOC
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

    //TODO JAVADOC
    //TODO DUPLICATE CODE
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

    //TODO JAVADOC
    @FXML
    public void createRequest() {
        ITeamRequest TR = null;
        TR = new TeamRequest(TFrequestName.getText(), TFrequestDescription.getText(), Integer.parseInt(TFrequestPolice.getText()), Integer.parseInt(TFrequestMedic.getText()), Integer.parseInt(TFrequestFireman.getText()), Integer.parseInt(TFrequestMilitaryPolice.getText()), mission);
        try {
            centralPoint.getOutput().writeObject(TR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO JAVADOC
    @FXML
    public void createRapport() throws IOException {
        centralPoint.createRapport(mission);
    }
}
