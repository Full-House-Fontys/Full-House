package Database;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Kaj Suiker on 20-3-2016.
 */
public abstract class DaoGeneric<T> {
    protected final String tableName;
    protected Connection connection;

    /**
     * @param connection database connection
     * @param tableName String of tablename
     * generic abstract class for all database connection classes
     */
    protected DaoGeneric(Connection connection, String tableName){
        this.tableName = tableName;
        this.connection = connection;
    }

    /**
     * Gives list of objects
     * @return list of objects
     */
    public abstract List<T> getAllRecord();

    /**
     * @param value list of to update
     * @param key key of row
     * Update bool in a table row
     * @return bool is completed
     */
    public abstract boolean update(T value, int key);

    public abstract boolean update(T value, String key);

    public abstract boolean insert(T value);

    public abstract boolean delete(int key);
}