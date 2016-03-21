package GUI;

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
    private MaterialManager mm;

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


    public MaterialController() {
        mm = new MaterialManager();
        mm.renewMaterials();

        Platform.runLater(() -> tvMaterials.setItems(mm.getMaterials()));
    }


}
