package Database;

import CentralPoint.Material;

import java.util.List;

/**
 * Created by jvdwi on 21-3-2016.
 */
public class DaoMaterial extends DaoGeneric<Material>{
    @Override
    public List<Material> getAllRecord() {
        return null;
    }

    @Override
    public boolean update(Material value, String key) {
        return false;
    }
}
