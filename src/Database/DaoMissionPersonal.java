package Database;

import CentralPoint.Mission;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Qunfo on 23-3-2016.
 */
public class DaoMissionPersonal extends DaoGeneric<int[]>{
    private final static String TABLENAME = DbTables.Personeel_Missie.toString();
    private final String personeelID = "PersoneelID";
    private final String missionID = "missionID";

    public DaoMissionPersonal(Connection connection) {
        super(connection, TABLENAME);
    }

    @Override
    public ObservableList<int[]> getSpecificList(int id) {
        return null;
    }

    @Override
    public ObservableList<int[]> getAllRecord() {
        return null;
    }

    @Override
    public boolean update(int[] value, int key) {
        return false;
    }

    @Override
    public boolean update(int[] value, String key) {
        return false;
    }

    @Override
    public boolean insert(int[] value) {
        boolean result = false;
        String query = MessageFormat.format("INSERT INTO {0} ({1}, {2}, {3}, {4}, {5}, {6), {7} VALUES (?, ?, ?, ?, ?)", TABLENAME, personeelID, missionID);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, value[0]);
            ps.setInt(2, value[1]);
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }
}
