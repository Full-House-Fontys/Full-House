package DA;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class DBRead {

    static void closeAll(PreparedStatement ps, ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {
        }
        try {
            ps.close();
        } catch (Exception e) {
        }
    }

    static  <T> Map<String, String> getColums(String tableName){
        Map<String, String> column = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String queryString = DBSpecifics.queryString(tableName, QueryType.COLUMN, "");
            ps = DBConnection.getConn().prepareStatement(queryString);
            ps.setString(1, tableName);
            rs = ps.executeQuery();
            while (rs.next()) {
                String key = rs.getString("COLUMN_NAME");
                String value = rs.getString("DATA_TYPE");
                if(key != null || key.equals("")){
                    column.put(key, value);
                }
            }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            closeAll(ps, rs);
        }
        return column;
    }

    public <T> T getPojoForPrimarKey(Connection conn, TableType tableType, String primaryKey) throws Exception {
        T currentPojo = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String queryString = DBSpecifics.queryString(tableType.getTableName(), QueryType.READ, primaryKey);
            ps = conn.prepareStatement(queryString);
            ps.setInt(1, Integer.parseInt(primaryKey));

            rs = ps.executeQuery();
            if (rs.next()) {
                currentPojo = DBSpecifics.getPojoFromResultSet(tableType.getTableName(), rs);
            }
        } finally {
            closeAll(ps, rs);
        }
        return currentPojo;
    }


    public <T> T getMaterials(Connection conn, String tableName) throws Exception {
        List<T> materials = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
//        try{
//            String queryString = DBSpecifics.queryString(tableName, QueryType.READ);
//        }
        return null;
    }
}
