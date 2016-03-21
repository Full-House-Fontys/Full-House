package CentralPoint;

import Database.DaoManager;
import Database.DbTables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaj Suiker on 20-3-2016.
 */
public class CentralPoint {
    DaoManager daoManager;
    List<Staff> staffList;
    List<Material> materialList;

    ObservableList<Staff> staffObservableList;
    ObservableList<Material> materialObservableList;

    public CentralPoint(){
        daoManager = DaoManager.INSTANCE;
        staffList = new ArrayList<>();
        materialList = new ArrayList();
        staffObservableList = FXCollections.observableArrayList(staffList);
        materialObservableList = FXCollections.observableArrayList(materialList);
    }

    /**
     * @param table object of table to renewLists
     */
    private void renewLists(DbTables table){
        switch (table){
            case PERSONEEL:
                staffObservableList.clear();
                staffObservableList = daoManager.getDao(table).getAllRecord();
                break;
            case MATERIAAL:
                materialObservableList.clear();
                materialObservableList = daoManager.getDao(table).getAllRecord();
            default:
                break;
        }
    }

    /**
     * @return is of staff on location
     */
    public ArrayList<String> staffOnLocation(){
        ArrayList<String> staffArrayList = new ArrayList<>();
        renewLists(DbTables.PERSONEEL);
        for (Staff staff : staffList){
            if(staff.isOnLocation())
                staffArrayList.add("TeamId: "+staff.getTeamID()+"\nName: "+staff.getName()+" "+ staff.getPrefix() +" "+staff.getLastName()+"\nSort: "+staff.getSort());
        }
        return staffArrayList;
    }

    /**
     * returns the materialLists as observableList
     * @return unmodifiableObservableList of materials
     */
    public ObservableList<Material> getMaterials(){
        renewLists(DbTables.MATERIAAL);
        return FXCollections.unmodifiableObservableList(materialObservableList);
    }

    /**
     * @param team list of staff in team
     */
    public void setStaffOnLocation(List<String> team){
        for(String staffname : team){
            Staff toUpdate = new Staff();
            toUpdate.setOnLocation(true);
            daoManager.getDao(DbTables.PERSONEEL).update(toUpdate, staffname);
        }
    }

}
