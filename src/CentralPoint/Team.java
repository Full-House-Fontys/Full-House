package CentralPoint;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kees on 16/03/2016.
 */
public class Team implements Serializable {
    private String name;
    private ArrayList<Staff> teamMembers;
    private int id;
    private ArrayList<Integer> missionID;

    //TODO
    public Team(int iD, String name, ArrayList<Staff> teamMembers, ArrayList<Integer> missionID) {
        this.name = name;
        this.teamMembers = teamMembers;
        this.id = iD;
        this.missionID = missionID;

    }

    //TODO
    public ArrayList<Integer> getMissionID() {
        return missionID;
    }

    //TODO
    public void setMissionID(ArrayList<Integer> missionID) {
        this.missionID = missionID;
    }

    //TODO
    public int getId() {
        return id;
    }

    //TODO
    public void setId(int id) {
        this.id = id;
    }

    //TODO
    public String getName() {
        return name;
    }

    //TODO
    public void setName(String name) {
        this.name = name;
    }

    //TODO
    public ArrayList<Staff> getTeamMembers() {
        return teamMembers;
    }

    //TODO
    public void setTeamMembers(ArrayList<Staff> teamMembers) {
        this.teamMembers = teamMembers;
    }

    //TODO
    public void addMember(Staff member) {
        teamMembers.add(member);
    }

    //TODO
    public void addMissie(int toAddMissionID) {
        if (!missionID.contains(toAddMissionID)) {
            missionID.add(toAddMissionID);
        }
    }

    //TODO
    @Override
    public String toString() {
        return "Team : " + this.name;
    }
}
