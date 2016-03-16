package DA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class DBInsert {
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

    public static <T> void insertValue(Connection conn, String tableName, T object)  {
        System.out.println(object.toString());
    }

}
