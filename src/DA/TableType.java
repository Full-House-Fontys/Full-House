package DA;

import java.util.ArrayList;

/**
 * Created by kaj75 on 16-3-2016.
 */
public enum  TableType {
    STAFF("PERSONEEL", (ArrayList<String>)DBRead.getColums("PERSONEEL"));

    private final String tableName;
    private final ArrayList<String> columns;

    TableType(String tableName, ArrayList<String> column) {
        this.tableName = tableName;
        this.columns = column;
    }

    public String getTableName(){
        return tableName;
    }

    public ArrayList<String> getColumns(){
        return columns;
    }

    public String toString() {
        return tableName;
    }

}
