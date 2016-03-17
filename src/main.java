

//import static javafx.application.Application.*;

import DA.DBConnection;
import DBElements.Material;
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
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Task.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 650));
        primaryStage.show();


        DBConnection dbConnection = new DBConnection();

//        catch (SQLException ex) {
//            System.out.println("Connectie is niet gemaakt");
//        }

        //Staff staff = new Staff("s","s","s","s","s", new Point2D.Double(12.456,42.632), "s", 2, true);
        MaterialManage mm = new MaterialManage();
        //mm.insertMaterial("AF-12-AD", "Ambulance", 12.24, 12.58, true);
        mm.renewMaterials();
        System.out.println("==============Initial=================");
        for (Material m : mm.getMaterials()) {
            System.out.println(m.getId() + m.getName());
        }
        mm.updateMaterial(2, "Harrie", "Fiets", new Point2D.Double(12.456, 42.632), true);
        System.out.println("==============Update==================");
        for (Material m : mm.getMaterials()) {
            System.out.println(m.getId() + m.getName());
        }
        mm.deleteMaterial(mm.getMaterialById(2));
        System.out.println("==============Delete==================");
        for (Material m : mm.getMaterials()) {
            System.out.println(m.getId() + m.getName());
        }
        //DBInsert.insertStaff(TableType.STAFF, staff);

    }
}
