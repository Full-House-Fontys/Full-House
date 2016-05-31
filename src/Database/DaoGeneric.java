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
     * Gives specific data when needed
     * @param id
     * @return list of objects
     */
    public abstract ObservableList<T> getSpecificList(int id);
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

    /**
     * Inserts two ints in a database, useful for multiple to multiple relationships with pairing tables
     *
     * @param id
     * @param id1
     */
    public abstract void insertTwoInts(int id, int id1);

    /**
     * get object from database
     *
     * @param value object value
     * @param key   key
     * @return object
     */
    public abstract T getObject(T value, int key);
}