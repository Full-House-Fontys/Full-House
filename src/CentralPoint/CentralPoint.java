package CentralPoint;

import Database.DaoManager;
import Database.DbTables;
import HulpDienst.TeamRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kaj Suiker on 20-3-2016.
 */
public class CentralPoint {
    ServerSocket serverSocket;
    Socket connection = null;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    private DaoManager daoManager;
    private ObservableList<Staff> staffObservableList;
    private ObservableList<Material> materialObservableList;
    private ObservableList<Mission> missionObservableList;
    private ObservableList<Team> teamObservableList;
    private ObservableList<Team> availableTeamObservableList;
    private ObservableList<Material> availableMaterialObservableList;
    private ObservableList<Notification> notificationObservableList;

    /**
     * Constructor for central point
     * Create server starts up the connection with the server.
     */
    public CentralPoint() throws Exception {
        daoManager = DaoManager.INSTANCE;
        staffObservableList = FXCollections.observableArrayList(new ArrayList<Staff>());
        materialObservableList = FXCollections.observableArrayList(new ArrayList<Material>());
        availableMaterialObservableList = FXCollections.observableArrayList(new ArrayList<Material>());
        missionObservableList = FXCollections.observableArrayList(new ArrayList<Mission>());
        teamObservableList = FXCollections.observableArrayList(new ArrayList<Team>());
        availableTeamObservableList = FXCollections.observableArrayList();
        createserver();
        notificationObservableList = FXCollections.observableArrayList(new ArrayList<Notification>());

    }

    /**
     * Renews the list with the updated data from the database
     * @param table object of table to renewLists
     */

    //todo default is niet ingevuld
    private void renewLists(DbTables table) {
        switch (table) {
            case PERSONEEL:
                staffObservableList.clear();
                staffObservableList = daoManager.getDao(table).getAllRecord();
                break;
            case MATERIAAL:
                materialObservableList.clear();
                materialObservableList = daoManager.getDao(table).getAllRecord();
                availableMaterialObservableList = daoManager.getDao(table).getSpecificList(0);
                break;
            case MISSIE:
                missionObservableList.clear();
                missionObservableList = daoManager.getDao(table).getAllRecord();
                break;
            case TEAM:
                teamObservableList.clear();
                teamObservableList = daoManager.getDao(table).getAllRecord();
                //Below: adds teams and materials to the mission, so that they are linked together
                addTeamsToMission();
                addMaterialsToMission(0);
                break;
            case MELDING:
                notificationObservableList.clear();
                notificationObservableList = daoManager.getDao(table).getAllRecord();
                break;
            default:
                break;
        }
    }

    /**
     * Returns the staff who are on location
     * @return is of staff on location
     */
    public ObservableList<Staff> getStaffOnLocation() {
        return daoManager.getDao(DbTables.PERSONEEL).getSpecificList(0);
    }


    /**
     * Returns the objectoutputstream of the helpservice
     * @return objectOutputstream
     */
    public ObjectOutputStream getOutput() {
        return objectOutputStream;
    }

    /**
     * Returns the materialLists as observableList
     * @return unmodifiableObservableList of materials
     */
    public ObservableList<Material> getMaterials() {
        renewLists(DbTables.MATERIAAL);
        return FXCollections.unmodifiableObservableList(materialObservableList);
    }

    /**
     * Returns the available materiallist as observableList
     *
     * @return unmodifiableObservableList of available materials
     */
    public ObservableList<Material> getAvailableMaterials() {
        renewLists(DbTables.MATERIAAL);
        return FXCollections.unmodifiableObservableList(availableMaterialObservableList);
    }

    /**
     * Returns the material for the given id
     * @param id; the id where this method should search for in materials
     * @return the material with the given id
     */
    public Material getMaterialById(int id) {
        Material materialById = null;
        for (Material material : materialObservableList) {
            if (material.getId() == id) {
                materialById = material;
            }
        }
        return materialById;
    }

    /**
     * Get all the missions from the database
     * @return unmodifiableObservableList of all missions
     */
    public ObservableList<Mission> getAllMissions() {
        renewLists(DbTables.MISSIE);
        return FXCollections.unmodifiableObservableList(missionObservableList);
    }

