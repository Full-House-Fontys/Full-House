package CentralPoint;

import java.util.ArrayList;

/**
 * Created by Kees on 16/03/2016.
 */
public class Mission {

    private String name;
    private String description;
    private ArrayList<Team> teams = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Mission(String name, String description, ArrayList<Team> teams){
        this.name = name;
        this.description = description;
        this.teams = teams;
    }

    /**
     * Adds a team to the mission
     * @param t : Team that will be added to the mission.
     * @return true if the team is correctly added, false if it failed.
     */
    public boolean addTeam(Team t){
        return false;
    }

    private void removeEverything() {
    }

    /**
     * Removes a team from the mission
     * @param t : Team that will be removed from the mission.
     * @return true if the team is correctly removed, false if it failed.
     */
    public boolean removeTeam(Team t){
        return false;
    }

}
