package DA;

import DBElements.Staff;

import javax.naming.Binding;
import java.awt.geom.Point2D;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class SpecObject {
    public static Staff getPojo(ResultSet rs) throws Exception {
        Staff staff = new Staff();

        staff.setName(rs.getString("Voornaam"));
        staff.setLastName(rs.getString("Achternaam"));
        Point2D location = new Point2D.Double(rs.getDouble("LocatieX"), rs.getDouble("LocatieY"));
        staff.setLocation(location);

        return staff;
    }

    public static String getColumn(ResultSet rs) throws Exception{
        return rs.getString("COLUMN_NAME");
    }

    public static String getDataType(ResultSet rs) throws Exception{
        return rs.getString("DATA_TYPE");
    }
}