    /**
     * creates a mission from given parameters
     *
     * @param name        as String
     * @param description as Stirng
     * @param startTime   as Date
     * @param locationX   as double
     * @param locationY   as double
     */
    public void createMission(String name, String description, Date startTime, double locationX, double locationY, int estimatedTime) {
        Mission mission = new Mission(0, name, description, startTime, null, null, locationX, locationY, estimatedTime);
        daoManager.getDao(DbTables.MISSIE).insert(mission);
        renewLists(DbTables.MISSIE);
    }

    /**
     * Returns all the teams
     *
     * @return teams as unmodifiableObservableList
     */
    public ObservableList<Team> getAllTeams() {
        renewLists(DbTables.TEAM);
        return FXCollections.unmodifiableObservableList(teamObservableList);
    }

    /**
     * Add teams to a mission
     */
    public void addTeamsToMission() {
        ArrayList<Team> allTeamsAssignedToMission = new ArrayList<>();
        for (Mission mission : missionObservableList) {
            for (Team team : teamObservableList) {
                if (team.getMissionID().contains(mission.getID())) {
                    allTeamsAssignedToMission.add(team);
                }
            }
            ArrayList<Team> copyAllTeamsAssignedToMission = new ArrayList<>(allTeamsAssignedToMission);
            mission.setTeamsAssigned(copyAllTeamsAssignedToMission);
            allTeamsAssignedToMission.clear();
        }
    }

    /**
     * Adds materials to a mission
     *
     * @param id as int, the id of a mission
     * @return the Mission which is opened
     */
    public Mission addMaterialsToMission(int id) {
        Mission mission1 = null;
        renewLists(DbTables.MATERIAAL);
        ArrayList<Material> allMaterialsAssignedToMission = new ArrayList();
        for (Mission mission : missionObservableList) {
            if (mission.getID() == id) {
                for (Material material : materialObservableList) {
                    for (int i : material.getMissionIds()) {
                        if (mission.getID() == i) {
                            allMaterialsAssignedToMission.add(material);
                        }
                    }
                }
                mission.setMaterialsAssigned(allMaterialsAssignedToMission);//copyAllMaterialsAssignedToMission);
                mission1 = mission;
            }
        }
        return mission1;
    }

    /**
     * Adds one material to a mission
     *
     * @param selectedMaterial as Material, the material that should be added to the mission
     * @param activeMission    as Mission, the mission where the material belongs to
     * @return the Mission which is opened
     */
    public Mission addMaterialToMission(Material selectedMaterial, Mission activeMission) {
        daoManager.getDao(DbTables.MISSIE).insertTwoInts(selectedMaterial.getId(), activeMission.getID());
        Mission updateMission = addMaterialsToMission(activeMission.getID());
        renewLists(DbTables.MISSIE);
        renewLists(DbTables.MATERIAAL);
        return updateMission;
    }

    /**
     * Gets a specific team
     *
     * @return a list of the specific teams
     */
    public ObservableList<Team> getSpecificTeam() {
        availableTeamObservableList = daoManager.getDao(DbTables.TEAM).getSpecificList(0);
        return availableTeamObservableList;
    }


    /**
     * Check if user is in database
     *
     *
     * @param userName username of user
     * @param password password of user
     * @return returns mission id if in database and on mission else -1
     */
    public int checkExistingUser(String userName, String password) {
        Staff incomingLogin = new Staff();
        incomingLogin.setUserName(userName);
        incomingLogin.setPassword(password);
        return ((Staff) daoManager.getDao(DbTables.PERSONEEL).getObject(incomingLogin, 0)).getId();
    }

