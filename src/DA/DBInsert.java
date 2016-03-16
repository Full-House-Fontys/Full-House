package DA;

import DBElements.Staff;

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
        try {
            DBConnection.getConn().close();
        }catch (Exception e){
        }
    }

    public static <T> void insertStaff(TableType tableType, Staff staff)  {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String queryString = DBSpecifics.insertString(tableType);
            Connection connection = DBConnection.getConn();
            ps = connection.prepareStatement(queryString);
            ps.setString(1, staff.getLastName());
            ps.setString(2, staff.getPrefix());
            ps.setString(3, staff.getName());
            ps.setString(4, staff.getUserName());
            ps.setString(5, staff.getPassword());
            ps.setDouble(6, staff.getLocation().getX());
            ps.setDouble(7, staff.getLocation().getY());
            ps.setString(8, staff.getSort());
            ps.setInt(9, staff.getTeamID());
            ps.setInt(10, staff.isOnLocation() ? 1:0);

            ps.executeUpdate();
            connection.close();

        } catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            closeAll(ps, rs);
        }
    }

}
