package HulpDienst;

import CentralPoint.Mission;

import java.io.Serializable;

/**
 * Created by Mark on 6-4-2016.
 */
public class TeamRequest implements ITeamRequest, Serializable {

    private String name;
    private String description;
    private int EHBO;
    private int Politie;
    private int Brandweer;
    private int marachause;
    private Mission missie;

    public TeamRequest(String name, String description, int EHBO, int politie, int brandweer, int marachause, Mission mission) {
        this.name = name;
        this.description = description;
        this.EHBO = EHBO;
        this.Politie = politie;
        this.Brandweer = brandweer;
        this.marachause = marachause;
        this.missie = mission;
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
     * sets name of Request
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the discription of the request
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the discription of the request
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * gets the amount of EHBO needed
     * @return
     */
    public int getEHBO() {
        return EHBO;
    }

    /**
     * sets the amount of needed EHBO
     * @param EHBO
     */
    public void setEHBO(int EHBO) {
        this.EHBO = EHBO;
    }

    /**
     * gets the amount of police needed
     * @return
     */
    public int getPolitie() {
        return Politie;
    }

    /**
     * sets the amount of needed police
     * @param politie
     */
    public void setPolitie(int politie) {
        Politie = politie;
    }

    /**
     * gets the amount of fireman needed
     * @return
     */
    public int getBrandweer() {
        return Brandweer;
    }

    /**
     * sets the amount of needed fireman
     * @param brandweer
     */
    public void setBrandweer(int brandweer) {
        Brandweer = brandweer;
    }

    /**
     * gets the amount of marachause needed
     * @return
     */
    public int getMarachause() {
        return marachause;
    }

    /**
     * sets the amount of needed marachause
     * @param marachause
     */
    public void setMarachause(int marachause) {
        this.marachause = marachause;
    }

    /**
     * gets the assigned mission
     *
     * @return
     */
    public Mission getmissie() {
        return missie;
    }

    /**
     * sets to which mission the request is assigned to
     *
     * @param missie
     */
    public void setmissie(Mission missie) {
        this.missie = missie;
    }

    /**
     * returns the name and discription of the request as text
     *
     * @return
     */
    @Override
    public String toString() {
        return name + ": " + description;}
}
