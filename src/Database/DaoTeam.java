package Database;

import CentralPoint.CentralPoint;
import CentralPoint.Staff;
import CentralPoint.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qunfo on 23-3-2016.
 */
public class DaoTeam extends DaoGeneric<Team> {
    private final static String TABLENAME = DbTables.TEAM.toString();
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
    private CentralPoint centralPoint;


    public DaoTeam(Connection connection) {
        super(connection, TABLENAME);
    }

    @Override
    public ObservableList<Team> getSpecificList(int missionid) {
        List<Team> missionList = new ArrayList();
        ObservableList<Team> teamListObservableList = FXCollections.observableArrayList(missionList);
        ResultSet rs;

        String query = "Select * FROM Personeel INNER JOIN " + TABLENAME + " ON Personeel.ID = Team.PersoneelID WHERE MissieID IS NULL;";

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
        return false;
    }

    @Override
    public boolean update(Team team, String key) {
        return false;
    }

    @Override
    public boolean insert(Team team) {
        return false;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }
}
