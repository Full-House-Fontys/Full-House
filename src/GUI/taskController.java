package GUI;

import CentralPoint.Staff;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class taskController implements Initializable {

    @FXML
    TableView tvStaffOnLocation;

    /**
     * initialize the taskController
     *
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * show all staff on location
     * @param staffOnLocation
     */
    public void setList(ObservableList<Staff> staffOnLocation) {
        tvStaffOnLocation.setItems(staffOnLocation);
    }
}
