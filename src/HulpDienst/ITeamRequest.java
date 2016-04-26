package HulpDienst;

import CentralPoint.Mission;

import java.rmi.Remote;

/**
 * Created by Mark on 6-4-2016.
 */
public interface ITeamRequest extends Remote {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    int getEHBO();

    void setEHBO(int EHBO);

    int getPolitie();

    void setPolitie(int politie);

    int getBrandweer();

    void setBrandweer(int brandweer);

    int getMarachause();

    void setMarachause(int marachause);

    Mission getmissie();

    void setmissie(Mission missie);

    String toString();
}
