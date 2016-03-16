import DA.*;
import DBElements.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jvdwi on 16-3-2016.
 */
public class MaterialManage {
    private List<Material> materials = new ArrayList();
    private DBRead db = new DBRead();

    public void renewMaterials() {
        materials.clear();
        try {
            materials.addAll(db.getMaterials(DBConnection.getConn(), "materiaal"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Material getMaterialById(int id) {
        for (Material material : materials) {
            if (material.getId() == id) {
                return material;
            }
        }
        return null;
    }

    private List<Material> getMaterials(){
        return Collections.unmodifiableList(materials);
    }

    public void insertMaterial(String name, String sort, double locX, double locY, boolean onLoc) {
        if (!(name != null || name.trim().length() > 0)) {
            return;
        } else if (!(sort != null | sort.trim().length() > 0)) {
            return;
        } else {
            Material m = new Material(name, sort, locX, locY, onLoc);
            DBInsert.insertValue(DBConnection.getConn(), "materiaal", m);
            renewMaterials();
        }
    }

    public void updateMaterial(int matId, String name, String sort, double locX, double locY, boolean onLoc) {
        Material m = getMaterialById(matId);
        boolean changed = false;
        if (!m.getName().equals(name)) {
            if (name != null && name.trim().length() > 0) {
                m.setName(name);
                changed = true;
            }
        }
        if (!m.getSort().equals(sort)) {
            if (sort != null && sort.trim().length() > 0) {
                m.setSort(sort);
                changed = true;
            }
        }
        if (m.getLocationX() != locX) {
            m.setLocationX(locX);
            changed = true;
        }
        if (m.getLocationY() != locY) {
            m.setLocationY(locY);
            changed = true;
        }
        if (m.isOnLocation() != onLoc) {
            m.setOnLocation(onLoc);
            changed = true;
        }
        if (changed) {
            DBUpdate.updateValue(DBConnection.getConn(), "materiaal", m);
            renewMaterials();
        }
    }

    public void deleteMaterial(Material m) {
        DBDelete.deleteValue(DBConnection.getConn(), "materiaal", m);
        renewMaterials();
    }
}
