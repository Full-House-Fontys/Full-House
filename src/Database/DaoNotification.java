package Database;

import CentralPoint.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhict on 06-05-16.
 */
public class DaoNotification extends DaoGeneric<Notification> {

    private final static String TABLENAME = DbTables.MELDING.toString();
    private final String ID = "ID";
    private final String Titel = "Titel";
    private final String Inhoud = "Inhoud";
    private final String Tijdstip= "Tijdstip";
    private final String MissieID = "MissieID";
    private final String GebruikerID = "GebruikerID";

    /**
     * @param connection database connection
     * uses daoGenerics
     * database class of Mission table
     */
    public DaoNotification(Connection connection) {
        super(connection, TABLENAME);
    }

    /**
     * Gives specific data when needed
     *
     * @param id
     * @return list of objects
     */
    @Override
    public ObservableList<Notification> getSpecificList(int id) {
        return null;
    }

    /**
     * Gives list of objects
     *
     * @return list of objects
     */
    @Override
    public ObservableList<Notification> getAllRecord() {
        List<Notification> notificationList = new ArrayList();
        ObservableList<Notification> notificationObservableList = FXCollections.observableArrayList(notificationList);
        ResultSet rs;

        String query = "SELECT * FROM " + TABLENAME;

        try{
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                notificationObservableList.add(new Notification(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6)));

            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return notificationObservableList;
    }

    /**
     * @param value list of to update with int
     * @param key   key of row
     *              Update bool in a table row
     * @return bool is completed
     */
    @Override
    public boolean update(Notification value, int key) {
        return false;
    }

    /**
     * @param value list of to update with int
     * @param key   key of row
     *              Update bool in a table row
     * @return bool is completed
     */
    @Override
    public boolean update(Notification value, String key) {
        return false;
    }

    /**
     * insert data with given value
     *
     * @param value
     * @return
     */
    @Override
    public boolean insert(Notification value) {
        return false;
    }

    /**
     * delete data in database of given key as int
     *
     * @param key
     * @return
     */
    @Override
    public boolean delete(int key) {
        return false;
    }

    /**
     * Inserts two ints in a database, useful for multiple to multiple relationships with pairing tables
     *
     * @param id
     * @param id1
     */
    @Override
    public void insertTwoInts(int id, int id1) {

    }

    @Override
    public Notification getObject(Notification value, int key) {
        return null;
    }
}
