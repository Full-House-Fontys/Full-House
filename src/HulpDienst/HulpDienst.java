package HulpDienst;

import CentralPoint.Staff;
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
public class HulpDienst {
    private Socket requestSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private DaoManager daoManager;
    private List<Staff> staffList;
    private ObservableList<Staff> staffObservableList;
    private List<TeamRequest> requests;
    private ObservableList<TeamRequest> RequestObservableList;

    public HulpDienst() {
        daoManager = DaoManager.INSTANCE;
        staffList = new ArrayList<>();
        requests = new ArrayList<>();
        staffObservableList = FXCollections.observableArrayList(staffList);
        RequestObservableList = FXCollections.observableArrayList(requests);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                receiveSocket();
            }
        }, 0, 1000);
    }

    public ObservableList<Staff> renewStaffList() {
        staffObservableList.clear();
        staffObservableList = daoManager.getDao(DbTables.PERSONEEL).getSpecificList(1);
        return staffObservableList;
    }

    public ObservableList<TeamRequest> getTeamRequests() {
        return RequestObservableList;
    }

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

    private void receiveSocket() {
        try {
            requestSocket = new Socket("localhost", 2004);
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
            IRequests TR = null;
            do {
                TR = (IRequests) in.readObject();
            }
            while (TR == null);
            RequestObservableList.clear();
            RequestObservableList.addAll(TR.GetRequests());
            System.out.println("done");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
