package Database;

import CentralPoint.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.Point2D;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvdwi on 21-3-2016.
 */
public class DaoMaterial extends DaoGeneric<Material> {

    private final static String TABLENAME = DbTables.MATERIAAL.toString();
    private final String ID = "ID";
    private final String Naam = "naam";
    private final String Soort = "soort";
    private final String LocatieX = "LocatieX";
    private final String LocatieY = "LocatieY";
    private final String OpLocatie = "OpLocatie";

    /**
     * Sets up the connection for DaoMaterial
     * @param connection database connection
     *                   uses daoGenerics
     *                   database class of Material table
     */
    public DaoMaterial(Connection connection) {
        super(connection, TABLENAME);
    }

    /**
     * Get specified materials
     * if id=0 -> only available materials
     * @param id
     * @return
     */
    @Override
    public ObservableList<Material> getSpecificList(int id) {
        List<Material> materialList = new ArrayList();
        ObservableList<Material> materialObservableList = FXCollections.observableArrayList(materialList);
        switch (id) {
            case 0:
                String query = "SELECT ID, Naam, Soort, LocatieX, LocatieY, OpLocatie FROM Materiaal WHERE ID NOT IN (SELECT mm.MateriaalID FROM Materiaal_Missie mm INNER JOIN MISSIE mis ON mm.MissieID = mis.id WHERE mis.eindtijd IS NULL)";
                materialObservableList = getMaterial(query);
                break;
        }
        return materialObservableList;
    }

    /**
     * Returns all the materials in the database
     * @return list of staff
     * @see DaoGeneric#getAllRecord()
     */
    @Override
    public ObservableList<Material> getAllRecord() {
        ObservableList<Material> materialObservableList;

        String query = "SELECT * FROM " + TABLENAME;
        materialObservableList = getMaterial(query);

        return addMissionIds(materialObservableList);
    }

    /**
     * get material observable list
     *
     * @param query to execute
     * @return observable list of material
     */
    private ObservableList<Material> getMaterial(String query) {
        List<Material> materialList = new ArrayList();
        ObservableList<Material> materialObservableList = FXCollections.observableArrayList(materialList);
        ResultSet rs;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                materialObservableList.add(new Material(rs.getInt(1), rs.getString(2), rs.getString(3), new Point2D.Double(rs.getDouble(4), rs.getDouble(5)), rs.getBoolean(6)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materialObservableList;
    }

    /**
     * Adds missionids to the materials
     *
     * @param materials the list of materials where the missionids should be assigned to
     * @return a list of materials
     */
    private ObservableList<Material> addMissionIds(ObservableList<Material> materials) {
        ResultSet rs = null;
        String query = "SELECT * FROM MATERIAAL_MISSIE";

        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                getMaterialById(materials, rs.getInt(1)).addMissionId(rs.getInt(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materials;
    }

    /**
     * Get a material by an id
     * @param materials the list of the materials where the id should be in
     * @param id the id of a material
     * @return the material from the given list with the given id
     */
    private Material getMaterialById(ObservableList<Material> materials, int id) {
        for (Material material : materials) {
            if (material.getId() == id) {
                return material;
            }
        }
        return null;
    }

    /**
     * Update with int as key
     * @param value list of to update
     * @param key   key of row
     *              Update bool in a table row
     * @return true if update is successfully executed
     */
    @Override
    public boolean update(Material value, int key) {
        boolean result = false;
        String query = MessageFormat.format("UPDATE {0} SET {1} = ?, {2} = ?, {3} = ?, {4} = ?, {5} = ? WHERE ID = ?", TABLENAME, Naam, Soort, LocatieX, LocatieY, OpLocatie);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, value.getName());
            ps.setString(2, value.getSort());
            ps.setDouble(3, value.getLocation().getX());
            ps.setDouble(4, value.getLocation().getY());
            ps.setBoolean(5, value.isOnLocation());
            ps.setInt(6, key);
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * NOT IMPLEMENTED
     * Update with String as key
     * @param value the material to update
     * @param key the key of what should be updated
     * @return
     */
    @Override
    public boolean update(Material value, String key) {
        throw new NotImplementedException();
    }

    /**
     * Insert material in database
     *
     * @param value the material to insert into the db
     * @return
     */
    @Override
    public boolean insert(Material value) {
        boolean result = false;
        String query = MessageFormat.format("INSERT INTO {0} ({1}, {2}, {3}, {4}, {5}) VALUES (?, ?, ?, ?, ?)", TABLENAME, Naam, Soort, LocatieX, LocatieY, OpLocatie);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, value.getName());
            ps.setString(2, value.getSort());
            ps.setDouble(3, value.getLocation().getX());
            ps.setDouble(4, value.getLocation().getY());
            ps.setBoolean(5, value.isOnLocation());
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Delete material from database
     * @param key the id of the material that should be deleted
     * @return
     */
    @Override
    public boolean delete(int key) {
        boolean result = false;
        String query = MessageFormat.format("DELETE FROM {0} WHERE ID = ?", TABLENAME);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, key);
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * NOT IMPLEMENTED
     * Inserts two ints in one table
     * @param id first id
     * @param id1 second id
     */
    @Override
    public void insertTwoInts(int id, int id1) {
        throw new NotImplementedException();
    }

    /**
     * NOT IMPLEMENTED
     * Gets the value for the given int key
     * @param value object value
     * @param key   key
     * @return the material what belongs to the key
     */
    @Override
    public Material getObject(Material value, int key) {
        throw new NotImplementedException();
    }
}
