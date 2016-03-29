package CentralPoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Kees on 16/03/2016.
 */
public class Team implements Serializable {
    private String name;
    private ArrayList<Staff> teamMembers;

    public Team(String name, ArrayList<Staff> teamMembers) {
        this.name = name;
        this.teamMembers = teamMembers;
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


    @Override
    public String toString() {
        return "Team : " + this.name;
    }

}
