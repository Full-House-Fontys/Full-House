package Database;

import CentralPoint.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    /**
     * @param connection database connection
     * uses daoGenerics
     * database class of Material table
     */
    public DaoMaterial(Connection connection) {
        super(connection, TABLENAME);
    }

    /**
     * @return list of staff
     * @see DaoGeneric#getAllRecord()
     */
    @Override
    public ObservableList<Material> getAllRecord() {
        List<Material> materialList = new ArrayList();
        ObservableList<Material> materialObservableList = FXCollections.observableArrayList(materialList);
        ResultSet rs = null;

        String query = "SELECT * FROM " + TABLENAME;

        try{
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                materialObservableList.add(new Material(rs.getInt(1), rs.getString(2), rs.getString(3), new Point2D.Double(rs.getDouble(4), rs.getDouble(5)), rs.getBoolean(6)));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return materialObservableList;
    }

    /**
     * update with int as key
     * @param value list of to update
     * @param key key of row
     * Update bool in a table row
     * @return
     */
    @Override
    public boolean update(Material value, int key) {
        boolean result = false;
        return result;
    }

    /**
     * update with String as key
     * @param value
     * @param key
     * @return
     */
    @Override
    public boolean update(Material value, String key) {
        return false;
    }

    /**
     * insert material in database
     * @param value
     * @return
     */
    @Override
    public boolean insert(Material value) {
        return false;
    }

    /**
     * delete material from database
     * @param key
     * @return
     */
    @Override
    public boolean delete(int key) {
        return false;
    }
}
