package DA;

import CentralPoint.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jvdwi on 16-3-2016.
 */
public class DBUpdate {
    /*
    private static void closeAll(PreparedStatement ps, ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {
        }
        try {
            ps.close();
        } catch (Exception e) {
        }
        try {
            DBConnection.getConn().close();
        }catch (Exception e){
        }
    }

    public static void updateMaterial(TableType tableType, Material material) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String queryString = DBSpecifics.updateStringFromGivenID(tableType);
            Connection conn = DBConnection.getConn();
            ps = conn.prepareStatement(queryString);
            ps.setString(1, material.getName());
            ps.setString(2, material.getSort());
            ps.setDouble(3, material.getLocation().getX());
            ps.setDouble(4, material.getLocation().getY());
            ps.setBoolean(5, material.isOnLocation());
            ps.setInt(6, material.getId());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            closeAll(ps, rs);
        }
    }
    */
}
