package HulpDienst;

import CentralPoint.Mission;

import java.io.Serializable;

/**
 * Created by Mark on 6-4-2016.
 */
public class TeamRequest implements ITeamRequest, Serializable {

    private String name;
    private String description;
    private int Medic;
    private int Police;
    private int fireman;
    private int militarypolice;
    private Mission mission;

    public TeamRequest(String name, String description, int medic, int Police, int fireman, int militarypolice, Mission mission) {
        this.name = name;
        this.description = description;
        this.Medic = medic;
        this.Police = Police;
        this.fireman = fireman;
        this.militarypolice = militarypolice;
        this.mission = mission;
    }

    /**
     * Returns name of Request
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of Request
     * @param name as String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the discription of the request
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the discription of the request
     * @param description as String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * gets the amount of EHBO needed
     * @return the amount
     */
    public int getMedic() {
        return Medic;
    }

    /**
     * sets the amount of needed EHBO
     * @param Medic as int
     */
    public void setMedic(int Medic) {
        this.Medic = Medic;
    }

    /**
     * gets the amount of police needed
     * @return amount
     */
    public int getPolice() {
        return Police;
    }

    /**
     * sets the amount of needed police
     * @param police as int
     */
    public void setPolice(int police) {
        Police = Police;
    }

    /**
     * gets the amount of fireman needed
     * @return amount
     */
    public int getFireman() {
        return fireman;
    }

    /**
     * sets the amount of needed fireman
     * @param fireman as int
     */
    public void setFireman(int fireman) {
        fireman = fireman;
    }

    /**
     * gets the amount of marachause needed
     * @return amount
     */
    public int getMilitarypolice() {
        return militarypolice;
    }

    /**
     * sets the amount of needed marachause
     * @param militarypolice as int
     */
    public void setMilitarypolice(int militarypolice) {
        this.militarypolice = militarypolice;
    }

    /**
     * gets the assigned mission
     *
     * @return the mission
     */
    public Mission getMission() {
        return mission;
    }

    /**
     * sets to which mission the request is assigned to
     *
     * @param mission as Mission
     */
    public void setMission(Mission mission) {
        this.mission = mission;
    }

    /**
     * returns the name and discription of the request as text
     *
     * @return the generated String
     */
    @Override
    public String toString() {
        return name + ": " + description;}
}
