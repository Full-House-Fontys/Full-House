package GUI;

import CentralPoint.Staff;
import CentralPoint.Team;
import HulpDienst.HelpService;
import HulpDienst.TeamRequest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Mark on 6-4-2016.
 */
public class HulpdienstController implements Initializable {

    @FXML
    private ComboBox CBfilterpersonen;
    @FXML
    private ListView LVpersoneel;
    @FXML
    private ListView LVmissies;
    @FXML
    private TextArea TAmissiedetail;
    @FXML
    private TextArea TAmissiedetail2;
    @FXML
    private ListView LVaddedStaff;
    @FXML
    private ListView LVTeams;
    @FXML
    private Button ButtonBTNaddStaff;
    @FXML
    private Button ButtonBTNremoveStaff;
    @FXML
    private Button BTNcreateTeam;
    @FXML
    private TextField TFteamNaam;
    @FXML
    private TabPane TPhupldienst;
    @FXML
    private Button BTNassignTeam;
    private HelpService hulpdienst;
    private ObservableList<Staff> OBStaff;
    private int teamnummer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hulpdienst = new HelpService();
        OBStaff = FXCollections.observableArrayList();
        LVaddedStaff.setItems(OBStaff);
        LVpersoneel.setItems(hulpdienst.renewStaffList());
        LVTeams.setItems(hulpdienst.renewteams());
        teamnummer = hulpdienst.getAllTeams().size() + 1;
        LVmissies.setItems(hulpdienst.getTeamRequests());
        CBfilterpersonen.getItems().clear();
        CBfilterpersonen.getItems().addAll("Alle", "Politie", "EHBO", "Brandweer");
        CBfilterpersonen.getSelectionModel().select(0);
        CBfilterpersonen.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                LVpersoneel.setItems(hulpdienst.filterStaffList(t1));

            }
        });


    }

    @FXML
    public void getdetails() {
        TeamRequest request = (TeamRequest) LVmissies.getSelectionModel().getSelectedItem();
        String tekst = request.toString() + "\n" + "brandweer: " + request.getFireman() + "\n" + "EHBO: " + request.getMedic() + "\n" + "Politie: " + request.getPolice() + "\n" + "Marachause: " + request.getMilitarypolice() + "\n";
        TAmissiedetail.setText(tekst);
        TAmissiedetail2.setText(tekst);
    }

    public void addStaff() {
        if (LVpersoneel.getSelectionModel().getSelectedItem() != null) {
            Staff staff = (Staff) LVpersoneel.getSelectionModel().getSelectedItem();
            LVpersoneel.getItems().remove(staff);
            OBStaff.add(staff);
        }
    }

    public void removeStaff() {
        if (LVaddedStaff.getSelectionModel().getSelectedItem() != null) {
            Staff staff = (Staff) LVaddedStaff.getSelectionModel().getSelectedItem();
            LVpersoneel.getItems().add(staff);
            OBStaff.remove(staff);
        }
    }

    public void maakTeam() {
        if (!TFteamNaam.getText().isEmpty()) {
            ArrayList<Staff> personeel = new ArrayList<>();
            personeel.addAll(OBStaff);
            hulpdienst.createTeam(new Team(teamnummer, TFteamNaam.getText(), personeel, null));
            OBStaff.clear();
            TPhupldienst.getSelectionModel().select(0);
            TFteamNaam.clear();
            renew();
        }
    }

    private void renew() {
        LVpersoneel.setItems(hulpdienst.renewStaffList());
        LVTeams.setItems(hulpdienst.renewteams());
        teamnummer = hulpdienst.getAllTeams().size() + 1;
        LVmissies.setItems(hulpdienst.getTeamRequests());
    }

    public void assignteam() {
        Team team = (Team) LVTeams.getSelectionModel().getSelectedItem();
        TeamRequest request = (TeamRequest) LVmissies.getSelectionModel().getSelectedItem();
        int missionid = request.getMission().getID();
        hulpdienst.addMissionToTeam(team, missionid);
        renew();
        LVmissies.getItems().remove(request);
        if (LVmissies.getSelectionModel().getSelectedItem() == null) {
            TAmissiedetail.clear();
        } else {
            getdetails();
        }

    }
}
