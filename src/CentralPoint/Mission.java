package CentralPoint;

import Database.DaoGeneric;
import Database.DaoManager;
import Database.DbTables;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kees on 16/03/2016.
 */
public class Mission implements Serializable{
    private int ID;
    private String name;
    private String description;
    private Date startTime;
    private Date lastUpdate;
    private Date endTime;
    private double locationX;
    private double locationY;
    private ArrayList<Team> teamsAssigned;
    private ArrayList<Material> materialsAssigned;

    //TODO
    public Mission(int ID, String name, String description, Date startTime, Date lastUpdate, Date endTime, double locationX, double locationY) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.lastUpdate = lastUpdate;
        this.endTime = endTime;
        this.locationX = locationX;
        this.locationY = locationY;
        this.teamsAssigned = new ArrayList<>();
        this.materialsAssigned = new ArrayList<>();
    }

    //TODO
    public int getID() {
        return ID;
    }

    //TODO
    public String getName() {
        return name;
    }

    //TODO
    public String getDescription() {
        return description;
    }

    //TODO
    public Date getStartTime() {
        return startTime;
    }

    //TODO
    public Date getLastUpdate() {
        return lastUpdate;
    }

    //TODO
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    //TODO
    public Date getEndTime() {
        return endTime;
    }

    //TODO
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    //TODO
    public double getLocationX() {
        return locationX;
    }

    //TODO
    public double getLocationY() {
        return locationY;
    }

    //TODO
    public ArrayList<Team> getTeamsAssigned() {
        return teamsAssigned;
    }

    //TODO
    public void setTeamsAssigned(ArrayList<Team> teamsAssigned) {
        this.teamsAssigned = teamsAssigned;
    }

    /**
     * Gets the material what is assigned to this mission
     *
     * @return list of assigned materials
     */
    public ArrayList<Material> getMaterialsAssigned() {
        return materialsAssigned;
    }

    /**
     * Sets the materials assigned to this mission
     *
     * @param materialsAssigned as list of materials
     */
    public void setMaterialsAssigned(ArrayList<Material> materialsAssigned) {
        this.materialsAssigned = materialsAssigned;
    }

    //TODO
    public boolean addTeamToJob(Team teamToAdd) {
        boolean addedToTeam = false;
        int counter = 0;
        for(Team team : teamsAssigned){
            if(teamToAdd.getName().equals(team.getName())){
                counter++;
            }
        }
        if(counter==0){
            teamsAssigned.add(teamToAdd);
            addedToTeam = true;
        }
        return addedToTeam;
    }

    //TODO
    @Override
    public String toString() {
        return this.ID + ": " + this.name;
    }

    //TODO
    public ObservableList getTeamsAssignedToMission(int id) {
        DaoManager.INSTANCE.open();
        DaoGeneric getAssignedMembers = DaoManager.INSTANCE.getDao(DbTables.PERSONEEL);
        return getAssignedMembers.getSpecificList(id);
    }
}
