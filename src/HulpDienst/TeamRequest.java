package HulpDienst;

import CentralPoint.Staff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private List<Staff> personeel;

    public TeamRequest(String name, String description, int EHBO, int politie, int brandweer, int marachause) {
        this.name = name;
        this.description = description;
        this.EHBO = EHBO;
        this.Politie = politie;
        this.Brandweer = brandweer;
        this.marachause = marachause;
        personeel = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEHBO() {
        return EHBO;
    }

    public void setEHBO(int EHBO) {
        this.EHBO = EHBO;
    }

    public int getPolitie() {
        return Politie;
    }

    public void setPolitie(int politie) {
        Politie = politie;
    }

    public int getBrandweer() {
        return Brandweer;
    }

    public void setBrandweer(int brandweer) {
        Brandweer = brandweer;
    }

    public int getMarachause() {
        return marachause;
    }

    public void setMarachause(int marachause) {
        this.marachause = marachause;
    }

    public List<Staff> getPersoneel() {
        return personeel;
    }

    public void setPersoneel(List<Staff> personeel) {
        this.personeel = personeel;
    }


}
