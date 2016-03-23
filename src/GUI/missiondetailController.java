package GUI;

import CentralPoint.MissionMark;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class missiondetailController {

    // zorg ervoor dat er constant gekeken wordt of er iets nieuws in de database staat.
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

    private List<MissionMark> missionMarks;
    private ObservableList<MissionMark> obMissionMarks;
    private int idcounter;
    public missiondetailController()
    {
        this.missionMarks = new ArrayList<>();
        idcounter = 1;
    }

    @FXML
    private void newmission()
    {
        Date date = new Date();
        try
        {
            Double LocationX = Double.parseDouble(TFlocationXMission.getText());
            Double LocationY = Double.parseDouble(TFlocationYMission.getText());
            MissionMark missionMark = new MissionMark(idcounter,date, LocationX, LocationY);
            idcounter++;
            missionMarks.add(missionMark);
            Collections.reverse(missionMarks);
            obMissionMarks = FXCollections.observableArrayList(missionMarks);
            Collections.reverse(missionMarks);
            missionlist.setItems(obMissionMarks);
        }
        catch (Exception e)
        {
            System.out.println("put in numbers please");
        }
    }

    @FXML
    private void SearchMission()
    {
        List<MissionMark> searchlist = new ArrayList<>();
        String searcher = TFsearchMission.getText();
        for(MissionMark missionMark : missionMarks)
        {
            int missionID = missionMark.getMissionId();
           if(missionID == Integer.parseInt(searcher))
           {
               searchlist.add(missionMark);
           }
        }
        Collections.reverse(searchlist);
        obMissionMarks = FXCollections.observableArrayList(searchlist);
        missionlist.setItems(obMissionMarks);
    }

    @FXML
    private void selectmission()
    {
        MissionMark selectedMissionMark = null;
        for(MissionMark missionMark : missionMarks)
        {
            if (missionlist.getSelectionModel().getSelectedItem().equals(missionMark))
            {
                selectedMissionMark = missionMark;
            }
        }
        if(selectedMissionMark != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("missionscreen.fxml"));
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = loader.load();
                missionscontroller MC = loader.getController();
                MC.setMissionMark(selectedMissionMark);
                stage.setTitle("MissionMark: " + selectedMissionMark.getMissionId());
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

    public void changeMission(MissionMark missionMark)
    {
        for(MissionMark msn : missionMarks)
        {
            if(missionMark.getMissionId() == msn.getMissionId())
            {
                msn = missionMark;
            }
        }
    }
}
