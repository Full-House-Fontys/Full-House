package HulpDienst;

import CentralPoint.Staff;
import CentralPoint.Team;
import Database.DaoManager;
import Database.DbTables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mark on 6-4-2016.
 */
public class HelpService {

    private Socket requestSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private DaoManager daoManager;
    private List<Staff> staffList;
    private ObservableList<Staff> staffObservableList;
    private ObservableList<Team> OBavailableteams;
    private ObservableList<Team> OBallteams;
    private List<TeamRequest> listrequests;
    private ObservableList<TeamRequest> RequestObservableList;
    private IRequests RQ;

    public HelpService() {
        daoManager = DaoManager.INSTANCE;
        staffList = new ArrayList<>();
        listrequests = new ArrayList<>();
        OBavailableteams = FXCollections.observableArrayList();
        OBallteams = FXCollections.observableArrayList();
        staffObservableList = FXCollections.observableArrayList(staffList);
        RequestObservableList = FXCollections.observableArrayList(listrequests);
        RQ = new requests();
        try {
            requestSocket = new Socket("localhost", 2006);
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                receiveSocket();
            }
        }, 0, 1000);
    }

    /**
     * Refresh the staff that is currently available
     *
     * @return the observable list of the staff
     */
    public ObservableList<Staff> renewStaffList() {
        staffObservableList.clear();
        staffObservableList = daoManager.getDao(DbTables.PERSONEEL).getSpecificList(1);
        return staffObservableList;
    }

    /**
     * Refresh the teams that are available
     *
     * @return the new observable list of the teams
     */
    public ObservableList<Team> renewteams() {
        OBavailableteams.clear();
        OBavailableteams = daoManager.getDao(DbTables.TEAM).getSpecificList(1);
        return OBavailableteams;
    }

    /**
     * Gets all teams
     *
     * @return the observable list of the teams
     */
    public ObservableList<Team> getAllTeams() {
        OBallteams.clear();
        OBallteams = daoManager.getDao(DbTables.TEAM).getAllRecord();
        return OBallteams;
    }

    /**
     * Gets all teamrequests
     * @return the teamrequests
     */
    public ObservableList<TeamRequest> getTeamRequests() {
        return RequestObservableList;
    }

    /**
     * Filters the stafflist based on the filter you selected
     * @param filter the filter as String
     * @return the observable list of the staff
     */
    public ObservableList<Staff> filterStaffList(String filter) {
        if (filter.equals("Alle")) {
            return staffObservableList;
        }
        ObservableList<Staff> tempList = FXCollections.observableArrayList();
        for (Staff staff : staffObservableList) {
            if (staff.getSort().equals(filter)) {
                tempList.add(staff);
            }
        }
        return tempList;
    }

    /**
     * Socket which receives new request from central point
     */
    private void receiveSocket() {
        try {
            ITeamRequest TR;
            do {
                TR = null;
                TR = (ITeamRequest) in.readObject();
            }
            while (TR == null);
            RQ.addRequests((TeamRequest) TR);
            RequestObservableList.clear();
            RequestObservableList.addAll(RQ.GetRequests());

            System.out.println("done");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a team
     *
     * @param team the team to create
     */
    public void createTeam(Team team) {
        daoManager.getDao(DbTables.TEAM).insert(team);
    }

    /**
     * Assigns a mission to a team
     *
     * @param team the team where the mission belongs to
     * @param missionnr the missonid of the mission to add to the team
     */
    public void addMissionToTeam(Team team, int missionnr) {
        daoManager.getDao(DbTables.TEAM).update(team, missionnr);
        for (Staff staff : team.getTeamMembers()) {
            daoManager.getDao(DbTables.PERSONEEL).update(staff, 1);
        }
    }
}
