package Database;

import CentralPoint.CentralPoint;
import CentralPoint.Staff;
import CentralPoint.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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


    public DaoTeam(Connection connection) {
        super(connection, TABLENAME);
    }

    @Override
    public ObservableList<Team> getSpecificList(int missionid) {
        List<Team> missionList = new ArrayList();
        ObservableList<Team> teamListObservableList = FXCollections.observableArrayList(missionList);
        ResultSet rs;

        String query = "Select * FROM Personeel INNER JOIN " + TABLENAME + " ON Personeel.ID = Team.PersoneelID WHERE MissieID IS NULL";

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

    @Override
    public ObservableList<Team> getAllRecord() {
        List<Team> missionList = new ArrayList();
        ObservableList<Team> teamListObservableList = FXCollections.observableArrayList(missionList);
        ResultSet rs;

        String query = "Select * FROM Personeel INNER JOIN " + TABLENAME + " ON Personeel.ID = Team.PersoneelID";

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

    @Override
    public boolean update(Team team, String key) {
        return false;
    }

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

    @Override
    public boolean delete(int key) {
        return false;
    }

    @Override
    public void insertTwoInts(int id, int id1) {

    }

    @Override
    public Team getObject(Team value, int key) {
        return null;
    }
}
