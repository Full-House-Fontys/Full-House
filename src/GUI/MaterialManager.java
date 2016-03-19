package GUI;

import DA.*;
import DBElements.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.geom.Point2D;

/**
 * Created by jvdwi on 16-3-2016.
 */
public class MaterialManager {
    private ObservableList<Material> materials;
    private DBRead db = new DBRead();

    public MaterialManager(){
        materials = FXCollections.observableArrayList(new ArrayList());
    }

    public void renewMaterials() {
        materials.clear();
        try {
            materials.addAll(db.getMaterials(DBConnection.getConn()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Material getMaterialById(int id) {
        for (Material material : materials) {
            if (material.getId() == id) {
                return material;
            }
        }
        return null;
    }

    public ObservableList<Material> getMaterials(){
        return FXCollections.unmodifiableObservableList(materials);
    }

    public void insertMaterial(String name, String sort, double locX, double locY, boolean onLoc) throws IllegalArgumentException {
        if (!(name != null || name.trim().length() > 0)) {
            throw new IllegalArgumentException();
        } else if (!(sort != null | sort.trim().length() > 0)) {
            throw new IllegalArgumentException();
        } else {
            Material m = new Material(name, sort, new Point2D.Double(locX, locY), onLoc);
            DBInsert.insertMaterial(TableType.MATERIAL, m);
            renewMaterials();
        }

    }

    public void updateMaterial(int matId, String name, String sort, Point2D location, boolean onLoc) throws IllegalArgumentException, Exception {
        Material m = getMaterialById(matId);
        boolean changed = false;
        if (!m.getName().equals(name)) {
            if (name != null && name.trim().length() > 0) {
                m.setName(name);
                changed = true;
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        if (!m.getSort().equals(sort)) {
            if (sort != null && sort.trim().length() > 0) {
                m.setSort(sort);
                changed = true;
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        if (!m.getLocation().equals(location)) {
            m.setLocation(location);
            changed = true;
        }
        if (m.isOnLocation() != onLoc) {
            m.setOnLocation(onLoc);
            changed = true;
        }
        if (changed) {
            DBUpdate.updateMaterial(TableType.MATERIAL, m);
            renewMaterials();
        }
        else{
            throw new Exception();
        }
    }

    public void deleteMaterial(Material m) {
        DBDelete.deleteMaterial(TableType.MATERIAL, m);
        renewMaterials();
    }
}
