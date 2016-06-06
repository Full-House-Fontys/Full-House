//import static javafx.application.Application.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * jvdwi on 9-3-2016.
 */
public class CentralPoint extends javafx.application.Application {

    /**
     * to launch the program
     */
    public static void main(String[] args) {
        launch(args);

    }

    /**
     * start the CentralPoint program the central point program
     *
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        primaryStage.setTitle("Centraal punt programma");
        primaryStage.setScene(new Scene(root, 900, 650));
        primaryStage.show();
    }
}