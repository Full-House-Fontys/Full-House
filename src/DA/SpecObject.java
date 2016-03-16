package DA;

import DBElements.Staff;

import java.awt.geom.Point2D;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class SpecObject {
    public static Staff getPojo(ResultSet rs) throws Exception {
        Staff personeel = new Staff();
        personeel.setName(rs.getString("Voornaam"));
        personeel.setLastName(rs.getString("Achternaam"));
        Point2D location = new Point2D.Double(rs.getDouble("LocatieX"), rs.getDouble("LocatieY"));
        personeel.setLocation(location);

        return personeel;
    }

    public static String getColumn(ResultSet rs) throws Exception{
        return rs.getString("COLUMN_NAME");
    }
}
