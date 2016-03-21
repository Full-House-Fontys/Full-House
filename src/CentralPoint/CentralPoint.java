package CentralPoint;

import Database.DaoManager;
import Database.DbTables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaj Suiker on 20-3-2016.
 */
public class CentralPoint {
    DaoManager daoManager;
    List<Staff> staffList;

    public CentralPoint(){
        daoManager = DaoManager.INSTANCE;
        staffList = new ArrayList<>();
    }

    /**
     * @param table object of table to update
     */
    private void update(DbTables table){
        switch (table){
            case PERSONEEL:
                staffList = daoManager.getDao(table).getAllRecord();
                break;
            default:
                break;
        }
    }

    /**
     * @return is of staff on location
     */
    public ArrayList<String> staffOnLocation(){
        ArrayList<String> staffArrayList = new ArrayList<>();
        update(DbTables.PERSONEEL);
        for (Staff staff : staffList){
            if(staff.isOnLocation())
                staffArrayList.add("TeamId: "+staff.getTeamID()+"\nName: "+staff.getName()+" "+ staff.getPrefix() +" "+staff.getLastName()+"\nSort: "+staff.getSort());
        }
        return staffArrayList;
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
