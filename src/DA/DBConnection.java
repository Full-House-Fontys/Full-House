package DA;

import DBElements.Staff;

import java.sql.*;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class DBConnection {
    private static Connection conn;

    public DBConnection(){
        /*
        try {
            String userName = "dbi329146";
            String passWord = "Fullhouseaapje";
            String url = "jdbc:sqlserver://mssql.fhict.local";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userName, passWord);
            DBRead read = new DBRead();
            DBInsert.insertValue(conn, "PERSONEEL", read.getPojoForPrimarKey(conn, TableType.STAFF, "1"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            conn.close();
        }
        */
    }
    public static Connection getConn() {
        return conn;
    }
}
