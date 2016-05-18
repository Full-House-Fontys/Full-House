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

    int getMedic();

    void setMedic(int medic);

    int getPolice();

    void setPolice(int police);

    int getFireman();

    void setFireman(int fireman);

    int getMilitarypolice();

    void setMilitarypolice(int militarypolice);

    Mission getMission();

    void setMission(Mission mission);

    String toString();
}
