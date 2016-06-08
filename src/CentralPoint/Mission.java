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
    private int estimatedTime;
    /**
     * Constructor for the mission with all the details
     *
     * @param ID
     * @param name
     * @param description
     * @param startTime
     * @param lastUpdate
     * @param endTime
     * @param locationX
     * @param locationY
     * @param estimatedTime
     */
    public Mission(int ID, String name, String description, Date startTime, Date lastUpdate, Date endTime, double locationX, double locationY, int estimatedTime) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.lastUpdate = lastUpdate;
        this.endTime = endTime;
        this.locationX = locationX;
        this.locationY = locationY;
        this.estimatedTime = estimatedTime;
        this.teamsAssigned = new ArrayList<>();
        this.materialsAssigned = new ArrayList<>();
    }

    /**
     * returns the id of the mission
     *
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /**
     * returns the name of the mission
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * returns the description of the mission
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * returns the startTime(when the mission is executed) of the mission
     * @return StartTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * return the last time the mission was updated
     * @return LastUpdate
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }


    /**
     * Sets the time of the last update from the mission
     * @param lastUpdate
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    /**
     * The endtime of the mission
     * @return Endtime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the mission
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Return the X-Coördinates of the mission
     * @return LocationX
     */
    public double getLocationX() {
        return locationX;
    }

    /**
     * Return the Y-Coördinates of the mission
     * @return LocationY
     */
    public double getLocationY() {
        return locationY;
    }

    /**
     * The teams who are assigned to the mission
     * @return list of all teams
     */
    public ArrayList<Team> getTeamsAssigned() {
        return teamsAssigned;
    }

    /**
     * Sets the teams who are assigned for the mission
     * @param teamsAssigned
     */
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

    /**
     * get the estimated time of this mission.
     *
     * @return
     */
    public int getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * set the estimated time of this mission
     *
     * @param estimatedTime
     */
    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    /**
     * Adds team to a mission
     * @param teamToAdd
     * @return true when team is added to the mission
     */
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

    /**
     * Overrides the toString such that it now return
     * The id with the name of the mission
     * @return Id and Name
     */
    @Override
    public String toString() {
        return this.ID + ": " + this.name;
    }


    /**
     * The teams who are assigned to the mission
     * @param id
     * @return teams
     */
    public ObservableList getTeamsAssignedToMission(int id) {
        DaoManager.INSTANCE.open();
        DaoGeneric getAssignedMembers = DaoManager.INSTANCE.getDao(DbTables.PERSONEEL);
        return getAssignedMembers.getSpecificList(id);
    }
}
