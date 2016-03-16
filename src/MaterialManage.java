import DA.DBConnection;
import DA.DBInsert;
import DA.DBRead;
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
        materials.addAll(db.getMaterials(DBConnection.getConn(), "materiaal"));
    }

    public void insertMaterial(String name, String sort, double locX, double locY, boolean onLoc){
        Material m = new Material(name, sort, locX, locY, onLoc);
        DBInsert.insertValue(DBConnection.getConn(), "materiaal", m);
        renewMaterials();
    }
}
