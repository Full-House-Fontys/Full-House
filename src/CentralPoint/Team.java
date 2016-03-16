package CentralPoint;

import DBElements.Staff;

import java.util.ArrayList;

/**
 * Created by Kees on 16/03/2016.
 */
public class Team {

    private String teamName;
    private ArrayList<Staff> teamMembers = new ArrayList<>();

    public String getTeamName() {
        return teamName;
    }

    public Team(String teamNaam, ArrayList<Staff> teamMembers){
        this.teamName = teamNaam;
        this.teamMembers = teamMembers;
    }

    /**
     * Adds a team member to the team
     * @param s : Team member that will be added to the team.
     * @return true if the team member is correctly added, false if it failed.
     */
    public boolean addTeamMember(Staff  s){
        return false;
    }

    /**
     * Removes a team member from the team
     * @param s : Team member that will be removed from the team.
     * @return true if the team member is correctly removed, false if it failed.
     */
    public boolean removeTeamMember(Staff  s){
        return false;
    }

}
