package Database;

import javafx.collections.ObservableList;

import java.sql.Connection;

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
    public abstract ObservableList<T> getAllRecord();

    /**
     * @param value list of to update with int
     * @param key key of row
     * Update bool in a table row
     * @return bool is completed
     */
    public abstract boolean update(T value, int key);

    /**
     * @param value list of to update with int
     * @param key key of row
     * Update bool in a table row
     * @return bool is completed
     */
    public abstract boolean update(T value, String key);

    /**
     * insert data with given value
     * @param value
     * @return
     */
    public abstract boolean insert(T value);

    /**
     * delete data in database of given key as int
     * @param key
     * @return
     */
    public abstract boolean delete(int key);
}