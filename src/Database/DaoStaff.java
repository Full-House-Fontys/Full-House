package Database;

import CentralPoint.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private final String Tussenvoegesel = "Tussenvoegsel";
    private final String Voornaam = "Voornaam";
    private final String Gebruikersnaam = "Gebruikersnaam";
    private final String Wachtwoord = "Wachtwoord";
    private final String LocatieX = "LocatieX";
    private final String LocatieY = "LocatieY";
    private final String Soort = "Soort";
    private final String TeamID = "TeamID";
    private final String OpLocatie = "OpLocatie";

    /**
     * @param connection database connection
     * uses daoGenerics
     * database class of Staff table
     */
    public DaoStaff(Connection connection) {
        super(connection, TABLENAME);
    }

    /**
     * Returns list of people who are involved in the mission
     * @param id
     * @return list of Staff
     */
    @Override
    public ObservableList<Staff> getSpecificList(int id) {
        ArrayList staffList = new ArrayList();
        ObservableList<Staff> staffListObservableList = FXCollections.observableArrayList(staffList);
        ResultSet rs;

        String query = "Select DISTINCT Soort From Personeel,Personeel_Missie WHERE Personeel.ID = Personeel_Missie.PersoneelID AND Personeel_Missie.MissieID ="+id + ";";

        try{
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                staffListObservableList.add(new Staff(null, null, null, null, null, null, rs.getString(1),0, false));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return staffListObservableList;
    }

    /**
     * @return list of staff
     * @see DaoGeneric#getAllRecord()
     */
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
                obsStaff.add(new Staff(res.getString(Voornaam),
                        res.getString(Tussenvoegesel), res.getString(Achternaam), res.getString(Gebruikersnaam),
                        res.getString(Wachtwoord), new Point2D.Double(res.getDouble(LocatieX),
                        res.getDouble(LocatieY)), res.getString(Soort), res.getInt(TeamID),
                        res.getInt(OpLocatie) != 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obsStaff;
    }

    /**
     * @param value object to update
     * @param key   key of row
     *              Update bool in a table row
     * @return
     */
    @Override
    public boolean update(Staff value, int key) {
        return false;
    }

    @Override
    public boolean update(Staff value, String key) {
        boolean result = false;
        String bit = value.isOnLocation() ? "1" : "0";
        String query = "UPDATE " + TABLENAME + " Set OpLocatie = " + bit + " WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, key);
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Staff value) {
        return false;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }
}
