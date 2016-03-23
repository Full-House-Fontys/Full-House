package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kaj Suiker on 22-3-2016.
 */
public class StartController implements Initializable {
    @FXML
    private Button btnMaterial;
    @FXML
    private Button btnMissionControl;
    @FXML
    private Button btnAssignMissionControl;

    @FXML
    public void startMaterialScene(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("frmMaterialManagement.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)btnMaterial.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void startMissionControlScene(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("Task.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)btnMissionControl.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void startTeamAssignMissionScene(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("Task.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)btnMissionControl.getScene().getWindow();
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
