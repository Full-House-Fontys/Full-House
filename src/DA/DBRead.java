package DA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public <T> T getPojoForPrimarKey(Connection conn, String tableName, String primaryKey) throws Exception {
        T currentPojo = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String queryString = DBSpecifics.queryString(tableName,
                    primaryKey, QueryType.READ);
            ps = conn.prepareStatement(queryString);
            rs = ps.executeQuery();
            if (rs.next()) {
                currentPojo = DBSpecifics.getPojoFromResultSet(tableName,
                        rs);
            }
        } finally {
            closeAll(ps, rs);
        }
        return currentPojo;
    }

    public <T> T getMaterials(Connection conn, String tableName) throws Exceptions {
        List<T> materials = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String queryString = DBSpecifics.queryString(tableName, QueryType.READ);
        }
    }
}
