package GUI;

import CentralPoint.CentralPoint;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Date;

/**
 * Created by Qunfo on 29-3-2016.
 */
public class createMissionController {

    @FXML
    private TextField TFnameMission;
    @FXML
    private TextArea TAdescriptionMission;
    @FXML
    private TextField TFlocationXMission;
    @FXML
    private TextField TFlocationYMission;

    private CentralPoint centralPoint;

    //TODO JAVADOC
    public void createMission() {
        try {
            Date date = new Date();
            Double LocationX = Double.parseDouble(TFlocationXMission.getText());
            Double LocationY = Double.parseDouble(TFlocationYMission.getText());
            String NameMission = TFnameMission.getText();
            String Description = TAdescriptionMission.getText();
            if (!NameMission.isEmpty() && !Description.isEmpty()) {
                centralPoint.createMission(NameMission, Description, date, LocationX, LocationY);

            }
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Niet alle velden zijn ingevuld");
            alert.setHeaderText("");
            alert.setContentText("Vul alle velden correct in");
            alert.showAndWait();
        }

    }

    //TODO JAVADOC
    public void setCentralPoint(CentralPoint centralPoint) {
        this.centralPoint = centralPoint;
    }
}
