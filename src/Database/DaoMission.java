package Database;

import CentralPoint.Mission;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.MessageFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Qunfo on 23-3-2016.
 */
public class DaoMission extends DaoGeneric<Mission>{

    private final static String TABLENAME = DbTables.MISSIE.toString();
    private final String ID = "ID";
    private final String Naam = "Naam";
    private final String Beschrijving = "Beschrijving";
    private final String BeginTijd= "BeginTijd";
    private final String LaatsteUpdate = "LaatsteUpdate";
    private final String EindTijd = "EindTijd";
    private final String LocatieX = "LocatieX";
    private final String LocatieY = "LocatieY";
    private final String teamsAssigned = "";

    /**
     * @param connection database connection
     * uses daoGenerics
     * database class of Mission table
     */
    public DaoMission(Connection connection) {
        super(connection, TABLENAME);
    }

    @Override
    public ObservableList getAllRecord() {
        List<Mission> missionList = new ArrayList();
        ObservableList<Mission> missionListObservableList = FXCollections.observableArrayList(missionList);
        ResultSet rs = null;

        String query = "SELECT * FROM " + TABLENAME;

        try{
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                missionListObservableList.add(new Mission(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getDate(4),rs.getDate(5),rs.getDate(6),rs.getInt(7),rs.getInt(8)));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return missionListObservableList;
    }

    @Override
    public boolean update(Mission value, int key) {
        boolean result = false;
        String query = MessageFormat.format("UPDATE {0} SET {1} = ?, {2} = ?, {3} = ?, {4} = ?, {5} = ?, {6] = ?, {7} = ? WHERE ID = ?", TABLENAME, Naam, Beschrijving, BeginTijd, LaatsteUpdate, EindTijd, LocatieX,LocatieY);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, value.getName());
            ps.setString(2, value.getDescription());
            ps.setDate(3, (java.sql.Date) value.getStartTime());
            ps.setDate(4, (java.sql.Date) value.getLastUpdate());
            ps.setDate(5, (java.sql.Date) value.getEndTime());
            ps.setDouble(6, value.getLocationX());
            ps.setDouble(7, value.getLocationY());
            ps.setInt(8, key);
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Mission value, String key) {
        return false;
    }

    @Override
    public boolean insert(Mission value) {
        boolean result = false;
        String query = MessageFormat.format("INSERT INTO {0} ({1}, {2}, {3}, {4}, {5}, {6), {7} VALUES (?, ?, ?, ?, ?)", TABLENAME, Naam, Beschrijving, BeginTijd, LaatsteUpdate, EindTijd, LocatieX, LocatieY);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, value.getName());
            ps.setString(2, value.getDescription());
            ps.setDate(3, (java.sql.Date) value.getStartTime());
            ps.setDate(4, (java.sql.Date) value.getLastUpdate());
            ps.setDate(5, (java.sql.Date) value.getEndTime());
            ps.setDouble(6, value.getLocationX());
            ps.setDouble(7, value.getLocationY());
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int key) {
        boolean result = false;
        String query = MessageFormat.format("DELETE FROM {0} WHERE ID = ?", TABLENAME, ID);
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, key);
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return result;
    }
}
