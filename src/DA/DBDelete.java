package DA;

import CentralPoint.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by jvdwi on 16-3-2016.
 */
public class DBDelete {

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

    public static void deleteMaterial(TableType tableType, Material material) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String queryString = DBSpecifics.deleteString(tableType);
            Connection conn = DBConnection.getConn();
            ps = conn.prepareStatement(queryString);
            ps.setInt(1, material.getId());
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            closeAll(ps, rs);
        }
    }
}
