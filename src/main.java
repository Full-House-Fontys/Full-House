

//import static javafx.application.Application.*;

import DA.DBConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import DA.DBInsert;
import DA.TableType;
import DBElements.Staff;
import javafx.stage.Stage;

import java.awt.geom.Point2D;
import java.sql.*;

/**
 * Created by jvdwi on 9-3-2016.
 */
public class main extends javafx.application.Application {
    public static Connection conn;

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Task.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 650));
        primaryStage.show();


        DBConnection dbConnection = new DBConnection();
    }
}
