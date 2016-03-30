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

    public Team(int iD, String name, ArrayList<Staff> teamMembers, ArrayList<Integer> missionID) {
        this.name = name;
        this.teamMembers = teamMembers;
        this.id = iD;
        this.missionID = missionID;

    }

    public ArrayList<Integer> getMissionID() {
        return missionID;
    }

    public void setMissionID(ArrayList<Integer> missionID) {
        this.missionID = missionID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Staff> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(ArrayList<Staff> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public void addMember(Staff member) {
        teamMembers.add(member);
    }
    @Override
    public String toString() {
        return "Team : " + this.name;
    }

    public void addMissie(int toAddMissionID) {
        if (!missionID.contains(toAddMissionID)) {
            missionID.add(toAddMissionID);
        }
    }
}
