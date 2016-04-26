/**
 * Created by Mark on 6-4-2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Hupldienst extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Hulpdienst.fxml"));
        primaryStage.setTitle("Hulpdienst");
        primaryStage.setScene(new Scene(root, 619, 466));
        primaryStage.show();
    }
}
