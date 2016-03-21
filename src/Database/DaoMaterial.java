package Database;

import CentralPoint.Material;

import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvdwi on 21-3-2016.
 */
public class DaoMaterial extends DaoGeneric<Material>{

    private final static String TABLENAME = DbTables.MATERIAAL.toString();
    private final String ID = "ID";
    private final String Naam = "naam";
    private final String Soort = "soort";
    private final String LocatieX = "LocatieX";
    private final String LocatieY = "LocatieY";
    private final String OpLocatie = "OpLocatie";

    public DaoMaterial(Connection connection) {
        super(connection, TABLENAME);
    }

    @Override
    public List<Material> getAllRecord() {
        List<Material> materials = new ArrayList();
        ResultSet rs = null;

        String query = "SELECT * FROM " + TABLENAME;

        try{
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                materials.add(new Material(rs.getInt(0), rs.getString(1), rs.getString(2), new Point2D.Double(rs.getDouble(3), rs.getDouble(4)), rs.getBoolean(5)));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return materials;
    }

    @Override
    public boolean update(Material value, int key) {
        boolean result = false;
        return result;
    }

    @Override
    public boolean insert(Material value) {
        return false;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }
}
