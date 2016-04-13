package GUI;

import HulpDienst.HulpDienst;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
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
    private HulpDienst hulpdienst;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hulpdienst = new HulpDienst();
        LVpersoneel.setItems(hulpdienst.renewStaffList());
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


}
