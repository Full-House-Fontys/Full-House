package Database;

import CentralPoint.MissionPlan;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * Created by Qun on 15-6-2016.
 */
public class DaoMissionPlan extends DaoGeneric<MissionPlan> {
    private final static String TABLENAME = DbTables.MISSIE_STAPPENPLAN.toString();

    public DaoMissionPlan(Connection connection) {
        super(connection, TABLENAME);
    }

    @Override
    public ObservableList<MissionPlan> getSpecificList(int id) {
        return null;
    }

    @Override
    public ObservableList<MissionPlan> getAllRecord() {
        return null;
    }

    @Override
    public boolean update(MissionPlan value, int key) {
        return false;
    }

    @Override
    public boolean update(MissionPlan value, String key) {
        return false;
    }

    @Override
    public boolean insert(MissionPlan missionPlan) {
        boolean result = false;
        String query = MessageFormat.format("INSERT INTO {0} ({1}, {2}) VALUES (?, ?)", TABLENAME, "MissieID", "Stappen");
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, missionPlan.getMissionID());
            ps.setString(2, missionPlan.getAllSteps());
            ps.execute();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }

    @Override
    public void insertTwoInts(int id, int id1) {

    }

    @Override
    public MissionPlan getObject(MissionPlan value, int key) {
        return null;
    }
}
