package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Kaj Suiker on 20-3-2016.
 */
public enum DaoManager {
    INSTANCE;

    private Connection connection;
    private final String userName = "dbi329146";
    private final String passWord = "Fullhouseaapje";
    private final String url = "jdbc:sqlserver://mssql.fhict.local";

    /**
     * Database access manager
     */
    private DaoManager(){

    }

    /**
     * Gets the connection to the database
     * @return connection to database
     */
    private Connection getConnectrion(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, userName, passWord);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DbConnection error");
        }
        return connection;
    }

    /**
     * Open connection to database.
     */
    public void open(){
        try {
            if(connection == null || connection.isClosed()){
                connection = getConnectrion();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes connection to database
     */
    public void close(){
        try {
            if(connection != null || !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param table name of table to use
     * Used to get database generic class of database table you want to use
     * @return generic to use on table
     */
    public DaoGeneric getDao(DbTables table){
        DaoGeneric dao = null;
        try {
            if (connection == null || connection.isClosed()) {
                open();
            }

            switch (table) {
                case PERSONEEL:
                    dao = new DaoStaff(connection);
                    break;
                default:
                    dao = null;
                    break;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dao;
    }
}
