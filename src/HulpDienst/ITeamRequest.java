package HulpDienst;

import CentralPoint.Staff;

import java.rmi.Remote;
import java.util.List;

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

    List<Staff> getPersoneel();

    void setPersoneel(List<Staff> personeel);


}
