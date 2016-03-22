package CentralPoint;

import Database.DaoManager;
import Database.DbTables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.geom.Point2D;
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
     * @param team list of staff in team
     */
    public void setStaffOnLocation(List<String> team){
        for(String staffname : team){
            Staff toUpdate = new Staff();
            toUpdate.setOnLocation(true);
            daoManager.getDao(DbTables.PERSONEEL).update(toUpdate, staffname);
        }
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
     * inserts a new material in the database
     * @param name
     * @param sort
     * @param locX
     * @param locY
     * @param onLoc
     * @throws IllegalArgumentException
     */
    public void insertMaterial(String name, String sort, double locX, double locY, boolean onLoc) throws IllegalArgumentException {
        if (name == null || name.trim().length() <= 0) {
            throw new IllegalArgumentException();
        } else if (sort == null || sort.trim().length() <= 0) {
            throw new IllegalArgumentException();

        } else {
            Material m = new Material(name, sort, new Point2D.Double(locX, locY), onLoc);
            daoManager.getDao(DbTables.MATERIAAL).insert(m);
            renewLists(DbTables.MATERIAAL);
        }
    }

    /**
     * returns the material for the given id
     * @param id
     * @return
     */
    public Material getMaterialById(int id) {
        for (Material material : materialObservableList) {
            if (material.getId() == id) {
                return material;
            }
        }
        return null;
    }

    /**
     * update the material for given id
     * @param matId
     * @param name
     * @param sort
     * @param location
     * @param onLoc
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    public void updateMaterial(int matId, String name, String sort, Point2D location, boolean onLoc) throws IllegalArgumentException, IllegalStateException {
        Material m = getMaterialById(matId);
        boolean changed = false;
        if (!(m.getName().equals(name))) {
            if (name != null && name.trim().length() > 0) {
                m.setName(name);
                changed = true;
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        else{
            throw new IllegalStateException();
        }
        if (!(m.getSort().equals(sort))) {
            if (sort != null && sort.trim().length() > 0) {
                m.setSort(sort);
                changed = true;
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        else{
            throw new IllegalStateException();
        }
        if ((!(m.getLocation().equals(location))) && location != null) {
            m.setLocation(location);
            changed = true;
        } else if (location == null){
            throw new IllegalArgumentException();
        }
        else{
            throw new IllegalStateException();
        }
        if (m.isOnLocation() != onLoc) {
            m.setOnLocation(onLoc);
            changed = true;
        }
        else{
            throw new IllegalStateException();
        }
        if (changed) {
            daoManager.getDao(DbTables.MATERIAAL).update(m, matId);
            renewLists(DbTables.MATERIAAL);
        }
    }

    public void deleteMaterial(Material m) {
        daoManager.getDao(DbTables.MATERIAAL).delete(m.getId());
        renewLists(DbTables.MATERIAAL);
    }

}
