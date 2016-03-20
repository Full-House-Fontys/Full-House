package Database;

import DBElements.Staff;

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
     * @return list of staff
     * @see DaoGeneric#getAllRecord()
     */
    @Override
    public List<Staff> getAllRecord() {
        List<Staff> allStaff = new ArrayList<>();
        ResultSet res = null;

        String query = "SELECT * FROM " + TABLENAME;

        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery(query);
            while (res.next()) {
                allStaff.add(new Staff(res.getString(Voornaam),
                        res.getString(Tussenvoegesel), res.getString(Achternaam), res.getString(Gebruikersnaam),
                        res.getString(Wachtwoord), new Point2D.Double(res.getDouble(LocatieX),
                        res.getDouble(LocatieY)), res.getString(Soort), res.getInt(TeamID),
                        res.getInt(OpLocatie) == 0 ? false : true));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStaff;
    }

    /**
     * @param value object to update
     * @param key   key of row
     *              Update bool in a table row
     * @return
     */
    @Override
    public boolean update(Staff value, String key) {
        boolean result = false;
        String bit = value.isOnLocation() ? "1" : "0";
        String query = "UPDATE " + TABLENAME + " Set OpLocatie = " + bit + " WHERE Gebruikersnaam = ?";
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
}
