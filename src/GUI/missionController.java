package GUI;

import CentralPoint.Mission;
import Database.DaoGeneric;
import Database.DaoManager;
import Database.DbTables;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class missionController {

    // zorg ervoor dat er constant gekeken wordt of er iets nieuws in de database staat.
    @FXML
    private TextField TFnameMission;
    @FXML
    private TextArea TAdescriptionMission;
    @FXML
    private TextField TFsearchMission;
    @FXML
    private Button BTNsearchMission;
    @FXML
    private ListView missionlist;
    @FXML
    private Button BTNreateMission;
    @FXML
    private TextField TFlocationXMission;
    @FXML
    private TextField TFlocationYMission;

    private List<Mission> missions;
    private ObservableList<Mission> obMission;
    private int idcounter;
    private Timer timer;

    public missionController()
    {
        this.missions = new ArrayList<>();
        idcounter = 1;
        startTimer();
    }

    @FXML
    private void newmission()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        try
        {

            Double LocationX = Double.parseDouble(TFlocationXMission.getText());
            Double LocationY = Double.parseDouble(TFlocationYMission.getText());
            Mission mission = new Mission(1, TFnameMission.getText(), TAdescriptionMission.getText(), date, null, null, LocationX, LocationY);
            DaoManager.INSTANCE.open();
            DaoGeneric<Mission> DBmissions = DaoManager.INSTANCE.getDao(DbTables.MISSIE);
            if(DBmissions.insert(mission))
            {
                System.out.println("success");
            }
            else
            {
                System.out.println("failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("put in numbers please");
        }
    }

    @FXML
    private void SearchMission()
    {
        List<Mission> searchlist = new ArrayList<>();
        for(Mission mission : obMission)
        {
           if(mission.getName().indexOf(TFsearchMission.getText()) != -1)
           {
               searchlist.add(mission);
           }
        }
        timer.cancel();
        ObservableList<Mission> tempMission = FXCollections.observableArrayList(searchlist);
        missionlist.setItems(tempMission);
    }

    @FXML
    private void selectmission()
    {
        Mission selectedMission = null;
        for(Mission mission : obMission)
        {
            if (missionlist.getSelectionModel().getSelectedItem().equals(mission))
            {
                selectedMission = mission;
            }
        }
        if(selectedMission != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Resources/missionscreen.fxml"));
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = loader.load();
                missionsdetailcontroller MC = loader.getController();
                MC.setMissionMark(selectedMission);
                stage.setTitle("Mission: " + selectedMission.getID());
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("select mission");
        }
    }

    public void changeMission(Mission mission)
    {
        for(Mission msn : missions)
        {
            if(mission.getID() == msn.getID())
            {
                msn = mission;
            }
        }
    }
    private void startTimer()
    {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                DaoManager.INSTANCE.open();
                DaoGeneric<Mission> DBmissions = DaoManager.INSTANCE.getDao(DbTables.MISSIE);
                obMission = DBmissions.getAllRecord();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        missionlist.setItems(obMission);
                    }
                });

                DaoManager.INSTANCE.close();
            }
        },0, 1000);

    }
}
