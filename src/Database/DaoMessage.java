package Database;

import CentralPoint.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaj Suiker on 24-4-2016.
 */
public class DaoMessage extends DaoGeneric<Message> {

    /**
     * @param connection database connection
     * uses daoGenerics
     * database class of Staff table
     */
    public DaoMessage(Connection connection) {
        super(connection, DbTables.BERICHT.toString());
    }

    @Override
    public ObservableList<Message> getSpecificList(int id) {
        return null;
    }


    /**
     * get last 5
     * @return
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
                obsMessage.add(new Message(res.getString("Titel"), res.getString("Bericht")));
            }
            return obsMessage;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Message value, int key) {
        return false;
    }

    @Override
    public boolean update(Message value, String key) {
        return false;
    }

    @Override
    public boolean insert(Message value) {
        return false;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }

    @Override
    public void insertTwoInts(int id, int id1) {

    }
}
