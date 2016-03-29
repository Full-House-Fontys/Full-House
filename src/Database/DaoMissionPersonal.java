package Database;

import javafx.collections.ObservableList;

import java.sql.Connection;

/**
 * Created by Qunfo on 23-3-2016.
 */
public class DaoMissionPersonal extends DaoGeneric<int[]>{
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
        return false;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }
}
