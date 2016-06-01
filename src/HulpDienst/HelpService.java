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
            requestSocket = new Socket("localhost", 2004);
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
     * refresh the staff that is currently available
     *
     * @return
     */
    public ObservableList<Staff> renewStaffList() {
        staffObservableList.clear();
        staffObservableList = daoManager.getDao(DbTables.PERSONEEL).getSpecificList(1);
        return staffObservableList;
    }

    /**
     * refresh the teams that are available
     *
     * @return
     */
    public ObservableList<Team> renewteams() {
        OBavailableteams.clear();
        OBavailableteams = daoManager.getDao(DbTables.TEAM).getSpecificList(1);
        return OBavailableteams;
    }

    /**
     * gets all teams
     *
     * @return
     */
    public ObservableList<Team> getAllTeams() {
        OBallteams.clear();
        OBallteams = daoManager.getDao(DbTables.TEAM).getAllRecord();
        return OBallteams;
    }

    /**
     * gets all teamrequests
     * @return
     */
    public ObservableList<TeamRequest> getTeamRequests() {
        return RequestObservableList;
    }

    /**
     * filters the stafflist based on the filter you selected
     * @param filter
     * @return
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
     * socket which receives new requests from central point
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
     * create a team
     *
     * @param team
     */
    public void createTeam(Team team) {
        daoManager.getDao(DbTables.TEAM).insert(team);
    }

    /**
     * assigns a mission to a team
     *
     * @param team
     * @param missionnr
     */
    public void addMissionToTeam(Team team, int missionnr) {
        daoManager.getDao(DbTables.TEAM).update(team, missionnr);
        for (Staff staff : team.getTeamMembers()) {
            daoManager.getDao(DbTables.PERSONEEL).update(staff, 1);
        }
    }
}
