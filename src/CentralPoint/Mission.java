package CentralPoint;

import java.util.HashSet;

/**
 * Created by Kees on 16/03/2016.
 */
public class Mission {
    private int id;
    private String name;
    private String description;
    private HashSet<Team> teamsAssigned;

    public Mission(int id, String name, String description, HashSet<Team> teamsAssigned) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teamsAssigned = teamsAssigned;
    }

    public void addTeamToJob(Team team){
        if(!teamsAssigned.contains(team)){
            teamsAssigned.add(team);
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashSet<Team> getTeamsAssigned() {
        return teamsAssigned;
    }

    public void setTeamsAssigned(HashSet<Team> teamsAssigned) {
        this.teamsAssigned = teamsAssigned;
    }

}
