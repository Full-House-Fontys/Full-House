package Database;

import CentralPoint.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaj Suiker on 24-4-2016.
 */
public class DaoMessage extends DaoGeneric<Message> {

    private final static String TABLENAME = DbTables.BERICHT.toString();
    private final String ID = "ID";
    private final String Titel = "Titel";
    private final String Bericht = "Bericht";
    private final String Tijdstip = "Tijdstip";
    private final String MissieID = "MissieID";

    /**
     * uses daoGenerics
     * database class of Staff table
     * @param connection database connection
     */
    public DaoMessage(Connection connection) {
        super(connection, DbTables.BERICHT.toString());
    }

    /**
     * NOT IMPLEMENTED
     * returns a specific list of messages for given id
     * @param id
     * @return
     */
    @Override
    public ObservableList<Message> getSpecificList(int id) {
        ResultSet res;
        List<Message> last5Messages = new ArrayList<>();
        ObservableList<Message> obsMessage = FXCollections.observableArrayList(last5Messages);
        String query = "select * from bericht where id ="+id;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            res = ps.executeQuery();
            while (res.next()) {
                obsMessage.add(new Message(res.getString("Titel"), res.getString("Bericht"), res.getInt("MissieID")));
            }
            return obsMessage;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * get last 5
     * @return an observable list of last 5 messages
     */
    @Override
    public ObservableList<Message> getAllRecord() {
        ResultSet res;
        List<Message> last5Messages = new ArrayList<>();
        ObservableList<Message> obsMessage = FXCollections.observableArrayList(last5Messages);
        String query = "select * from bericht where id not in (select top ((select count(*) from bericht) - 5) id from bericht)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            res = ps.executeQuery();
            while (res.next()) {
                obsMessage.add(new Message(res.getString("Titel"), res.getString("Bericht"), res.getInt("MissieID")));
            }
            return obsMessage;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * NOT IMPLEMENTED
     * Updates the message with int key
     * @param value list of to update with int
     * @param key key of row
     * @return true if update is correctly executed
     */
    @Override
    public boolean update(Message value, int key) {
        throw new NotImplementedException();
    }

    /**
     * NOT IMPLEMENTED
     * Updates the message with String key
     * @param value list of to update with int
     * @param key key of row
     * @return true if update is correctly executed
     */
    @Override
    public boolean update(Message value, String key) {
        throw new NotImplementedException();
    }

    /**
     * Inserts a message in the database
     * @param value to insert
     * @return true if insert is correctly executed
     */
    @Override
    public boolean insert(Message value) {
        boolean result = false;

        String query = MessageFormat.format("INSERT INTO {0} ({1}, {2}, {3}, {4}) VALUES (?, ?, GETDATE(), ?)", TABLENAME, Titel, Bericht, Tijdstip, MissieID);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, value.getTitel());
            ps.setString(2, value.getMessage());
            if (value.getMissionId() == -1) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, value.getMissionId());
            }

            ps.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * NOT IMPLEMENTED
     * Delete mission from database
     * @param key the id of the mission to delete
     * @return true if delete is executed correctly
     */
    @Override
    public boolean delete(int key) {
        throw new NotImplementedException();
    }

    /**
     * NOT IMPLEMENTED
     * Inserts two ints in the database, most useful when there is a ManyToMany relation
     * @param id the id for the first table
     * @param id1 the id for the second table
     */
    @Override
    public void insertTwoInts(int id, int id1) {
        throw new NotImplementedException();
    }

    /**
     * NOT IMPLEMENTED
     * Gets a object from database for given value and key
     * @param value object value
     * @param key   key
     * @return
     */
    @Override
    public Message getObject(Message value, int key) {

        throw new NotImplementedException();
    }
}
