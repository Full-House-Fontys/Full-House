

//import static javafx.application.Application.*;

import DA.DBConnection;
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
        Staff staff = new Staff("s","s","s","s","s", new Point2D.Double(12.456,42.632), "s", 2, true);
        DBInsert.insertStaff(TableType.STAFF, staff);
    }
}
