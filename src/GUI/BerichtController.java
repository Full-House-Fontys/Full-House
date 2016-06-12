package GUI;

import CentralPoint.Message;
import CentralPoint.Mission;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class BerichtController implements Initializable {

    @FXML
    private ListView lvIngekomenBerichten;
    @FXML
    private ListView lvActieveMissies;
    @FXML
    private ListView lvTeamLog;

    @FXML
    private Button btnTest;
    @FXML
    private Button btnMissieSelect;

    //TODO JAVADOC
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = FXCollections.observableArrayList(
                "Een", "Twee", "Drie", "Vier Vijf");
        lvIngekomenBerichten.setItems(items);
        ObservableList<String> items2 = FXCollections.observableArrayList(
                "Nee", "Eewt", "Dird", "Reiv Fjiv");
        lvActieveMissies.setItems(items2);
        ObservableList<String> items3 = FXCollections.observableArrayList(
                ".", "..", "...", ".... .....");
        lvTeamLog.setItems(items3);
    }

    //TODO JAVADOC
    @FXML
    private void simuleerTestBericht(ActionEvent event) {
        Message m = new Message("test", "inhoud", 0);

        ObservableList<String> items = lvIngekomenBerichten.getItems();
        items.add(m.getTitel());
        lvIngekomenBerichten.setItems(items);

        showPopup("New message");
    }

    //TODO JAVADOC
    //TODO DUPLICATE CODE
    private void showPopup(String bericht){
        Stage primaryStage = new Stage();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(bericht));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    //TODO JAVADOC
    @FXML
    private void selecteerMissie(ActionEvent event) {
        Mission missie;
        try{
            String titel = lvActieveMissies.getSelectionModel().getSelectedItem().toString();
            missie = new Mission(0, titel, "Hulp nodig!", null, null, null, 1.000001, 1.000001, -1);
        } catch (Exception e) {
            missie = new Mission(0, "Error", "Er is geen missie geselecteerd", null, null, null, 1.000001, 1.000001, -1);
        }

        showPopup("Naam: " + missie.getName() + "\n" + "Omschrijving: " + missie.getDescription());
    }

    //TODO JAVADOC
    //TODO NOT IMPL
    @FXML
    private void zendBerichtNaarTeam(ActionEvent event) {

    }

}
