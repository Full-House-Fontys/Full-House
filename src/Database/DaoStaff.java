package Database;

import CentralPoint.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.Point2D;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaj Suiker on 20-3-2016.
 */
public class DaoStaff extends DaoGeneric<Staff> {

    private final static String TABLENAME = DbTables.PERSONEEL.toString();
    private final String ID = "ID";
    private final String Achternaam = "Achternaam";
    private final String Tussenvoegsel = "Tussenvoegsel";
    private final String Voornaam = "Voornaam";
    private final String Gebruikersnaam = "Gebruikersnaam";
    private final String Wachtwoord = "Wachtwoord";
    private final String LocatieX = "LocatieX";
    private final String LocatieY = "LocatieY";
    private final String Soort = "Soort";
    private final String OpLocatie = "OpLocatie";
    private final String TeamID = "TeamID";
    private final String MissionID = "MissieID";

    /**
     * uses daoGenerics
     * database class of Staff table
     * @param connection database connection
     */
    public DaoStaff(Connection connection) {
        super(connection, TABLENAME);
    }

    /**
     * Returns list of people who are involved in the mission
     * @param id for specific list
     * @return list of Staff
     */
    //TODO DUPLICATE CODE
    @Override
    public ObservableList<Staff> getSpecificList(int id) {
        ArrayList staffList = new ArrayList();
        ObservableList<Staff> staffListObservableList = FXCollections.observableArrayList(staffList);
        ResultSet rs;
        String query;
        if (id == 0) {
            query = "Select * FROM Personeel INNER JOIN Team ON Personeel.ID = Team.PersoneelID AND Team.MissieID IS NOT NULL AND Personeel.OpLocatie = 1";
        } else {
            query = "Select DISTINCT Personeel.* FROM Personeel INNER JOIN Team ON Personeel.ID = Team.PersoneelID AND Personeel.OpLocatie = 0";
        }

        try{
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                staffListObservableList.add(new Staff(rs.getInt(ID), rs.getString(Voornaam),
                        rs.getString(Tussenvoegsel), rs.getString(Achternaam), rs.getString(Gebruikersnaam),
                        rs.getString(Wachtwoord), new Point2D.Double(rs.getDouble(LocatieX),
                        rs.getDouble(LocatieY)), rs.getString(Soort),
                        rs.getInt(OpLocatie) == 0));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return staffListObservableList;
    }

    /**
     * Get all members of staff
     * @return list of staff
     * @see DaoGeneric#getAllRecord()
     */
    //TODO DUPLICATE CODE
    @Override
    public ObservableList<Staff> getAllRecord() {
        List<Staff> allStaff = new ArrayList<>();
        ObservableList<Staff> obsStaff = FXCollections.observableArrayList(allStaff);
        ResultSet res;

        String query = "SELECT * FROM " + TABLENAME;

        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery(query);
            while (res.next()) {
                obsStaff.add(new Staff(res.getInt(ID), res.getString(Voornaam),
                        res.getString(Tussenvoegsel), res.getString(Achternaam), res.getString(Gebruikersnaam),
                        res.getString(Wachtwoord), new Point2D.Double(res.getDouble(LocatieX),
                        res.getDouble(LocatieY)), res.getString(Soort),
                        res.getInt(OpLocatie) == 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obsStaff;
    }

    /**
     * Used for getting login data (true/false)
     * @param value object to update
     * @param key   key of row
     *              Update bool in a table row
     * @return
     */
    @Override
    public boolean update(Staff value, int key) {
        boolean result = false;
        ResultSet res;
            String bit = value.isOnLocation() ? "1" : "0";
            String query = "UPDATE " + TABLENAME + " Set OpLocatie = " + bit + " WHERE id = ?";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, (String.valueOf(value.getId())));
                ps.executeUpdate();
                result = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
    }

    /**
     * update staff values
     *
     * @param value list of to update with int
     * @param key   key of row
     * @return
     */
    @Override
    public boolean update(Staff value, String key) {
        throw new NotImplementedException();
    }

    /**
     * insert new staff
     * @param value
     * @return
     */
    @Override
    public boolean insert(Staff value) {
        throw new NotImplementedException();
    }

    /**
     * delete staff from database
     * @param key
     * @return
     */
    @Override
    public boolean delete(int key) {
        throw new NotImplementedException();
    }

    /**
     * not used method
     * @param id
     * @param id1
     */
    @Override
    public void insertTwoInts(int id, int id1) {
        throw new NotImplementedException();
    }

    /**
     * returns missionID that Staff is part of
     *
     * @param value object value
     * @param key   key
     * @return the Staff
     */
    @Override
    public Staff getObject(Staff value, int key) {
        Staff result = new Staff();
        result.setId(-1);
        ResultSet res;
        //String query = "SELECT * FROM Personeel WHERE Gebruikersnaam = ? AND Wachtwoord = ?";
        String query = "SELECT Missieid From Team WHERE PersoneelID = (SELECT ID FROM Personeel WHERE Gebruikersnaam=? AND Wachtwoord=?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, value.getUserName());
            ps.setString(2, value.getPassword());
            res = ps.executeQuery();
            while (res.next()) {
                result.setId(res.getInt(MissionID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
