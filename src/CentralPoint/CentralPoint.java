package CentralPoint;

import Database.DaoManager;
import Database.DbTables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kaj Suiker on 20-3-2016.
 */
public class CentralPoint {
    DaoManager daoManager;
    List<Staff> staffList;
    List<Material> materialList;
    List<Material> availableMatList;
    List<Mission> missionsList;
    List<Team> teamList;

    ObservableList<Staff> staffObservableList;
    ObservableList<Material> materialObservableList;
    ObservableList<Mission> missionObservableList;
    ObservableList<Team> teamObservableList;
    ObservableList<Team> availableTeamObservableList;
    ObservableList<Material> availableMaterialObservableList;
    /**
     * constructor for central point
     */
    public CentralPoint(){
        daoManager = DaoManager.INSTANCE;
        missionsList = new ArrayList<>();
        staffList = new ArrayList<>();
        materialList = new ArrayList();
        availableMatList = new ArrayList();
        teamList = new ArrayList<>();
        staffObservableList = FXCollections.observableArrayList(staffList);
        materialObservableList = FXCollections.observableArrayList(materialList);
        availableMaterialObservableList = FXCollections.observableArrayList(availableMatList);
        missionObservableList = FXCollections.observableArrayList(missionsList);
        teamObservableList = FXCollections.observableArrayList(teamList);
        availableTeamObservableList = FXCollections.observableArrayList();
    }

    /**
     * @param table object of table to renewLists
     */
    private void renewLists(DbTables table){
        switch (table){
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
                addTeamsToMission();
                addMaterialsToMission();
                break;
            default:
                break;
        }
    }

    /**
     * @return is of staff on location
     */
    public ObservableList<Staff> getStaffOnLocation(){
        //ObservableList<Staff> staffOnLocation = FXCollections.observableArrayList();

        //for (Staff staff : staffObservableList){
            //if(staff.isOnLocation())
                //staffOnLocation.add(staff);

        return daoManager.getDao(DbTables.PERSONEEL).getSpecificList(0);  //FXCollections.unmodifiableObservableList(staffOnLocation);
    }

    /**
     * @param team list of staff in team
     */
    public void setStaffOnLocation(List<String> team){
        for(String staffname : team){
            Staff toUpdate = new Staff();
            toUpdate.setOnLocation(true);
            daoManager.getDao(DbTables.PERSONEEL).update(toUpdate, staffname);
        }
    }

    /**
     * returns the materialLists as observableList
     * @return unmodifiableObservableList of materials
     */
    public ObservableList<Material> getMaterials(){
        renewLists(DbTables.MATERIAAL);
        return FXCollections.unmodifiableObservableList(materialObservableList);
    }

    public ObservableList<Material> getAvailableMaterials() {
        renewLists(DbTables.MATERIAAL);
        return FXCollections.unmodifiableObservableList(availableMaterialObservableList);
    }

    /**
     * inserts a new material in the database
     * @param name
     * @param sort
     * @param locX
     * @param locY
     * @param onLoc
     * @throws IllegalArgumentException
     */
    public void insertMaterial(String name, String sort, double locX, double locY, boolean onLoc) throws IllegalArgumentException {
        if (name == null || name.trim().length() <= 0) {
            throw new IllegalArgumentException();
        } else if (sort == null || sort.trim().length() <= 0) {
            throw new IllegalArgumentException();

        } else {
            Material m = new Material(name, sort, new Point2D.Double(locX, locY), onLoc);
            daoManager.getDao(DbTables.MATERIAAL).insert(m);
            renewLists(DbTables.MATERIAAL);
        }
    }

    /**
     * returns the material for the given id
     * @param id
     * @return
     */
    public Material getMaterialById(int id) {
        for (Material material : materialObservableList) {
            if (material.getId() == id) {
                return material;
            }
        }
        return null;
    }

    /**
     * update the material for given id
     * @param matId
     * @param name
     * @param sort
     * @param location
     * @param onLoc
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    public void updateMaterial(int matId, String name, String sort, Point2D location, boolean onLoc) throws IllegalArgumentException, IllegalStateException {
        Material m = getMaterialById(matId);
        boolean changed = false;
        if (!(m.getName().equals(name))) {
            if (name != null && name.trim().length() > 0) {
                m.setName(name);
                changed = true;
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        else{
            throw new IllegalStateException();
        }
        if (!(m.getSort().equals(sort))) {
            if (sort != null && sort.trim().length() > 0) {
                m.setSort(sort);
                changed = true;
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        else{
            throw new IllegalStateException();
        }
        if ((!(m.getLocation().equals(location))) && location != null) {
            m.setLocation(location);
            changed = true;
        } else if (location == null){
            throw new IllegalArgumentException();
        }
        else{
            throw new IllegalStateException();
        }
        if (m.isOnLocation() != onLoc) {
            m.setOnLocation(onLoc);
            changed = true;
        }
        else{
            throw new IllegalStateException();
        }
        if (changed) {
            daoManager.getDao(DbTables.MATERIAAL).update(m, matId);
            renewLists(DbTables.MATERIAAL);
        }
    }

    /**
     * delete given material
     * @param m
     */
    public void deleteMaterial(Material m) {
        daoManager.getDao(DbTables.MATERIAAL).delete(m.getId());
        renewLists(DbTables.MATERIAAL);
    }

    /**
     * get all the missions from the database
     *
     * @return unmodifiableObservableList of all missions
     */
    public ObservableList<Mission> getAllMissions() {
        renewLists(DbTables.MISSIE);
        return FXCollections.unmodifiableObservableList(missionObservableList);
    }

    public void createMission(String name, String description, Date startTime, double locationX, double locationY) {
        Mission mission = new Mission(0, name, description, startTime, null, null, locationX, locationY);
        daoManager.getDao(DbTables.MISSIE).insert(mission);
        renewLists(DbTables.MISSIE);
        //availableTeamObservableList = daoManager.getDao(DbTables.TEAM).getSpecificList(0);
        //addTeamsToMission();
    }

    public ObservableList<Team> getAllTeams() {
        renewLists(DbTables.TEAM);
        return FXCollections.unmodifiableObservableList(teamObservableList);
    }

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

    public void addMaterialsToMission() {
        renewLists(DbTables.MATERIAAL);
        ArrayList<Material> allMaterialsAssignedToMission = new ArrayList();
        for (Mission mission : missionObservableList) {
            for (Material material : materialObservableList) {
                for (int i : material.getMissionIds()) {
                    if (mission.getID() == i) {
                        allMaterialsAssignedToMission.add(material);
                    }
                }
            }
            ArrayList<Material> copyAllMaterialsAssignedToMission = new ArrayList(allMaterialsAssignedToMission);
            mission.setMaterialsAssigned(copyAllMaterialsAssignedToMission);
            allMaterialsAssignedToMission.clear();
        }
    }

    public ObservableList<Team> getSpecificTeam() {
        availableTeamObservableList = daoManager.getDao(DbTables.TEAM).getSpecificList(0);
        return availableTeamObservableList;
    }
}
