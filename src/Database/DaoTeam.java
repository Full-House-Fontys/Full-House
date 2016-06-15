package Database;

import CentralPoint.CentralPoint;
import CentralPoint.Staff;
import CentralPoint.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.Point2D;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qunfo on 23-3-2016.
 */
public class DaoTeam extends DaoGeneric<Team> {
    private final static String TABLENAME = DbTables.TEAM.toString();
    private final String ID = "TeamID";
    private final String Naam = "TeamNaam";
    private final String Beschrijving = "Beschrijving";
    private final String BeginTijd = "BeginTijd";
    private final String LaatsteUpdate = "LaatsteUpdate";
    private final String EindTijd = "EindTijd";
    private final String LocatieX = "LocatieX";
    private final String LocatieY = "LocatieY";
    private final String teamsAssigned = "";
    private final String personeelID = "PersoneelID";
    private final String missionID = "missieID";
    private CentralPoint centralPoint;

    /**
     * constructor of DaoTeam
     *
     * @param connection
     */
    public DaoTeam(Connection connection) {
        super(connection, TABLENAME);
    }

    /**
     * get all staff where missionID is not null
     * @param missionid
     * @return
     */
    @Override
    public ObservableList<Team> getSpecificList(int missionid) {
        String query = "Select * FROM Personeel INNER JOIN " + TABLENAME + " ON Personeel.ID = Team.PersoneelID WHERE MissieID IS NULL";

        return getTeam(query);
    }

    /**
     * get all the teams that exist
     * @return
     */
    @Override
    public ObservableList<Team> getAllRecord() {
        String query = "Select * FROM Personeel INNER JOIN " + TABLENAME + " ON Personeel.ID = Team.PersoneelID";

        return getTeam(query);
    }

    private ObservableList<Team> getTeam(String query) {
        List<Team> missionList = new ArrayList();
        ObservableList<Team> teamListObservableList = FXCollections.observableArrayList(missionList);
        ResultSet rs;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                boolean teamExist = false;
                Staff teamMember = new Staff(rs.getInt("ID"), rs.getString("Voornaam"),
                        rs.getString("Tussenvoegsel"), rs.getString("Achternaam"), rs.getString("Gebruikersnaam"),
                        rs.getString("Wachtwoord"), new Point2D.Double(rs.getDouble("LocatieX"),
                        rs.getDouble("LocatieY")), rs.getString("Soort"), rs.getInt("OpLocatie") == 0);
                for (Team team : teamListObservableList) {
                    if (team.getName().equals(rs.getString("TeamNaam"))) {
                        team.addMember(teamMember);
                        team.addMissie(rs.getInt("MissieID"));
                        teamExist = true;
                    }
                }
                ArrayList<Staff> members = new ArrayList<>();
                ArrayList<Integer> missions = new ArrayList<>();
                if (!teamExist) {
                    missions.add(rs.getInt("MissieID"));
                    members.add(teamMember);
                    teamListObservableList.add(new Team(rs.getInt("TeamID"), rs.getString("TeamNaam"), members, missions));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return teamListObservableList;
    }

    /**
     * update the teams mission
     * @param team
     * @param key key of row
     * @return
     */
    @Override
    public boolean update(Team team, int key) {
        boolean result = false;
        String query = MessageFormat.format("UPDATE {0} SET {1} = ? WHERE TeamID = ?", TABLENAME, missionID);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, key);
            ps.setInt(2, team.getId());
            ps.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * update to the team
     * @param team
     * @param key key of row
     * @return
     */

    @Override
    public boolean update(Team team, String key) {
        throw new NotImplementedException();
    }

    /**
     * insert new team
     * @param team
     * @return
     */
    @Override
    public boolean insert(Team team) {
        for (Staff staff : team.getTeamMembers()) {
            String query = MessageFormat.format("INSERT INTO {0} ({1}, {2}, {3}) VALUES (?, ?, ?)", TABLENAME, ID, Naam, personeelID);
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, team.getId());
                ps.setString(2, team.getName());
                ps.setInt(3, staff.getId());
                ps.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * remove team from database
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
     * get a specific team object.
     * @param value object value
     * @param key   key
     * @return
     */

    @Override
    public Team getObject(Team value, int key) {
        throw new NotImplementedException();
    }
}
