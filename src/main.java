//import static javafx.application.Application.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by jvdwi on 9-3-2016.
 */
public class main extends javafx.application.Application {

    //TODO JAVADOC
    public static void main(String[] args) {
        launch(args);

    }

    //TODO JAVADOC
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 650));
        primaryStage.show();
    }
}
