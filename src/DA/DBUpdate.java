package DA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jvdwi on 16-3-2016.
 */
public class DBUpdate {

    static void closeAll(PreparedStatement ps) {
        try {
            ps.close();
        } catch (Exception e) {
        }
        try {
            DBConnection.getConn().close();
        }catch (Exception e){
        }
    }

    static void switchInput(String type, int pos, String value, PreparedStatement statement) throws SQLException {
        switch (type){
            case "varchar":
                statement.setString(pos, value);
        }
    }

    public boolean updateValue(TableType table,String columnSet, String valueSet, String columnWhere, String valuWhere) throws SQLException {
        /*
        try {

            String query = "update" + table.getTableName() + " set "+ columnSet +" = ? where "+ columnWhere +" = ?";
             stmt = DBConnection.getConn().prepareStatement(query);
            switchInput(table.getColumns().get(columnSet), 1, valueSet, stmt);
            switchInput(table.getColumns().get(columnSet), 2, valuWhere, stmt);
            stmt.exec executeUpdate();
            closeAll(stmt);
            *
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        */
        return true;
    }
}
