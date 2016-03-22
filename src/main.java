

//import static javafx.application.Application.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/frmMaterialManagement.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 896, 716));
        primaryStage.show();


        //DBConnection dbConnection = new DBConnection();

//        catch (SQLException ex) {
//            System.out.println("Connectie is niet gemaakt");
//        }

        //Staff staff = new Staff("s","s","s","s","s", new Point2D.Double(12.456,42.632), "s", 2, true);
//        MaterialManager mm = new MaterialManager();
//        mm.insertMaterial("AF-12-AD", "Ambulance", 12.24, 12.58, true);
//        mm.renewMaterials();
//        System.out.println("==============Initial=================");
//        for (Material m : mm.getMaterials()) {
//            System.out.println(m.getId() + m.getName());
//        }
//        mm.updateMaterial(11, "Harrie", "Fiets", new Point2D.Double(12.456, 42.632), true);
//        System.out.println("==============Update==================");
//        for (Material m : mm.getMaterials()) {
//            System.out.println(m.getId() + m.getName());
//        }
//        mm.deleteMaterial(mm.getMaterialById(11));
//        System.out.println("==============Delete==================");
//        for (Material m : mm.getMaterials()) {
//            System.out.println(m.getId() + m.getName());
//        }
        //DBInsert.insertStaff(TableType.STAFF, staff);

    }
}
