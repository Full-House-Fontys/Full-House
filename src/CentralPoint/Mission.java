package CentralPoint;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

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
    private int locationX;
    private int locationY;
    private HashSet<Team> teamsAssigned;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public HashSet<Team> getTeamsAssigned() {
        return teamsAssigned;
    }

    public void setTeamsAssigned(HashSet<Team> teamsAssigned) {
        this.teamsAssigned = teamsAssigned;
    }

    public Mission(int ID, String name, String description, Date startTime, Date lastUpdate, Date endTime, int locationX, int locationY, HashSet<Team> teamsAssigned) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.lastUpdate = lastUpdate;
        this.endTime = endTime;
        this.locationX = locationX;
        this.locationY = locationY;
        this.teamsAssigned = null;
    }

    public void addTeamToJob(Team team){
        if(!teamsAssigned.contains(team)){
            teamsAssigned.add(team);
        }
    }

    @Override
    public String toString() {
        return "Mission : " + this.name;
    }
}
