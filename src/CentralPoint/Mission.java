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
    private double locationX;
    private double locationY;
    private HashSet<Team> teamsAssigned;

    public int getID() {
        return ID;
    }


    public String getName() {
        return name;
    }



    public String getDescription() {
        return description;
    }



    public Date getStartTime() {
        return startTime;
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

    public double getLocationX() {
        return locationX;
    }



    public double getLocationY() {
        return locationY;
    }



    public HashSet<Team> getTeamsAssigned() {
        return teamsAssigned;
    }

    public void setTeamsAssigned(HashSet<Team> teamsAssigned) {
        this.teamsAssigned = teamsAssigned;
    }

    public Mission(int ID, String name, String description, Date startTime, Date lastUpdate, Date endTime, double locationX, double locationY) {
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
        return this.name;
    }
}
