package CentralPoint;

import java.util.HashSet;

/**
 * Created by Kees on 16/03/2016.
 */
public class Team {
    private String name;
    private HashSet<Staff> teamMembers;

    public Team(String name, HashSet<Staff> teamMembers) {
        this.name = name;
        this.teamMembers = teamMembers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<Staff> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(HashSet<Staff> teamMembers) {
        this.teamMembers = teamMembers;
    }

    @Override
    public String toString() {
        return "Team : " + this.name;
    }
}
