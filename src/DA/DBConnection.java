package DA;

import DBElements.Staff;

import java.sql.*;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class DBConnection {
    private static Connection conn;

    public DBConnection() throws Exception{
    }

    public static Connection getConn() {
        try {
            String userName = "dbi329146";
            String passWord = "Fullhouseaapje";
            String url = "jdbc:sqlserver://mssql.fhict.local";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return conn = DriverManager.getConnection(url, userName, passWord);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
