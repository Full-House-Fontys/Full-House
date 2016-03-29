package GUI;

import CentralPoint.CentralPoint;
import CentralPoint.Mission;
import CentralPoint.Team;
import Database.DaoGeneric;
import Database.DaoManager;
import Database.DaoStaff;
import Database.DbTables;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import CentralPoint.Team;
import CentralPoint.Staff;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;

public class taskController implements Initializable {
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
    private ListView lvAssignedTeams;
    @FXML
    private ListView lvTasks;
    @FXML
    private TextField tfTeamName;
    @FXML
    private ListView lvTeams;
    @FXML
    private ComboBox cbTeams;
    @FXML
    private Label lbDescription;
    @FXML
    private TextField tfFakeMission;
    @FXML
    private TextArea taDescription;
    @FXML
    private TableView<Staff> tvStaffOnLocation;
    @FXML
    private TableColumn<Staff, String> tcName;
    @FXML
    private TableColumn<Staff, Integer> tcTeamID;
    @FXML
    private TableColumn<Staff, Point2D> tcLocation;

    private CentralPoint centralPoint;

    private HashSet<Team> teams;
    private ObservableList<Team> teamObservableList;
    private ObservableList<Mission> missionListOberservable;
    private ArrayList<Mission> missionList;
    private ObservableList<Staff> assignedTeamsObservable;
    private HashMap<Mission,String> sendableMissions;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPersonel();
        loadMission();
        lvTasks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mission>() {
            @Override
            public void changed(ObservableValue<? extends Mission> observable, Mission oldValue, Mission newValue) {
                loadTeamsAssignedToMission(newValue);
            }
        });
    }

    private void loadTeamsAssignedToMission(Mission newValue) {
        assignedTeamsObservable = newValue.getTeamsAssignedToMission(newValue.getID());
        lvAssignedTeams.setItems(assignedTeamsObservable);
    }

    public taskController() {
        //DBRead DBR = new DBRead();
        teams = new HashSet<>();
        teamObservableList = FXCollections.observableArrayList(teams);
        centralPoint = new CentralPoint();
        // ObservableList<Staff> staffOnLocation = centralPoint.getStaffOnLocation();
        // Platform.runLater(() -> tvStaffOnLocation.setItems(staffOnLocation));
        sendableMissions = new HashMap<>();
        missionList = new ArrayList<>();
        missionListOberservable = FXCollections.observableArrayList(missionList);
    }
    /**
     * Loads all the missions from the database
     */
    private void loadMission() {
        try {
            DaoManager.INSTANCE.open();
            DaoGeneric allMission = DaoManager.INSTANCE.getDao(DbTables.MISSIE);
            ObservableList inputMission = allMission.getAllRecord();
            lvTasks.setItems(inputMission);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoManager.INSTANCE.close();
        }
    }

    /**
     * Loads all possible rescueservices for a mission
     */
    private void loadPersonel() {
        try {
            DaoManager.INSTANCE.open();
            DaoGeneric allStaff = DaoManager.INSTANCE.getDao(DbTables.PERSONEEL);
            ObservableList<Staff> inputStaff = allStaff.getAllRecord();
            ArrayList<String> rescueTypes = new ArrayList<>();
            for (Staff staff : inputStaff) {
                if (!rescueTypes.contains(staff.getSort())) {
                    rescueTypes.add(staff.getSort());
                }
            }
            ObservableList<String> rescueTypesOberservable = FXCollections.observableArrayList(rescueTypes);
            lvTeams.setItems(rescueTypesOberservable);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoManager.INSTANCE.close();
        }
    }

    /**
     * This is to test making a team
     */
    public void makeTeam() {
        Team toMakeTeam = new Team(tfTeamName.getText(), null);
        if (!teamObservableList.contains(toMakeTeam)) {
            teamObservableList.add(toMakeTeam);
            Platform.runLater(() -> {
                //lvStaffOnLocation.setItems(staffObservableList);
                lvTeams.setItems(teamObservableList);
                cbTeams.setItems(teamObservableList);
            });

        }
        //saveToDatabase();

    }

    /**
     * Tries to assign a new rescueservice to a mission
     */
    public void assignTeamToMission() {
        Team toAssignTeam = new Team((String)lvTeams.getSelectionModel().getSelectedItem(), null);
        Mission toAssignJob = (Mission) lvTasks.getSelectionModel().getSelectedItem();

        ObservableList<Staff> staffAssigned = lvAssignedTeams.getItems();
        ArrayList<Team> assignedTeams = new ArrayList<>();
        for(Staff staff : staffAssigned){
            assignedTeams.add(new Team(staff.getSort(),null));
        }
        toAssignJob.setTeamsAssigned(assignedTeams);
        boolean succesfulAdd = toAssignJob.addTeamToJob(toAssignTeam);
        if (succesfulAdd) {
            assignedTeamsObservable.add(new Staff(null,null,null,null,null,null,toAssignTeam.getName(),0,false));
            sendableMissions.put(toAssignJob,toAssignTeam.getName());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("This team zit al in de missie");
            alert.setHeaderText("Mission toevoegen");
            alert.setContentText("Dit team participeert al in de missie");

            alert.showAndWait();

        }
    }

    /**
     * This shows the description of the selected mission
     */
    public void showDescriptionOfSelectedMission() {
        Mission mission = (Mission) lvTasks.getSelectionModel().getSelectedItem();
        lbDescription.setText(mission.getDescription());
    }

    /**
     * This is so you can replicate creating missions
     */
    public void createFakeMission() {
        Mission fakeMission = new Mission(0,tfFakeMission.getText(), taDescription.getText(), null, null,null,0,0);
        missionListOberservable.add(fakeMission);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lvTasks.setItems(missionListOberservable);
            }
        });
    }

    /**
     * Creates connection with rescueservice and sends the mission
     * @throws IOException
     */
    public void sendMissionToTeam() throws IOException {
        
        String hostName = "";
        int portNumber = 0;
        Socket sendSocket = new Socket("localhost",2002);
        try (OutputStream os = sendSocket.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(missionList);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
