

//import static javafx.application.Application.*;

import DA.DBConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Created by jvdwi on 9-3-2016.
 */
public class main extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/TaskV1.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 650));
        primaryStage.show();

        try {
            DBConnection dbConnection = new DBConnection();
        } catch (SQLException ex) {
            System.out.println("Connectie is niet gemaakt");
        }


    }
}
