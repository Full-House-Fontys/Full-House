package GUI;

import CentralPoint.CentralPoint;
import CentralPoint.Material;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.geom.Point2D;

/**
 * Created by fhict on 18-03-16.
 */
public class MaterialController {
    private ObservableList<Material> obsMat;
    private CentralPoint cp;

    @FXML
    private TableView<Material> tvMaterials;
    @FXML
    private TableColumn<Material, Integer> tcID;
    @FXML
    private TableColumn<Material, String> tcName;
    @FXML
    private TableColumn<Material, String> tcSort;
    @FXML
    private TableColumn<Material, Point2D> tcLocation;
    @FXML
    private TableColumn<Material, Boolean> tcOnLoc;

    /**
     * constructs the materialController
     */
    public MaterialController() {
        cp = new CentralPoint();

        Platform.runLater(() -> tvMaterials.setItems(cp.getMaterials()));
    }


}
