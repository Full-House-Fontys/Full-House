package GUI;

import CentralPoint.Mission;
import CentralPoint.MissionMark;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mark on 16-3-2016.
 */
public class missionsdetailcontroller {

    @FXML
    private TextField TFmissionId;
    @FXML
    private TextField TFname;
    @FXML
    private TextField TFdescription;
    @FXML
    private TextField TFstartTime;
    @FXML
    private TextField TFupdateTime;
    @FXML
    private TextField TFendTime;
    @FXML
    private TextField LocationX;
    @FXML
    private TextField LocationY;
    @FXML
    private ListView Listservices;
    @FXML
    private Button BTNsolve;
    private Mission mission;
    private missionController missionController;

    public void setMissionController(missionController missionController)
    {
        this.missionController = missionController;
    }
    public void setMissionMark(Mission mission)
    {
        this.mission = mission;
        setSettings();
    }

    private void setSettings()
    {
        TFmissionId.setText(String.valueOf(mission.getID()));
        TFstartTime.setText(String.valueOf(mission.getStartTime()));
        TFupdateTime.setText(String.valueOf(mission.getLastUpdate()));
        TFendTime.setText(String.valueOf(mission.getEndTime()));
        LocationX.setText(String.valueOf(mission.getLocationX()));
        LocationY.setText(String.valueOf(mission.getLocationY()));
    }

    @FXML
    private void Solved()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        mission.setEndTime(date);
        TFendTime.setText(String.valueOf(mission.getEndTime()));
    }
}
