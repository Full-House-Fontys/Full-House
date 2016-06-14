package Database;

import CentralPoint.Mission;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qunfo on 23-3-2016.
 */
public class DaoMission extends DaoGeneric<Mission> {

    private final static String TABLENAME = DbTables.MISSIE.toString();
    private final String ID = "ID";
    private final String Naam = "Naam";
    private final String Beschrijving = "Beschrijving";
    private final String BeginTijd = "BeginTijd";
    private final String LaatsteUpdate = "LaatsteUpdate";
    private final String EindTijd = "EindTijd";
    private final String LocatieX = "LocatieX";
    private final String LocatieY = "LocatieY";
    private final String teamsAssigned = "";
    private final String verwachtetijd = "VerwachteTijdMin";
    /**
     * uses daoGenerics
     * database class of Mission table
     * @param connection database connection
     */
    public DaoMission(Connection connection) {
        super(connection, TABLENAME);
    }

    //TODO NOT IMPL
    //TODO JAVADOC
    @Override
    public ObservableList<Mission> getSpecificList(int id) {
        return null;
    }

    //TODO JAVADOC
    @Override
    public ObservableList getAllRecord() {
        List<Mission> missionList = new ArrayList();
        ObservableList<Mission> missionListObservableList = FXCollections.observableArrayList(missionList);
        ResultSet rs;

        String query = "SELECT * FROM " + TABLENAME + " WHERE EindTijd IS NULL";

        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                missionListObservableList.add(new Mission(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getDouble(7), rs.getDouble(8), rs.getInt(9)));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return missionListObservableList;
    }

    //TODO JAVADOC
    @Override
    public boolean update(Mission value, int key) {
        boolean result = false;
        String query = MessageFormat.format("UPDATE {0} SET {1} = ?, {2} = ?, {3} = ?, {4} = ?, {5} = ?, {6] = ?, {7} = ? {8} = ? WHERE ID = ?", TABLENAME, Naam, Beschrijving, BeginTijd, LaatsteUpdate, EindTijd, LocatieX, LocatieY, verwachtetijd);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, value.getName());
            ps.setString(2, value.getDescription());
            ps.setDate(3, (java.sql.Date) value.getStartTime());
            ps.setDate(4, (java.sql.Date) value.getLastUpdate());
            ps.setDate(5, (java.sql.Date) value.getEndTime());
            ps.setDouble(6, value.getLocationX());
            ps.setDouble(7, value.getLocationY());
            ps.setInt(8, value.getEstimatedTime());
            ps.setInt(9, key);
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    //TODO JAVADOC
    //TODO NOT IMPL
    @Override
    public boolean update(Mission value, String key) {
        return false;
    }

    //TODO JAVADOC
    @Override
    public boolean insert(Mission value) {
        boolean result = false;
        String query = MessageFormat.format("INSERT INTO {0} ({1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", TABLENAME, Naam, Beschrijving, BeginTijd, LaatsteUpdate, EindTijd, LocatieX, LocatieY, verwachtetijd);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, value.getName());
            ps.setString(2, value.getDescription());
            ps.setDate(3, new java.sql.Date(value.getStartTime().getTime()));
            if (value.getLastUpdate() == null) {
                ps.setDate(4, null);
            } else {
                ps.setDate(4, new java.sql.Date(value.getLastUpdate().getTime()));
            }
            if (value.getEndTime() == null) {
                ps.setDate(5, null);
            } else {
                ps.setDate(5, new java.sql.Date(value.getEndTime().getTime()));
            }
            ps.setDouble(6, value.getLocationX());
            ps.setDouble(7, value.getLocationY());
            ps.setInt(8, value.getEstimatedTime());
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    //TODO JAVDOC
    //TODO DUPLICATED CODE
    @Override
    public boolean delete(int key) {
        boolean result = false;
        String query = MessageFormat.format("DELETE FROM {0} WHERE ID = ?", TABLENAME, ID);
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
     * Inserts two ints in one table, this time for adding a material to a mission
     *
     * @param id  as the materialId
     * @param id1 as the missionId
     */
    @Override
    public void insertTwoInts(int id, int id1) {
        String query = "INSERT INTO MATERIAAL_MISSIE (MateriaalID, MissieID) VALUES (?, ?)";
        String query2 = "UPDATE MATERIAAL SET OPLOCATIE = 0 WHERE ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps.setInt(1, id);
            ps.setInt(2, id1);
            ps.executeUpdate();
            ps2.setInt(1, id);
            ps2.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //TODO NOT IMPL
    //TODO JAVADOC
    @Override
    public Mission getObject(Mission value, int key) {
        return null;
    }
}
