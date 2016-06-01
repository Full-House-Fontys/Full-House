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


    /**
     * Constructor for team
     *
     * @param iD
     * @param name
     * @param teamMembers
     * @param missionID
     */
    public Team(int iD, String name, ArrayList<Staff> teamMembers, ArrayList<Integer> missionID) {
        this.name = name;
        this.teamMembers = teamMembers;
        this.id = iD;
        this.missionID = missionID;

    }

    /**
     * The mission which is bound to the team
     *
     * @return missionID
     */
    public ArrayList<Integer> getMissionID() {
        return missionID;
    }

    /**
     * Sets the mission for the team
     * @param missionID
     */
    public void setMissionID(ArrayList<Integer> missionID) {
        this.missionID = missionID;
    }

    /**
     * The id of the team
     * @return teamID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the team
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The name of the team
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the team
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns all members of the team
     * @return team
     */
    public ArrayList<Staff> getTeamMembers() {
        return teamMembers;
    }

    /**
     * Sets the members of the team
     * @param teamMembers
     */
    public void setTeamMembers(ArrayList<Staff> teamMembers) {
        this.teamMembers = teamMembers;
    }

    /**
     * Add members to the team
     * @param member
     */
    public void addMember(Staff member) {
        teamMembers.add(member);
    }

    /**
     * Add mission to the team
     * @param toAddMissionID
     */
    public void addMissie(int toAddMissionID) {
        if (!missionID.contains(toAddMissionID)) {
            missionID.add(toAddMissionID);
        }
    }

    /**
     * override the toString so that now it returns
     * the teamname
     * @return the teamname
     */
    @Override
    public String toString() {
        return "Team : " + this.name;
    }
}
