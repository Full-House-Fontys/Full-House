package GUI;

import Webservice.TestingWebserviceServer;
import Webservice.TestingWebserviceServerSoap;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by fhict on 25-05-16.
 */
public class pubOnWebController {
    @FXML
    private TextField tfTitle;
    @FXML
    private TextArea taContent;

    /**
     * Sends message to the asp.net website
     * Launches the asp website after submitting a notification
     *
     * @throws IOException
     * @throws URISyntaxException
     */
    public void publishTextOnWeb() throws IOException, URISyntaxException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ontbrekende gegevens");
        alert.setHeaderText("Vul de nodige velden in");

        if (tfTitle.getText().isEmpty() && taContent.getText().isEmpty()) {

        } else if (tfTitle.getText().isEmpty()) {
            alert.setContentText("Titel");
            alert.showAndWait();
        } else if (taContent.getText().isEmpty()) {
            alert.setContentText("Beschrijving");
            alert.showAndWait();
        } else {
            TestingWebserviceServerSoap service = new TestingWebserviceServer().getTestingWebserviceServerSoap();
            service.publishEmergencyMessage(tfTitle.getText(), taContent.getText());
            URL url = new URL("http://localhost:19157/Webpages/GetMessagesFromCims");
            URI uri = url.toURI();
            java.awt.Desktop.getDesktop().browse(uri);
        }
    }
}