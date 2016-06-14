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
    @FXML
    private TextField TFestimatedTime;

    private CentralPoint centralPoint;
    private int estimatedTime;

    /**
     * creates and saves a mission
     */
    public void createMission() {
        try {
            Date date = new Date();
            Double LocationX = Double.parseDouble(TFlocationXMission.getText());
            Double LocationY = Double.parseDouble(TFlocationYMission.getText());
            String NameMission = TFnameMission.getText();
            String Description = TAdescriptionMission.getText();
            try {
                estimatedTime = Integer.parseInt(TFestimatedTime.getText());
            } catch (Exception e) {

            }

            if (!NameMission.isEmpty() && !Description.isEmpty()) {
                centralPoint.createMission(NameMission, Description, date, LocationX, LocationY, estimatedTime);

            }
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Niet alle velden zijn ingevuld");
            alert.setHeaderText("");
            alert.setContentText("Vul alle velden correct in");
            alert.showAndWait();
        }

    }

    /**
     * set the central point class in the controller.
     *
     * @param centralPoint
     */
    public void setCentralPoint(CentralPoint centralPoint) {
        this.centralPoint = centralPoint;
    }
}
