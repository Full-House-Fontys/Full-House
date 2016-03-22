package GUI;

import CentralPoint.MissionMark;
import javafx.collections.FXCollections;
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
public class missionscontroller {

    @FXML
    private TextField TFmissionId;
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
    private MissionMark missionMark;
    private ControllerMark controllerMark;

    public void setControllerMark(ControllerMark controllerMark)
    {
        this.controllerMark = controllerMark;
    }
    public void setMissionMark(MissionMark missionMark)
    {
        this.missionMark = missionMark;
        setSettings();
    }

    private void setSettings()
    {
        TFmissionId.setText(String.valueOf(missionMark.getMissionId()));
        TFstartTime.setText(String.valueOf(missionMark.getStartTime()));
        TFupdateTime.setText(String.valueOf(missionMark.getLastUpdate()));
        TFendTime.setText(String.valueOf(missionMark.getEndTime()));
        LocationX.setText(String.valueOf(missionMark.getLocationX()));
        LocationY.setText(String.valueOf(missionMark.getLocationY()));
        Listservices.setItems(FXCollections.observableArrayList(missionMark.getServices()));
    }

    @FXML
    private void Solved()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        missionMark.setEndTime(date);
        TFendTime.setText(String.valueOf(missionMark.getEndTime()));
    }
}
