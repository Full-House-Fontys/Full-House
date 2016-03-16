import DA.*;
import DBElements.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvdwi on 16-3-2016.
 */
public class MaterialManage {
    private List<Material> materials = new ArrayList();
    private DBRead db = new DBRead();

    public void renewMaterials(){
        materials.clear();
        try {
            materials.addAll(db.getMaterials(DBConnection.getConn(), "materiaal"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Material getMaterialById(int id){
        for (Material material : materials){
            if(material.getId() == id){
                return material;
            }
        }
        return null;
    }

    public void insertMaterial(String name, String sort, double locX, double locY, boolean onLoc){
        Material m = new Material(name, sort, locX, locY, onLoc);
        //DBInsert.insertValue(DBConnection.getConn(), "materiaal", m);
        renewMaterials();
    }

    public void updateMaterial(int matId, String name, String sort, double locX, double locY, boolean onLoc){
        Material m = getMaterialById(matId);
        if (! m.getName().equals(name)) {
            m.setName(name);
        }
        if (! m.getSort().equals(sort)) {
            m.setSort(sort);
        }
        if (m.getLocationX() != locX){
            m.setLocationX(locX);
        }
        if (m.getLocationY() != locY){
            m.setLocationY(locY);
        }
        if (m.isOnLocation() != onLoc){
            m.setOnLocation(onLoc);
        }
        DBUpdate.updateValue(DBConnection.getConn(), "materiaal", m);
        renewMaterials();
    }

    public void deleteMaterial(Material m){
        DBDelete.deleteValue(DBConnection.getConn(), "materiaal", m);
        renewMaterials();
    }
}
