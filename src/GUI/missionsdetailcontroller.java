package GUI;

import CentralPoint.*;
import HulpDienst.ITeamRequest;
import HulpDienst.TeamRequest;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.geom.Point2D;
import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    private WebView wvGoogleMaps;
    @FXML
    private WebView wvAirQuality;

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
    @FXML
    private ScrollPane tabSteps;
    @FXML
    private TextField tfAmountOfSteps;

    private Mission mission;
    private CentralPoint centralPoint;
    private ObservableList<Team> teamAvailable;
    private ObservableList<Team> teamToAdd;
    private ObservableList<Team> teamsInTheMission;
    private ObservableList<Material> materialAvailable;
    private ObservableList<Material> materialInMission;
    private Alert alert;
    private ArrayList<TextField> steps;
    private int ID = 0;
    private Pane panel;
    private Timer teamTimer;
    private TimerTask timerTask;

    /**
     * set the mission controller in the detail controller
     *
     * @param mission
     * @param centralPoint
     */
    public void setMissionController(Mission mission, CentralPoint centralPoint) {
        steps = new ArrayList<>();
        panel = new Pane();
        this.mission = mission;
        this.centralPoint = centralPoint;
        if (mission != null) {
            Mission tempMission = centralPoint.addMaterialsToMission(mission.getID());
            if (tempMission != null) {
                mission = tempMission;
            }
            this.teamAvailable = FXCollections.observableArrayList(centralPoint.getSpecificTeam());
            this.teamToAdd = FXCollections.observableArrayList();
            teamTimer = new java.util.Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    refreshView();
                }
            };

            if (mission.getTeamsAssigned() != null) {
                this.teamsInTheMission = FXCollections.observableArrayList(mission.getTeamsAssigned());
            }
            this.materialAvailable = FXCollections.observableArrayList(centralPoint.getAvailableMaterials());
            if (mission.getMaterialsAssigned() != null) {
                this.materialInMission = FXCollections.observableArrayList(mission.getMaterialsAssigned());
            }
            teamTimer.schedule(timerTask, 0, 1000);
            setSettings();
            receiveAndShowWeatherInfo();
            try {
                editHtml();
                initEngine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * update methode, updates list every second
     */
    private void refreshView() {
        ObservableList<Team> obTeam = centralPoint.getAllTeams();
        ArrayList<Team> teamList = new ArrayList<>();
        for (Team team : obTeam) {
            if (team.getMissionID().get(0) == mission.getID()) {
                teamList.add(team);
            }
        }

        mission.setTeamsAssigned(teamList);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ObservableList<Team> teamsUpdate = FXCollections.observableArrayList(mission.getTeamsAssigned());
                if (teamsUpdate != null) {
                    LvTeams.setItems(teamsUpdate);
                    lvTeamsInMission.setItems(teamsUpdate);
                }
            }
        });
    }

    /**
     * set all details of the mission in the text area
     */
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
     * @throws IOException when file could not be read
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
            readableWeatherInfo.append("");
        }

        return readableWeatherInfo.toString();
    }

    /**
     * Edits the html, so that there are better location marks
     * @throws IOException if html can't be read
     */
    private void editHtml() throws IOException {
        File input = null;
        try {
            input = new File(missionsdetailcontroller.class.getClassLoader().getResource("Resources/googlemaps.html").toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(input, "UTF-8");
        String javaScript = doc.getElementsByTag("Script").first().toString();
        String lat = javaScript.substring(javaScript.indexOf("lat: ") + 5, javaScript.indexOf(","));
        String lon = javaScript.substring(javaScript.indexOf("lng: ") + 5, javaScript.indexOf("}"));

        lat = String.valueOf(mission.getLocationX());
        lon = String.valueOf(mission.getLocationY());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(javaScript.substring(javaScript.indexOf(">") + 1, javaScript.indexOf("lat: ") + 5));
        stringBuilder.append(lat);
        stringBuilder.append(", lng: ");
        stringBuilder.append(lon);
        stringBuilder.append(javaScript.substring(javaScript.indexOf("}"), javaScript.lastIndexOf("<")));
        doc.getElementsByTag("Script").first().text(stringBuilder.toString());

        FileUtils.writeStringToFile(input, doc.outerHtml(), "UTF-8");
    }

    /**
     * Sets up the engine, so that the webpage is visible.
     */
    private void initEngine(){
        WebEngine googleWebEngine = wvGoogleMaps.getEngine();
        final URL urlGoogleMaps = getClass().getClassLoader().getResource("Resources/googlemaps.html");
        googleWebEngine.load(urlGoogleMaps.toExternalForm());

        WebEngine airWebEngine = wvAirQuality.getEngine();
        airWebEngine.load("https://www.luchtmeetnet.nl/");
    }

    /**
     * add material to a mission.
     */
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

    /**
     * add an available team to a current team
     */
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

    /**
     * remove a team from current teams
     */
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

    /**
     * send a mission to the team
     *
     * @throws IOException
     */
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

    /**
     * shows a pop up with the message
     *
     * @param bericht
     */
    //TODO niet gebruikt
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

    /**
     * creates a new teamrequest which will be send to the helpservice.
     */
    @FXML
    public void createRequest() {
        ITeamRequest TR = null;
        TR = new TeamRequest(TFrequestName.getText(), TFrequestDescription.getText(), Integer.parseInt(TFrequestPolice.getText()), Integer.parseInt(TFrequestMedic.getText()), Integer.parseInt(TFrequestFireman.getText()), Integer.parseInt(TFrequestMilitaryPolice.getText()), mission);
        try {
            clearText();
            centralPoint.getOutput().writeObject(TR);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team verzoek");
            alert.setHeaderText("Het voor verzoek voor team : " + TFrequestName.getText() + " is succesvol ingediend");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearText() {
        TFrequestName.clear();
        TFrequestDescription.clear();
        TFrequestPolice.clear();
        TFrequestMedic.clear();
        TFrequestFireman.clear();
        TFrequestMilitaryPolice.clear();
    }

    /**
     * creates the rapport of the mission
     * @throws IOException
     */
    @FXML
    public void createRapport() throws IOException {
        centralPoint.createRapport(mission);
    }

    @FXML
    public void createSteps() {
        panel.getChildren().clear();
        panel.setLayoutX(100);
        panel.setLayoutY(200);
        int amountOfSteps = Integer.parseInt(tfAmountOfSteps.getText());
        for (int i = 1; i < amountOfSteps + 1; i++) {
            Label label = new Label();
            label.setText(Integer.toString(i));
            TextField textField = new TextField();
            textField.setLayoutY(i * 50);
            textField.setLayoutX(20);
            label.setLayoutY(i * 50);
            label.setLayoutX(10);
            ID++;
            steps.add(textField);
            panel.getChildren().add(label);
            panel.getChildren().add(textField);
        }
        tabSteps.setContent(panel);
        // tabSteps.getChildren().add(panel);
    }

    @FXML
    public void saveStepsToDatabase() {
        ArrayList<TextField> allSteps = new ArrayList<>();
        String steps = "";
        Boolean saved = false;
        for (Object step : panel.getChildren()) {
            if (step instanceof TextField && !((TextField) step).getText().isEmpty()) {
                allSteps.add((TextField) step);
            }
        }
        for (int i = 1; i <= allSteps.size(); i++) {
            steps += i + "." + allSteps.get(i - 1).getText() + "//";
        }
        saved = centralPoint.insertMissionPlan(new MissionPlan(mission.getID(), steps));
        if (!saved) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Opslaan van missieplan");
            alert.setHeaderText("Niet gelukt om op te slaan");
            alert.setContentText("Probeer opnieuw");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Opslaan stappenplan");
            alert.setHeaderText("Stappenplan is opgeslagen en verstuurd");
            alert.showAndWait();
        }
    }
}