    /**
     * Makes a rapport for any mission.
     * All related data in the database will be gathered converted in a format.
     * An text file with all data will be created.
     * @param mission : this mission will turn in to a rapport
     */
    public void createRapport(Mission mission) throws IOException {
        //Getting all data related to the given mission
        ObservableList<Material> materials = daoManager.getDao(DbTables.MATERIAAL).getSpecificList(mission.getID());
        ObservableList<Message> messages = daoManager.getDao(DbTables.BERICHT).getSpecificList(mission.getID());
        ObservableList<Notification> notifications = daoManager.getDao(DbTables.MELDING).getSpecificList(mission.getID());
        ObservableList<Staff> staff = daoManager.getDao(DbTables.PERSONEEL).getSpecificList(mission.getID());
        ObservableList<Team> teams = daoManager.getDao(DbTables.TEAM).getSpecificList(mission.getID());

        //Name of the file
        String fileName = "Mission-" + mission.getID() + ".txt";

        //Textfile setup
        List<String> lines = new ArrayList<>();
        Path file = Paths.get(fileName);

        //Adding lines
        lines.add("Missie:\n\t" + mission.getName() + "\n");
        lines.add("Beschrijving:\n\t" + mission.getDescription() + "\n");

            lines.add("Personeel:");
            try {
                for (Staff s : staff){
                    lines.add("\t" + s.toString());
                }
            }
            catch (NullPointerException e) { lines.add(""); }

            lines.add("\nTeam:");
            try {
                for (Team t : teams){
                    lines.add("\t" + t.toString());
                }
            }
            catch (NullPointerException e) { lines.add(""); }

            lines.add("\nMateriaal:");
            try {
                for (Material m : materials){
                    lines.add("\t" + m.toString());
                }
            }
            catch (NullPointerException e) { lines.add(""); }

            lines.add("\nBerichten:");
            try {
                for (Message m : messages){
                    lines.add("\t" + m.toString());
                }
            }
            catch (NullPointerException e) { lines.add(""); }

            lines.add("\nNotificaties:");
            try {
                for (Notification n : notifications){
                    lines.add("\t" + n.toString());
                }
            }
            catch (NullPointerException e) { lines.add(""); }

        //Writes the rapport
        Files.write(file, lines, Charset.forName("UTF-8"));
        //Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    }


    /**
     * Returns all messages from the database
     *
     * @return messages
     */
    public String getLastMessages() {
        StringBuilder lastMessages = new StringBuilder();
        for (Object message : daoManager.getDao(DbTables.BERICHT).getAllRecord()) {
            Message mes = (Message) message;
            lastMessages.append(mes.getTitel() + " " + mes.getMessage() + (mes.getMissionId() != 0 ? 1 : 0) + "///");
        }
        return lastMessages.toString();
    }


    /**
     * Returns all notification from the database
     * @return observable notificationlist
     */
    public ObservableList<Notification> getAllNotifications() {
        renewLists(DbTables.MELDING);
        return FXCollections.unmodifiableObservableList(notificationObservableList);
    }

    /**
     * Creates a connection between Centralpoint and Helpservice
     * This is done by connecting sockets
     */
    public void createserver() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(2006);
                    connection = serverSocket.accept();
                    objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
                    objectOutputStream.flush();
                    objectInputStream = new ObjectInputStream(connection.getInputStream());
                } catch (IOException e) {

                }
            }
        });
        thread.start();
    }

    /**
     * gets mission with mission id
     * @param id id of mission to get
     * @return the mission
     */
    public Mission getMissionFromId(int id) {
        Mission mission1 = null;
        renewLists(DbTables.MISSIE);
        for(Mission mission : missionObservableList) {
            if (mission.getID() == id) {
                mission1 = mission;
            }
        }
        return mission1;
    }

    /**
     * send request for support service
     * @param teamRequest object to send
     */
    public void sendSupportService(TeamRequest teamRequest) {
        try {
            getOutput().writeObject(teamRequest);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * inserts send messages into database
     * creates message from payload and sends it to the daomanager
     *
     * @param payload the full messages
     * @return true/false for success
     */
    public boolean insertMessage(String payload) {
        return daoManager.getDao(DbTables.BERICHT).insert(new Message("", payload.substring(0, payload.length() - 1), Integer.parseInt(payload.substring(payload.length() - 1))));
    }

    public boolean insertMissionPlan(MissionPlan missionPlan) {
        return daoManager.getDao(DbTables.MISSIE_STAPPENPLAN).insert(new MissionPlan(missionPlan.getMissionID(), missionPlan.getAllSteps()));
    }
    /**
     * sets database mission as done
     * @param id mission id to set to done
     * @return true/false for success
     */
    public boolean missionDone(int id) {
        return daoManager.getDao(DbTables.MISSIE).update(new Mission(), id);
    }

    /**
     * get steps from database, is filled in as mission name
     *
     * @param missionId id of mission
     * @return string of steps, else empty string
     */
    public String getSteps(int missionId) {
        return ((Mission) daoManager.getDao(DbTables.MISSIE).getObject(new Mission(), missionId)).getName();
    }
}

