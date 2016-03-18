package GUI;

import DBElements.Material;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by fhict on 18-03-16.
 */
public class MaterialController {
    private ObservableList<Material> obsMat;
    private MaterialManager mm;

    @FXML
    private TableView tvMaterials;
    @FXML
    private TableColumn tcID;
    @FXML
    private TableColumn tcName;
    @FXML
    private TableColumn tcSort;
    @FXML
    private TableColumn tcLocation;
    @FXML
    private TableColumn tcOnLoc;


    public MaterialController() {
        obsMat = FXCollections.observableArrayList();
        mm = new MaterialManager();
        mm.renewMaterials();
        Platform.runLater(() -> tvMaterials.setItems(obsMat));
    }
}
